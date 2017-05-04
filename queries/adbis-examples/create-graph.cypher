CREATE
  (a:Person:Student {name: 'Alice', speaks: ['en']}),
  (b:Person {name: 'Bob', speaks: ['fr']}),
  (c:Person:Teacher {name: 'Cecil', speaks: ['en', 'de']}),
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
