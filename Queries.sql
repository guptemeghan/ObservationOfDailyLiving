--1. Find patients with the lowest weight amongst HIV patients.--
select DISTINCT U.name AS UserName, MAX(CAST(O.Info1Value AS INT)) As Weight from Users U , PatientClass P , OBSERVATIONS O
where U.UserType = 0 
AND P.cname='HIV'
AND P.ptid=U.id 
AND O.TNAME = 'Weight' 
AND O.ptid=U.id
group by U.name
having MAX(CAST(O.Info1Value AS INT)) = 
(Select Min(AMOUNT) 
FROM (Select MAX(CAST(O.Info1Value AS INT)) AS AMOUNT from OBSERVATIONS O 
where O.TNAME = 'Weight' AND O.Info1Text='Amount' AND DTIMERECORD IN (SELECT MAX(DTIMERECORD) FROM OBSERVATIONS O  where O.TNAME = 'Weight' AND O.Info1Text='Amount' group by O.ptid)
group by O.ptid))


--2. Of all Obesity and High Risk Patients, find patients with the highest blood pressure.---
select DISTINCT U.name AS UserName, MAX(CAST(O.Info1Value AS INT)) As BloodPressure from Users U , PatientClass P , OBSERVATIONS O
where U.UserType = 0 
AND (P.cname='Obesity' OR P.cname='High Risk Pregnancy')
AND P.ptid=U.id 
AND O.TNAME = 'Blood Pressure' 
AND O.ptid=U.id
group by U.name
having MAX(CAST(O.Info1Value AS INT)) = 
(Select MAX(AMOUNT) 
FROM (Select MAX(CAST(O.Info1Value AS INT)) AS AMOUNT from OBSERVATIONS O 
where O.TNAME = 'Blood Pressure' AND O.Info1Text='Systolic' AND DTIMERECORD IN (SELECT MAX(DTIMERECORD) FROM OBSERVATIONS O
group by O.ptid)))


--3. Find patients who have healthfriends with no outstanding alerts.--
Select name from users where id not in
(select distinct patientid from HEALTHFRIENDS where friendid in
(select distinct A.ptid from ALERTS A where A.indicator = 0)) AND usertype=0


--4. Find patients who live in same city as healthfriend.--
select distinct H.patientid As Patient,H.FriendID as Friend,U2.City As City from HealthFriends H,Users U1,Users U2
where U1.id=H.patientid
AND U2.id=H.friendid
AND U1.City=U2.City

--5. For PatientX, list their healthfriends, ordered by date in which friendships were initiated.--
select distinct U1.name AS UserName,U2.name AS Friend ,InitDate AS DateOfFriendship from
(select distinct H.patientid as PatientID,H.Friendid As FriendID,H.initdate as InitDate
from HealthFriends H,Users U
order by H.patientid,H.initdate),Users U1,Users U2
where U1.id=patientid
AND U2.id=FriendID
order by U1.name,InitDate

--6. For each patient, find the number of healthfriends made in the last month.--
select u.name,count(h.friendid)
from HealthFriends H, users u
where MONTHS_BETWEEN(sysdate,h.initdate) < 1 AND H.patientid=u.id AND u.usertype=0
group by  u.name

--7. For each patient and each type of observation, show the number of such observations recorded by the patients.
select U.name AS PatientName,O.Tname AS ObservationType,count(O.oid) as Count
from Users U,Observations O
where U.id=O.ptid
group by U.name,O.Tname

--8. For each patient, and each of their healthfriends, list the number of lingering alerts of the healthfriend.

select u.name,h.friendid,count(a.alertid)
from users u, HEALTHFRIENDS h,alerts a
where u.id=h.patientid and h.friendid=a.ptid and a.indicator=0 and u.usertype=0
group by u.name,h.friendid

