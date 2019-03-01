SELECT m_messageid
FROM message
WHERE m_c_replyof IS NOT NULL
ORDER BY m_messageid
