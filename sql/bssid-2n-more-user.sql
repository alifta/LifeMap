select
	_bssid,
	count(distinct _user_id) as c
from apTable
group by _bssid
having c > 1
order by _bssid