DOCUMENT=opencypher-report

all: clean
	git rev-parse --short HEAD > commit-id.tex
	latexmk -xelatex -quiet $(DOCUMENT)

clean:
	echo Cleaning temporary files and PDF...
	rm -f *.aux *.dvi *.thm *.lof *.log *.lot *.fls *.out *.toc *.bbl *.blg *.pdf
