library(data.table)
library(reshape2)
library(plyr)
library(ggplot2)
library(ggrepel)

#options(scipen=999)

tsvs <- list.files("../results/", pattern = "memory-.*\\.csv", full.names = T, recursive = T)
l <- lapply(tsvs, read.csv)
memories <- rbindlist(l)

memories$Description = sapply(
  memories$Description,
  function(i) substr(as.character(i),1,1)
)

workloads = c("SemaphoreNeighbor", "RouteSensor")

# we only keep every second artifact for the x axis
artifacts = c("8k", "15k", "33k", "67k", "136k", "272k", "0.5M", "1.1M", "2.2M", "4.5M", "9.3M", "18.5M") #, "37M")

for (workload in workloads) {
  print(workload)
  df = memories[memories$Workload == workload, ]

  # x axis labels
  xbreaks = unique(df$Model)
  xlabels = paste(xbreaks, "\n", artifacts, sep = "")
  evens = c(seq(1, length(xlabels), by=2))
  xlabels[evens] = ""
  
  # y axis labels
  yaxis = myyaxis()
  ybreaks = yaxis$ybreaks
  ylabels = yaxis$ylabels
  
  p = ggplot(df) +
    aes(x = as.factor(Model), y = Memory) +
    labs(title = workload, x = "Model size\n#Elements", y = "Memory consumption [MB]") +
    geom_point(aes(col = Description, shape = Description), size = 2.0) +
    geom_line(aes(col = Description, group = Description), size = 0.5) +
    scale_x_discrete(breaks = xbreaks, labels = xlabels) +
    scale_y_log10(breaks = ybreaks, labels = ylabels) +
    #facet_wrap(~ Phase, ncol = ncol, scale = "free_y") +
    guides(color = guide_legend(nrow = 1)) +
    theme_bw() +
    theme(
      text = element_text(size = 10),
      legend.key = element_blank(), 
      legend.title = element_blank(), 
      legend.position = "bottom",
      axis.text = element_text(size = 7)
    )
  print(p)
  
  ggsave(plot = p, filename = paste("../diagrams/", "memories-", workload, ".pdf", sep=""), width = 100, height = 100, units = "mm")
}
