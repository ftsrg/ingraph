MATCH
  (sensor:Sensor)<-[:monitoredBy]-(segment1:Segment),
  (segment1:Segment)-[:connectsTo]->
  (segment2:Segment)-[:connectsTo]->
  (segment3:Segment)-[:connectsTo]->
  (segment4:Segment)-[:connectsTo]->
  (segment5:Segment)-[:connectsTo]->(segment6:Segment),
  (segment2:Segment)-[:monitoredBy]->(sensor:Sensor),
  (segment3:Segment)-[:monitoredBy]->(sensor:Sensor),
  (segment4:Segment)-[:monitoredBy]->(sensor:Sensor),
  (segment5:Segment)-[:monitoredBy]->(sensor:Sensor),
  (segment6:Segment)-[:monitoredBy]->(sensor:Sensor)
RETURN sensor.id, segment1.id, segment2.id, segment3.id, segment4.id, segment5.id, segment6.id
