DOCUMENT=opencypher-report
#MODE=-interaction=batchmode
MODE=

all: clean
	git log --oneline -n 1 | sed "s/#/\\\\#/g" | sed "s/\`/'/g" | sed "s|_|\\\\_|" > commit-message.tex
	xelatex $(MODE) $(DOCUMENT)
#	xelatex $(MODE) $(DOCUMENT)
	bibtex $(DOCUMENT)
#	xelatex $(MODE) $(DOCUMENT)

clean:
	echo Cleaning temporary files and PDF...
	rm -f *.aux *.dvi *.thm *.lof *.log *.lot *.fls *.out *.toc *.bbl *.blg *.pdf
