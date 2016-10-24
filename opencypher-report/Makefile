DOCUMENT=opencypher-report

all: clean
	pdflatex $(DOCUMENT)
	bibtex $(DOCUMENT) || true
	pdflatex $(DOCUMENT)
	pdflatex $(DOCUMENT)

clean:
	echo Cleaning temporary files and PDF...
	rm -f *.aux *.dvi *.thm *.lof *.log *.lot *.fls *.out *.toc *.bbl *.blg *.pdf
