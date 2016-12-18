// Q14. Weighted paths. Given PersonX and PersonY, find all weighted paths of the shortest length between them in the subgraph induced by the Knows relationship. The weight of the path takes into consideration amount of Posts/Comments exchanged.
MATCH path = allShortestPaths((person1:Person {id:{1}})-[:KNOWS]-(person2:Person {id:{2}}))
WITH nodes(path) AS pathNodes
RETURN
  extract(n IN pathNodes | n.id) AS pathNodeIds,
  reduce(
    weight = 0.0, idx IN range(1, size(pathNodes)-1) |
      extract(
        prev IN [pathNodes[idx-1]] |
        extract(
            curr IN [pathNodes[idx]] |
            weight +
              length((curr)<-[:HAS_CREATOR]-(:Comment)-[:REPLY_OF]->(:Post)-[:HAS_CREATOR]->(prev)) * 1.0 +
              length((prev)<-[:HAS_CREATOR]-(:Comment)-[:REPLY_OF]->(:Post)-[:HAS_CREATOR]->(curr)) * 1.0 +
              length((prev)-[:HAS_CREATOR]-(:Comment)-[:REPLY_OF]-(:Comment)-[:HAS_CREATOR]-(curr)) * 0.5
        )
      )[0][0]
  ) AS weight
ORDER BY weight DESC
