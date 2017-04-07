DOCUMENT=opencypher-report
COMPILER=lualatex

all: clean
	git log --oneline -n 1 | sed "s/#/\\\\#/g" | sed "s/\`/'/g" | sed "s|_|\\\\_|" > commit-message.tex
	$(COMPILER) $(DOCUMENT)
	$(COMPILER) $(DOCUMENT)
	bibtex $(DOCUMENT)
	$(COMPILER) $(DOCUMENT)

clean:
	echo Cleaning temporary files and PDF...
	rm -f *.aux *.dvi *.thm *.lof *.log *.lot *.fls *.out *.toc *.bbl *.blg *.pdf
