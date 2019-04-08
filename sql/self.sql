create view edgelist
as
select distinct
	t1._user_id as i,
	t2._user_id as j,
	substr(t1._epoch,1,8) as t,
	substr(t1._time_ap,9,2) as h,
	substr(t1._time_ap,15) as d
from v t1
inner join v t2 on t1._bssid = t2._bssid
	and (t1._epoch = t2._epoch and t1._user_id < t2._user_id)
order by t1._epoch