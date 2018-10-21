SELECT m_messageid
FROM message JOIN likes ON (m_messageid = l_messageid)
ORDER BY m_messageid
