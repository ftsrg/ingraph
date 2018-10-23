#!/bin/bash

PG_CSV_DIR=${PG_CSV_DIR:-$(pwd)/../../../ldbc_snb_datagen/social_network/}
PG_DB_NAME=${PG_DB_NAME:-ldbcsf1}
PG_USER=${PG_USER:-$USER}

(cat snb-load-knows_undirected-table.sql | sed "s|PATHVAR|$PG_CSV_DIR|g"; echo "\q\n") | /usr/bin/psql -d $PG_DB_NAME -U $PG_USER
