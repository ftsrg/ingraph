select p_personid, p_firstname, p_lastname, k_creationdate
from knows_undirected, person
where k_person1id = 32985348833679 and k_person2id = p_personid
order by k_creationdate desc, p_personid asc;
