DOCUMENT=opencypher-report

all: clean
	latexmk -xelatex -quiet $(DOCUMENT)

clean:
	echo Cleaning temporary files and PDF...
	rm -f *.aux *.dvi *.thm *.lof *.log *.lot *.fls *.out *.toc *.bbl *.blg *.pdf
