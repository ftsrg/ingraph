#!/bin/bash

cat template-header.html > index.html
for doc in *.tex; do
  filename="${doc%.*}"
  texfile="$filename.tex"
  pdffile="$filename.pdf"

  # only recompile files where the tex source is newer than the generated pdf
  #echo Comparing the timestamp of $texfile and $pdffile
  if [ $texfile -nt $pdffile ]; then
    echo Compiling $texfile
    pdflatex -shell-escape -interaction=batchmode $filename.tex 1>/dev/null
  fi
  echo "      <h1 id='$filename'><a href='#$filename'>$filename</a></h1>" >> index.html
  echo "      <img src='$filename.png' />" >> index.html;
done
cat template-footer.html >> index.html
