select t_name, count(*)
from tag, message_tag, message,
 ( select k_person2id
   from knows_undirected
   where
   k_person1id = 30786325579101
   union
   select k2.k_person2id
   from knows_undirected k1, knows_undirected k2
   where
   k1.k_person1id = 30786325579101 and k1.k_person2id = k2.k_person1id and k2.k_person2id <> 30786325579101
 ) f
where
m_creatorid = f.k_person2id and
m_c_replyof IS NULL and -- post, not comment
m_messageid = mt_messageid and
mt_tagid = t_tagid and
t_name <> 'Shakira' and
exists (select * from tag, message_tag where mt_messageid = m_messageid and mt_tagid = t_tagid and t_name = 'Shakira')
group by t_name
order by 2 desc, t_name
limit 10
