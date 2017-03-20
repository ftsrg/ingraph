## Preprocessing

Set the `$NEO4J_HOME` and the following environment variables appropriately:

```bash
export POSTFIX=_0_0.csv
export DB_DIR=$NEO4J_HOME/data/databases/graph.db
```

### Script for converting city, country and contintent to City, Country and Continent

```bash
sed -i "s/|c\([a-z]\+\)$/|C\1/" place$POSTFIX
```

### Script for replacing headers

```bash
while read line; do
  IFS=' ' read -r -a array <<< $line
  filename=${array[0]}
  header=${array[1]}
  sed -i "1s/.*/$header/" $filename$POSTFIX
done < headers.md
```

## Neo4j import

```bash
$NEO4J_HOME/bin/neo4j-import --into $DB_DIR \
  --nodes:Message:Comment comment$POSTFIX \
  --nodes:Forum forum$POSTFIX \
  --nodes:Person person$POSTFIX \
  --nodes:Place place$POSTFIX \
  --nodes:Message:Post post$POSTFIX \
  --nodes:TagClass tagclass$POSTFIX \
  --nodes:Tag tag$POSTFIX \
  --relationships:hasCreator comment_hasCreator_person$POSTFIX \
  --relationships:isLocatedIn comment_isLocatedIn_place$POSTFIX \
  --relationships:replyOf comment_replyOf_comment$POSTFIX \
  --relationships:replyOf comment_replyOf_post$POSTFIX \
  --relationships:containerOf forum_containerOf_post$POSTFIX \
  --relationships:hasMember forum_hasMember_person$POSTFIX \
  --relationships:hasModerator forum_hasModerator_person$POSTFIX \
  --relationships:hasTag forum_hasTag_tag$POSTFIX \
  --relationships:hasInterest person_hasInterest_tag$POSTFIX \
  --relationships:isLocatedIn person_isLocatedIn_place$POSTFIX \
  --relationships:knows person_knows_person$POSTFIX \
  --relationships:likes person_likes_comment$POSTFIX \
  --relationships:likes person_likes_post$POSTFIX \
  --relationships:isPartOf place_isPartOf_place$POSTFIX \
  --relationships:hasCreator post_hasCreator_person$POSTFIX \
  --relationships:hasTag comment_hasTag_tag$POSTFIX \
  --relationships:hasTag post_hasTag_tag$POSTFIX \
  --relationships:isLocatedIn post_isLocatedIn_place$POSTFIX \
  --relationships:isSubclassOf tagclass_isSubclassOf_tagclass$POSTFIX \
  --relationships:hasType tag_hasType_tagclass$POSTFIX \
  --delimiter '|'

  #--relationships:studyAt person_studyAt_organisation$POSTFIX \
  #--relationships:workAt person_workAt_organisation$POSTFIX \
  #--relationships:isLocatedIn organisation_isLocatedIn_place$POSTFIX \
```

## Stuff that we currently ignore

None of the queries need these.

### Node files

* organisation

### Relationship files

* person_studyAt_organisation
* person_workAt_organisation
* organisation_isLocatedIn_place

### Property files

All of them:

* person_speaks_language
* person_email_emailaddress

## Misc

Regex for extracting relationship names (Atom):

`(\w+)_(\w+)_(\w+)` -> `--relationship:$2 $1_$2_$3`
