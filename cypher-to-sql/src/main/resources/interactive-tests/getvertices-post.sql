SELECT m_messageid
FROM message
WHERE m_c_replyof IS NULL
ORDER BY m_messageid
