
      MATCH (:Root {name: 'x'})-->(i:Child)
      WHERE NOT exists(i.id) OR i.id > 'te'
      RETURN i