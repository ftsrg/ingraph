MATCH
  (sensor:Sensor)<-[mb1:monitoredBy]-(segment1:Segment),
  (segment1:Segment)-[ct1:connectsTo]->
  (segment2:Segment)-[ct2:connectsTo]->
  (segment3:Segment)-[ct3:connectsTo]->
  (segment4:Segment)-[ct4:connectsTo]->
  (segment5:Segment)-[ct5:connectsTo]->(segment6:Segment),
  (segment2:Segment)-[mb2:monitoredBy]->(sensor:Sensor),
  (segment3:Segment)-[mb3:monitoredBy]->(sensor:Sensor),
  (segment4:Segment)-[mb4:monitoredBy]->(sensor:Sensor),
  (segment5:Segment)-[mb5:monitoredBy]->(sensor:Sensor),
  (segment6:Segment)-[mb6:monitoredBy]->(sensor:Sensor)
RETURN sensor, segment1, segment2, segment3, segment4, segment5, segment6
