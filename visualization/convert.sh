#!/bin/bash

echo > index.html
for doc in *.tex; do
  filename="${doc%.*}"
  echo $filename
  pdflatex -shell-escape -interaction=batchmode $filename.tex 1>/dev/null
  echo "<h1>$filename</h1>" >> index.html
  echo "<img src='$filename.png' />" >> index.html;
done
