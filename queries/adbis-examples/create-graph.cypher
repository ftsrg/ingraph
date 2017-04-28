CREATE
  (a:Person:Student {name: 'Alice', speaks: ['en', 'fr']}),
  (b:Person {name: 'Bob', speaks: ['en', 'fr']}),
  (c:Person:Teacher {name: 'Cecil', speaks: ['en']}),
  (d:Message:Post {language: 'en'}),
  (e:Message:Comment {language: 'en'}),
  (f:Message:Comment {language: 'fr'}),
  (a)-[:KNOWS {since: 2011}]->(b),
  (b)-[:KNOWS {since: 1979}]->(c),
  (a)-[:LIKES]->(d),
  (b)-[:LIKES]->(d),
  (c)-[:LIKES]->(e),
  (d)<-[:REPLY_OF]-(e),
  (e)<-[:REPLY_OF]-(f)
