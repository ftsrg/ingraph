library(data.table)
library(reshape2)
library(plyr)
library(ggplot2)
library(ggrepel)

myyaxis = function() {
  # y axis labels
  longticks = c(T, F, F, T, F, F, F, F, T)
  shortticks = 2:10
  range = -6:4
  
  ooms = 10^range
  
  ybreaks = as.vector(shortticks %o% ooms)
  ylabels = as.character(ybreaks * longticks)
  ylabels = gsub("^0$", "", ylabels)
  
  list(ybreaks = ybreaks, ylabels = ylabels)
}

options(scipen=999)

tsvs <- list.files("../results/", pattern = "times-.*\\.csv", full.names = T, recursive = T)
l <- lapply(tsvs, read.csv)
times <- rbindlist(l)

times$Description = sapply(
  times$Description,
  function(i) substr(as.character(i),1,1)
)

# preprocessing
times$Model = gsub("\\D+", "", times$Model)
times$Model = as.numeric(times$Model)
times$Time = times$Time / 10^6
# make the phases a factor with a fixed set of values to help dcasting
# (e.g. Batch measurements do not have Transformation and Recheck attributes, hence accessing the "Transformation" attribute would throw an error)
times$Phase = factor(times$Phase, levels = c("Read", "Check", "Transformation", "Recheck"))
times = subset(times, Tool == "IncQuery-DS")

times.wide = dcast(times,
                   Tool + Workload + Description + Model + Run ~ Phase,
                   value.var = "Time",
                   drop = F,
                   fun.aggregate = mean
)
# calculate aggregated values
times.derived = times.wide
times.derived$Read.and.Check = times.derived$Read + times.derived$Check
times.derived$Transformation.and.Recheck = times.derived$Transformation + times.derived$Recheck

phases = c("Read", "Check", "Read.and.Check", "Transformation", "Recheck", "Transformation.and.Recheck")

times.aggregated.runs = ddply(
  .data = times.derived,
  .variables = c("Tool", "Workload", "Description", "Model"),
  .fun = colwise(min),
  .progress = "text"
)

times.aggregated.runs

times.plot = melt(
  data = times.aggregated.runs,
  id.vars = c("Tool", "Workload", "Description", "Model", "Run"),
  measure.vars = phases,
  variable.name = "Phase",
  value.name = "Time"
)

#workloads = c("ConnectedSegments", "RouteSensor", "SwitchSet", "SemaphoreNeighbor")
workloads = c("SemaphoreNeighbor", "RouteSensor")

times.plot$Phase = gsub('\\.', ' ', times.plot$Phase)
times.plot$Phase = factor(times.plot$Phase, levels = c("Read", "Check", "Read and Check", "Transformation", "Recheck", "Transformation and Recheck"))
phases = gsub('\\.', ' ', phases)

# we only keep every second artifact for the x axis
artifacts = c("8k", "15k", "33k", "67k", "136k", "272k", "0.5M", "1.1M", "2.2M", "4.5M", "9.3M", "18.5M") #, "37M")

for (workload in workloads) {
  print(workload)
  df = times.plot[times.plot$Workload == workload, ]
  
  # do not visualize empty data sets
  if (nrow(df) == 0) {
    next
  }

  # x axis labels
  xbreaks = unique(df$Model)
  xlabels = paste(xbreaks, "\n", artifacts, sep = "")
  evens = c(seq(1, length(xlabels), by=2))
  xlabels[evens] = ""
  
  # y axis labels
  yaxis = myyaxis()
  ybreaks = yaxis$ybreaks
  ylabels = yaxis$ylabels

  ncol=3
  
  if (workload == "RouteSensor") {
    maxs <- data.frame(
      Model = rep(1,ncol*2), 
      Phase = phases,
      Time = c(rep(100000,ncol), rep(80,ncol))
    )
    mins <- data.frame(
      Model = rep(1,6), 
      Phase = phases,
      Time = c(rep(5,ncol), rep(0.002,ncol))
    )
  }
  if (workload == "SemaphoreNeighbor") {
    maxs <- data.frame(
      Model = rep(1,ncol*2), 
      Phase = phases,
      Time = c(rep(100000,ncol), rep(5,ncol))
    )
    mins <- data.frame(
      Model = rep(1,6), 
      Phase = phases,
      Time = c(rep(10,ncol), rep(0.002,ncol))
    )
  }
  
  p = ggplot(na.omit(df)) +
    aes(x = as.factor(Model), y = Time) +
    labs(title = workload, x = "Model size\n#Elements", y = "Execution times [ms]") +
    geom_point(aes(col = Description, shape = Description), size = 2.0) +
    geom_point(data = mins, color = "transparent") +
    geom_point(data = maxs, color = "transparent") +
    geom_line(aes(col = Description, group = Description), size = 0.5) +
    scale_x_discrete(breaks = xbreaks, labels = xlabels) +
    scale_y_log10(breaks = ybreaks, labels = ylabels) +
    facet_wrap(~ Phase, ncol = ncol, scale = "free_y") +
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
  
  ggsave(plot = p, filename = paste("../diagrams/times-", workload, ".pdf", sep=""))
}
