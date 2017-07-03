#!/bin/bash

$NEO4J_HOME/bin/neo4j-import --into $DB_DIR \
  --nodes:Message:Comment comment$POSTFIX \
  --nodes:Message:Post post$POSTFIX \
  --relationships:replyOf comment_replyOf_comment$POSTFIX \
  --relationships:replyOf comment_replyOf_post$POSTFIX \
  --delimiter '|'
