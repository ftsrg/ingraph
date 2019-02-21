select p.p_firstname, friend.p_firstname, friend.p_personid
from person p
       join knows_undirected on (p.p_personid = k_person1id)
       join person friend on (k_person2id = friend.p_personid)
where p.p_personid = 8796093022222
  and friend.p_personid <> p.p_personid
