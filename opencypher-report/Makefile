DOCUMENT=opencypher-report
COMPILER=lualatex -interaction=batchmode

all: clean
	git log --oneline -n 1 | sed -e "s|[^A-Za-z0-9#,;'/&_\`\.\" ]||g" | sed "s/#/\\\\#/g" | sed "s/\`/'/g" | sed "s|_|\\\\_|g" > commit-message.tex
	$(COMPILER) $(DOCUMENT)
	bibtex $(DOCUMENT) > /dev/null
	$(COMPILER) $(DOCUMENT)
	$(COMPILER) $(DOCUMENT)
	echo

clean:
	echo Cleaning temporary files and PDF...
	rm -f *.aux *.dvi *.thm *.lof *.log *.lot *.fls *.out *.toc *.bbl *.blg *.pdf
