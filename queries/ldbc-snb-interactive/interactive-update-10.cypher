// Remove Forum
MATCH (n:Forum {id: $forumId})
DETACH DELETE n