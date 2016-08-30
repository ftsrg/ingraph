MATCH (cypher:QueryLanguage)-[:QUERIES]->(graphs)
MATCH (cypher)<-[:USES]-(u:User) WHERE u.name IN ['Oracle', 'Apache Spark', 'Tableau', 'Structr']
MATCH (openCypher)-[:MAKES_AVAILABLE]->(cypher)
RETURN cypher.attributes
