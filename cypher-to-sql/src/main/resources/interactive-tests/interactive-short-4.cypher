MATCH (m:Message {id:2061584476422})
RETURN
  m.creationDate as messageCreationDate,
  CASE exists(m.content)
    WHEN true THEN m.content
    ELSE m.imageFile
  END AS messageContent
