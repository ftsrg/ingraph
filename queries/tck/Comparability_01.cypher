MATCH (:Root)-->(i:Child)
WHERE exists(i.id) AND i.id > 'x'
RETURN i.id
