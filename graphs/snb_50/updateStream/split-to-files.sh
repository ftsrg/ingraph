#!/bin/bash

declare -A filenames

filenames["1-add-person"]=updateStream_0_0_person.csv
filenames["2-add-post-like"]=updateStream_0_0_forum.csv
filenames["3-add-comment-like"]=updateStream_0_0_forum.csv
filenames["4-add-forum"]=updateStream_0_0_forum.csv
filenames["5-add-forum-membership"]=updateStream_0_0_forum.csv
filenames["6-add-post"]=updateStream_0_0_forum.csv
filenames["7-add-comment"]=updateStream_0_0_forum.csv
filenames["8-add-friendship"]=updateStream_0_0_forum.csv

for target in ${!filenames[@]}; do
  source=${filenames[$target]}
  index=${target:0:1}
  echo "Splitting $source to $target.csv, using index $index"
  grep -E "^\w+\|\w+\|$index" $source > $target.csv
done
