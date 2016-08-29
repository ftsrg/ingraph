COMPILER=xelatex -interaction=batchmode

DOCUMENTS = \
  connectedsegments \
	poslength \
	routesensor \
	semaphoreneighbor \
	switchmonitored \
	switchset

all:
	@$(foreach DOCUMENT, $(DOCUMENTS),\
		echo ============================== $(DOCUMENT) ==============================; \
		$(COMPILER) $(DOCUMENT).tex; \
		echo; \
	)
