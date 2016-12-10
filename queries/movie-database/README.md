# Movie database queries

The queries are taken from <https://neo4j.com/developer/movie-database/>.

## Graph schema

```
(:Movie {title, released, ...})
(:Person {name, born, ...​})
(:Person)-[:ACTED_IN|:DIRECTED|:PRODUCED]->(:Movie)
```

This defined a bipartite graph with a `Person`s and a `Movie`s partition.

## Queries

### Basic queries

Actors who played in some movie

```
MATCH (m:Movie {title: 'Forrest Gump'})<-[:ACTS_IN]-(a:Actor)
RETURN a.name, a.birthplace
```

Find the most prolific actors

```
MATCH (a:Actor)-[:ACTS_IN]->(m:Movie)
RETURN a, count(*)
ORDER BY count(*) DESC LIMIT 10
```

### Queries using aggregation

Find actors who have been in less than 3 movies

```
MATCH (a:Actor)-[:ACTS_IN]->(m:Movie)
WITH a, count(m) AS movie_count
WHERE movie_count < 3
RETURN a, movie_count
ORDER BY movie_count DESC LIMIT 5
```

Find the actors with 20+ movies, and the movies in which they acted

```
MATCH (a:Actor)-[:ACTS_IN]->(m:Movie)
WITH a, collect(m.title) AS movies
WHERE length(movies) >= 20
RETURN a, movies
ORDER BY length(movies) DESC LIMIT 10
```

Find prolific actors (10+) who have directed at least two films, count films acted in and list films directed

```
MATCH (a:Actor)-[:ACTS_IN]->(m:Movie)
WITH a, count(m) AS acted
WHERE acted >= 10
WITH a, acted
MATCH (a:Director)-[:DIRECTED]->(m:Movie)
WITH a, acted, collect(m.title) AS directed
WHERE length(directed) >= 2
RETURN a.name, acted, directed
ORDER BY length(directed) DESC, acted DESC
```

Rewritten to filter both `:Actor` and `:Director` labels up front

```
MATCH (a:Actor:Director)-[:ACTS_IN]->(m:Movie)
WITH a, count(1) AS acted
WHERE acted >= 10
WITH a, acted
MATCH (a:Actor:Director)-[:DIRECTED]->(m:Movie)
WITH a, acted, collect(m.title) AS directed
WHERE length(directed) >= 2
RETURN a.name, acted, directed
ORDER BY length(directed) DESC, acted DESC
```

Using the lowest cardinality label, `:Director`

```
MATCH (a:Director)-[:ACTS_IN]->(m)
WITH a, count(m) AS acted
WHERE acted >= 10
WITH a, acted
MATCH (a)-[:DIRECTED]->(m)
WITH a, acted, collect(m.title) AS directed
WHERE length(directed) >= 2
RETURN a.name, acted, directed
ORDER BY length(directed) DESC, acted DESC
```

### User ratings

Mutual friend recommendations

```
MATCH (u:User {login: 'Michael'})-[:FRIEND]-(f:Person)-[r:RATED]->(m:Movie)
WHERE r.stars > 3
RETURN f.name, m.title, r.stars, r.comment
```

Ratings by like-minded people

```
MATCH (u:User)-[r:RATED]->(m:Movie)<-[r2:RATED]-(likeminded),
(u)-[:FRIEND]-(friend)
WHERE r.stars > 3 AND r2.stars >= 3
RETURN likeminded, count(*)
ORDER BY count(*) desc LIMIT 10
```

User Ratings

```
MATCH (u:User {login: 'Michael'})-[r:RATED]->(m:Movie)
WHERE r.stars > 3
RETURN m.title, r.stars, r.comment
```

Recommendations including counts, grouping and sorting

```
MATCH (u:User {login: 'Michael'})-[:FRIEND]-()-[r:RATED]->(m:Movie)
RETURN m.title, avg(r.stars), count(*)
ORDER BY AVG(r.stars) DESC, count(*) DESC
```

### Demo queries (from the Neo4j web UI)

Find the actor named "Tom Hanks"...

```
MATCH (tom {name: "Tom Hanks"})
RETURN tom
```

Find the movie with title "Cloud Atlas"...

```
MATCH (cloudAtlas {title: "Cloud Atlas"})
RETURN cloudAtlas
```

Find 10 people...

```
MATCH (people:Person)
RETURN people.name
LIMIT 10
```

Find movies released in the 1990s...

```
MATCH (nineties:Movie)
WHERE nineties.released > 1990 AND nineties.released < 2000
RETURN nineties.title
```

List all Tom Hanks movies...

```
MATCH (tom:Person {name: "Tom Hanks"})-[:ACTED_IN]->(tomHanksMovies)
RETURN tom, tomHanksMovies
```

Who directed "Cloud Atlas"?

```
MATCH (cloudAtlas {title: "Cloud Atlas"})<-[:DIRECTED]-(directors)
RETURN directors.name
```

Tom Hanks' co-actors...

```
MATCH (tom:Person {name:"Tom Hanks"})-[:ACTED_IN]->(m)<-[:ACTED_IN]-(coActors)
RETURN coActors.name
```

How people are related to "Cloud Atlas"...

```
MATCH (people:Person)-[relatedTo]-(:Movie {title: "Cloud Atlas"})
RETURN people.name, Type(relatedTo), relatedTo
```

Movies and actors up to 4 "hops" away from Kevin Bacon

```
MATCH (bacon:Person {name:"Kevin Bacon"})-[*1..4]-(hollywood)
RETURN DISTINCT hollywood
```

Bacon path, the shortest path of any relationships to Meg Ryan -- uses shortesPath

```
MATCH p=shortestPath(
(bacon:Person {name:"Kevin Bacon"})-[*]-(meg:Person {name:"Meg Ryan"})
)
RETURN p
```

Extend Tom Hanks co-actors, to find co-co-actors who haven't work with Tom Hanks...

```
MATCH (tom:Person {name:"Tom Hanks"})-[:ACTED_IN]->(m)<-[:ACTED_IN]-(coActors),
(coActors)-[:ACTED_IN]->(m2)<-[:ACTED_IN]-(cocoActors)
WHERE NOT (tom)-[:ACTED_IN]->(m2)
RETURN cocoActors.name AS Recommended, count(*) AS Strength ORDER BY Strength DESC
```

Find someone to introduce Tom Hanks to Tom Cruise

```
MATCH (tom:Person {name:"Tom Hanks"})-[:ACTED_IN]->(m)<-[:ACTED_IN]-(coActors),
(coActors)-[:ACTED_IN]->(m2)<-[:ACTED_IN]-(cruise:Person {name:"Tom Cruise"})
RETURN tom, m, coActors, m2, cruise
```
