MATCH
  (route:Route)
    -[f:follows]->(swP:SwitchPosition)
    -[t:target]->(sw:Switch)
    -[m:monitoredBy]->(sensor:Sensor)
RETURN DISTINCT route.id, sensor.id, swP.id, sw.id
