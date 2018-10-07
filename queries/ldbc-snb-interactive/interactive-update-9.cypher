MATCH (n:Message:Post {id: $postId})
DETACH DELETE n
