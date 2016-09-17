#!/bin/bash

cat template-header.html > index.html
echo > body.html

echo "<ul>" >> index.html
for doc in *.tex; do
  filename="${doc%.*}"
  texfile="$filename.tex"
  pdffile="$filename.pdf"

  # only recompile files where the tex source is newer than the generated pdf
  if [ $texfile -nt $pdffile ]; then
    echo Compiling $texfile
    pdflatex -shell-escape -interaction=batchmode $filename.tex 1>/dev/null
  fi

  # toc
  echo "  <li><a href='#$filename'>$filename</a></li>" >> index.html

  # content
  echo "      <h1 id='$filename'><a href='#$filename'>$filename</a></h1>" >> body.html
  echo "      <img src='$filename.png' />" >> body.html
done
echo "</ul>" >> index.html

cat body.html >> index.html
cat template-footer.html >> index.html
