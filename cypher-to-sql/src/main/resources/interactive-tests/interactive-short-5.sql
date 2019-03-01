select p_personid, p_firstname, p_lastname
from message, person
where m_messageid = 2061584476422 and m_creatorid = p_personid;
