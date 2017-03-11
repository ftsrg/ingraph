MATCH (:Root)-->(i:Child)
WHERE NOT exists(i.id) OR i.id > 'x'
RETURN i.id
