MATCH (application:Application)-[:DEPENDS_ON*]->(server)
WHERE server.host = "HARDWARE-SERVER-3"
RETURN application.type as Type, application.host as Host
