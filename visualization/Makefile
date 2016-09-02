COMPILER=xelatex -interaction=batchmode

DOCUMENTS := $(shell find $(SOURCEDIR) -name '*.tex')

all:
	@$(foreach DOCUMENT, $(DOCUMENTS),\
		echo ============================== $(DOCUMENT) ==============================; \
		$(COMPILER) $(DOCUMENT).tex; \
		echo; \
	)
