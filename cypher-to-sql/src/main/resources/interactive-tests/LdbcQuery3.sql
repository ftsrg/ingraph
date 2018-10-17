select p_personid, p_firstname, p_lastname, ct1, ct2, total
from
 ( select k_person2id
   from knows
   where
   k_person1id = 15393162790207
   union
   select k2.k_person2id
   from knows k1, knows k2
   where
   k1.k_person1id = 15393162790207 and k1.k_person2id = k2.k_person1id and k2.k_person2id <> 15393162790207
 ) f,  person, place p1, place p2,
 (
  select chn.m_c_creatorid, ct1, ct2, ct1 + ct2 as total
  from
   (
      select m_creatorid as m_c_creatorid, count(*) as ct1 from message, place
      where
        m_locationid = pl_placeid and pl_name = 'Puerto_Rico' and
        m_creationdate >= TIMESTAMP 'epoch' + 1291161600000 * INTERVAL '1 ms' and  m_creationdate < (TIMESTAMP 'epoch' + 1291161600000 * INTERVAL '1 ms' + INTERVAL '1 days' * 30)
      group by m_c_creatorid
   ) chn,
   (
      select m_creatorid as m_c_creatorid, count(*) as ct2 from message, place
      where
        m_locationid = pl_placeid and pl_name = 'Republic_of_Macedonia' and
        m_creationdate >= TIMESTAMP 'epoch' + 1291161600000 * INTERVAL '1 ms' and  m_creationdate < (TIMESTAMP 'epoch' + 1291161600000 * INTERVAL '1 ms' + INTERVAL '1 days' * 30)
      group by m_creatorid --m_c_creatorid
   ) ind
  where CHN.m_c_creatorid = IND.m_c_creatorid
 ) cpc
where
f.k_person2id = p_personid and p_placeid = p1.pl_placeid and
p1.pl_containerplaceid = p2.pl_placeid and p2.pl_name <> 'Puerto_Rico' and p2.pl_name <> 'Republic_of_Macedonia' and
f.k_person2id = cpc.m_c_creatorid
order by 6 desc, 1
limit 20
