MATCH
  (route:Route)
    -[f:follows]->(swP:SwitchPosition)
    -[t:target]->(sw:Switch)
    -[m:monitoredBy]->(sensor:Sensor)
RETURN route, sensor, swP, sw
