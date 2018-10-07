// Interactive update 9
MATCH (n:Message:Post {id: $postId})
DETACH DELETE n
