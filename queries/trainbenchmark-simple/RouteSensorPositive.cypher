MATCH
  (route:Route)
    -[f:follows]->(swP:SwitchPosition)
    -[t:target]->(sw:Switch)
    -[m:monitoredBy]->(sensor:Sensor)
RETURN DISTINCT route, sensor, swP, sw
