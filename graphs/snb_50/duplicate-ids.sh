#!/bin/bash

# duplicate ids
# only run once
files=( comment forum person place post tagclass tag)
for file in "${files[@]}"
do
   echo $file$POSTFIX
   sed -i "s/^\([0-9]\+\)|/\1|\1|/" $file$POSTFIX
done
