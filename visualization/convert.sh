#!/bin/bash

echo > index.html
for doc in *.tex; do
	filename="${doc%.*}"
  echo ========================================
  xelatex -shell-escape -interaction=batchmode $filename.tex
  echo "<h1>$filename</h1>" >> index.html
	echo "<img src='$filename.png' />" >> index.html;
done
