select distinct
	t1._ap_id,
	t1._user_id,
	t2._ap_id,
	t2._user_id,
	t1._bssid,
	t1._epoch
from v t1
inner join v t2 on t1._bssid = t2._bssid
	and (t1._epoch = t2._epoch and t1._user_id <> t2._user_id)

order by t1._epoch