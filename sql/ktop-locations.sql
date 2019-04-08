select
_node_id,
count (DATE) as VISIT
from
(select
	_node_id,
	substr(_time_stay,1,8) as DATE
from
	stayTable
group by _node_id, DATE) as VISIT_TABLE
group by _node_id
order by VISIT desc