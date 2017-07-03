#!/bin/bash

$NEO4J_HOME/bin/neo4j-import --into $DB_DIR \
  --nodes:Person person$POSTFIX \
  --nodes:Message:Comment comment$POSTFIX \
  --nodes:Message:Post post$POSTFIX \
  --relationships:knows person_knows_person$POSTFIX \
  --relationships:replyOf comment_replyOf_comment$POSTFIX \
  --relationships:replyOf comment_replyOf_post$POSTFIX \
  --delimiter '|'
