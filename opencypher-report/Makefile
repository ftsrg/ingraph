DOCUMENT=opencypher-report

all: clean
	git log --oneline -n 1 > commit-message.tex
	latexmk -xelatex -quiet $(DOCUMENT)

clean:
	echo Cleaning temporary files and PDF...
	rm -f *.aux *.dvi *.thm *.lof *.log *.lot *.fls *.out *.toc *.bbl *.blg *.pdf
