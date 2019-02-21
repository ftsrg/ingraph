SELECT m_messageid
FROM message JOIN likes ON (m_messageid = l_messageid)
WHERE m_c_replyof IS NOT NULL
ORDER BY m_messageid
