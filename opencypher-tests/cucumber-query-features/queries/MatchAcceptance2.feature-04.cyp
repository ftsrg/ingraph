
      MATCH (:Root {name: 'x'})-->(i)
      WHERE exists(i.id) OR i.id > 'te'
      RETURN i