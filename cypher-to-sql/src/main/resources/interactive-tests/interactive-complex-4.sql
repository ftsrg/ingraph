select t_name, count(*)
from tag, message, message_tag recent, knows_undirected
where
    m_messageid = mt_messageid and
    mt_tagid = t_tagid and
    m_creatorid = k_person2id and
    m_c_replyof IS NULL and -- post, not comment
    k_person1id = 10995116278874 and
    m_creationdate >= to_bigint(TIMESTAMP 'epoch' + 1338508800000 * INTERVAL '1 ms') and  m_creationdate < to_bigint(TIMESTAMP 'epoch' + 1338508800000 * INTERVAL '1 ms' + INTERVAL '1 days' * 28) and
    not exists (
        select * from
  (select distinct mt_tagid from message, message_tag, knows_undirected
        where
	k_person1id = 10995116278874 and
        k_person2id = m_creatorid and
        m_c_replyof IS NULL and -- post, not comment
        mt_messageid = m_messageid and
        m_creationdate < to_bigint(TIMESTAMP 'epoch' + 1338508800000 * INTERVAL '1 ms')) tags
  where  tags.mt_tagid = recent.mt_tagid)
group by t_name
order by 2 desc, t_name
limit 10
