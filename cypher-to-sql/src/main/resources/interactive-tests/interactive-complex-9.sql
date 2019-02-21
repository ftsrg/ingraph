select p_personid, p_firstname, p_lastname,
       m_messageid, COALESCE(m_ps_imagefile,'')||COALESCE(m_content,''), m_creationdate
from
  ( select k_person2id
    from knows_undirected
    where
    k_person1id = 32985348834013
    union
    select k2.k_person2id
    from knows_undirected k1, knows_undirected k2
    where
    k1.k_person1id = 32985348834013 and k1.k_person2id = k2.k_person1id and k2.k_person2id <> 32985348834013
  ) f, person, message
where
  p_personid = m_creatorid and p_personid = f.k_person2id and
  m_creationdate < to_bigint(TIMESTAMP 'epoch' + 1346112000000 * INTERVAL '1 ms')
order by m_creationdate desc, m_messageid asc
limit 20
