MATCH (:Root {name: 'x'})-->(i:Child)
WHERE i.id > 'te'
RETURN i
