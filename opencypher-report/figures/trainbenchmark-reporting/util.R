# Sample script for processing and visualizing the benchmark results.
# 
# The script aggregates the data and transforms it to a wide table suited for visualization.
# The aggregration takes the **average** time required by the individual steps in the phases. 
# For multiple runs, the script takes the **minimum** value.
#
# The basic workflow for the script is the following:
# * load the file from the CSV
# * filter the data
# * convert the data from long table to wide table (better suited for processing)
# * aggregate the data
# * convert the data to long table which (better suited for visualization)
# * draw the plots

library(reshape2)
library(plyr)
library(ggplot2)
library(ggrepel)

####################################################################################################

# modelsizes
#modelsize.batch  = data.frame(Scenario = "Batch",  Artifact = 2^(0:14), Triples = c("4.7k", "7.9k", "20.6k", "41k", "89.4k", "191.8k", "374.1k", "716.5k", "1.5M", "2.8M", "5.7M", "11.5M", "23M", "45.9M", "92.3M"))
modelsize.batch  = data.frame(Scenario = "Batch",  Artifact = 2^(0:14), Triples = c("5k", "8k", "21k", "41k", "89k", "192k", "374k", "717k", "1.5M", "2.8M", "5.7M", "11.5M", "23M", "45.9M", "92.3M"))
modelsize.inject = data.frame(Scenario = "Inject", Artifact = 2^(0:14), Triples = c("5k", "9.3k", "19.9k", "44.6k", "85.7k", "191.6k", "373.1k", "752.8k", "1.5M", "3M", "5.8M", "11.6M", "23.3M", "46.5M", "93M"))
modelsize.repair = data.frame(Scenario = "Repair", Artifact = 2^(0:14), Triples = c("4.9k", "9.3k", "19.8k", "44.5k", "85.4k", "191.1k", "372.1k", "750.7k", "1.5M", "2.9M", "5.8M", "11.5M", "23.2M", "46.4M", "92.8M"))
modelsizes = do.call(rbind, list(modelsize.batch, modelsize.inject, modelsize.repair))

####################################################################################################

load = function(results.file, cases) {
  #results.file = "../results/results-mix.csv"
  
  results = read.csv(results.file, header = FALSE)
  colnames(results) = c("Scenario", "Tool", "Run", "Case", "Artifact", "Phase", "Iteration", "Metric", "Value")
  
  # make the phases a factor with a fixed set of values to help dcasting
  # (e.g. Batch measurements do not have Transformation and Recheck attributes which would cause df$Transformation to throw an error)
  results$Phase = factor(results$Phase, levels = c("Read", "Check", "Transformation", "Recheck"))
    
  # order queries according to their complexity
  results$Case = factor(results$Case, levels = cases)

  # replace underscore with space in tool names
  results$Tool = gsub('_', ' ', results$Tool)
  
  return(results)
}

####################################################################################################

process.times = function(results, drop) {
  # filtering for time values
  times = subset(results, Metric == "Time")
  # drop the malformed Iteration attribute
  times = subset(times, select = -c(Metric, Iteration))

  # convert nanoseconds to seconds
  times$Value = times$Value / 10^9
  
  # transform long table to wide table
  times.wide = dcast(times,
                     Scenario + Tool + Run + Case + Artifact ~ Phase,
                     value.var = "Value",
                     drop = drop,
                     fun.aggregate = mean
                    )
  
  # calculate aggregated values
  times.derived = times.wide
  times.derived$Read.and.Check = times.derived$Read + times.derived$Check
  times.derived$Transformation.and.Recheck = times.derived$Transformation + times.derived$Recheck
  
  # summarize for each value (along the **Iteration** attribute) using a columnwise function
  # there might be NAs as some phases (e.g. Read) are not executed repeatedly
  #times.aggregated.iterations = ddply(
    #.data = times.derived,
    #.variables = c("Scenario", "Tool", "Run", "Case", "Artifact"),
    #.fun = colwise(mean, na.rm = TRUE),
    #.progress = "text"
  #)
  
  # drop the **Iteration** attribute
  #times.aggregated.iterations = subset(times.aggregated.iterations, select = -c(Iteration))
  
  # summarize for each value (along the **Run** attribute) using a columnwise function
  times.aggregated.runs = ddply(
    .data = times.derived,
    .variables = c("Scenario", "Tool", "Case", "Artifact"),
    .fun = colwise(median),
    .progress = "text"
  )
  # drop results with less than 5 runs -- only results with 5 runs will have the median Run value of 3.
  # the **Run** attribute
  times.aggregated.finished = times.aggregated.runs[times.aggregated.runs$Run == 3, ]
  times.aggregated.finished = subset(times.aggregated.finished, select=-c(Run))
  
  # melt data to a wide table
  times.plot = melt(
    data = times.aggregated.finished,
    id.vars = c("Scenario", "Tool", "Case", "Artifact"),
    measure.vars = c("Read", "Check", "Read.and.Check", "Transformation", "Recheck", "Transformation.and.Recheck"),
    variable.name = "Phase",
    value.name = "Time"
  )
  
  # remove the . characters from the phasename
  #times.plot$Phase = factor(times.plot$Phase, levels = c("Read", "Transformation", "Check", "Recheck", "Read.and.Check", "Transformation.and.Recheck"))
  times.plot$Phase = gsub('\\.', ' ', times.plot$Phase)
  times.plot$Phase = factor(times.plot$Phase, levels = c("Read", "Transformation", "Check", "Recheck", "Read and Check", "Transformation and Recheck"))
  
  return(times.plot)
}

####################################################################################################

process.memories = function(results) {
  # filter on the MaxMemory metric, throw away unused columns
  memories = subset(results, Metric == "MaxMemory")
  memories = subset(memories, select = -c(Phase, Iteration, Metric))
  memories = unique(memories)
  
  memories = rename(memories, c("Value"="Memory"))
  
  # get the minimum values for the memory consumption
  minimum.memories = ddply(
    .data = memories,
    .variables = c("Scenario", "Tool", "Case", "Artifact", "Run"),
    .fun = colwise(min),
    .progress = "text"
  )
  
  # drop results from less than 5 runs
  #memories.runs = ddply(
  #  .data = minimum.memories,
  #  .variables = c("Scenario", "Tool", "Case", "Artifact", "Memory"),
  #  .fun = colwise(length),
  #  .progress = "text"
  #)
  #memories.finished = subset(memories.runs, Run == 1)
  memories.finished = subset(minimum.memories, select = -c(Run))
  
  # extract the tool names for the plots labels
  toolnames = ddply(
    .data = memories.finished,
    .variables = c("Scenario", "Tool", "Case"),
    .fun = colwise(max),
    .progress = "text"
  )
  list(memories = memories.finished, toolnames = toolnames)
}

####################################################################################################

yaxis = function() {
  # y axis labels
  longticks = c(F, F, F, T, F, F, F, F, T)
  shortticks = 2:10
  range = -6:4
  
  ooms = 10^range
  
  ybreaks = as.vector(shortticks %o% ooms)
  ylabels = as.character(ybreaks * longticks)
  ylabels = gsub("^0$", "", ylabels)
  
  list(ybreaks = ybreaks, ylabels = ylabels)
}

####################################################################################################

benchmark.plot = function(df, scenario, artifacts, title, filename, facet = NULL, scale, metric, toolnames = NULL, facet_cols = 2, legend_cols = 4, width = 210, height = 297) {
  # for multicolumn layouts, we omit every second label on the x axis
  if (facet_cols > 1) {
    evens = c(seq(4, nrow(artifacts), by=2))
    artifacts = artifacts[-evens, ]
  }
  
  # only keep the records for the current scenario
  df = df[df$Scenario == scenario, ]
  
  # x axis labels
  artifacts.scenario = artifacts[artifacts$Scenario == scenario, "Triples"]
  
  xbreaks = artifacts[artifacts$Scenario == scenario, "Artifact"]
  xlabels = paste(xbreaks, "\n", artifacts.scenario, sep = "")
  
  # y axis labels
  yaxis = yaxis()
  ybreaks = yaxis$ybreaks
  ylabels = yaxis$ylabels

  if (metric == "Time") {
    ycaption = "Execution time [s]"
  }  else {
    ycaption = "Memory [MB]"
  }
  
  p = ggplot(df) +
    aes(x = as.factor(Artifact)) +
    labs(title = paste(title, sep = ""), x = "Model size\n#Triples", y = ycaption) +
    geom_point(aes_string(y = metric, col = Tool, shape = Tool), size = 2.0) +
    geom_line(aes_string(y = metric, col = Tool, group = Tool), size = 0.5)

  if (!is.null(toolnames)) {
    p = p + geom_label_repel(data = toolnames, aes_string(y = metric, label = "Tool",  col = "Tool"), size = 1.6, show.legend = F, label.padding = unit(0.12, "lines"))
  }

  p = p +
    scale_shape_manual(values = seq(0,24)) +
    scale_x_discrete(breaks = xbreaks, labels = xlabels) +
    scale_y_log10(breaks = ybreaks, labels = ylabels)

  if (!is.null(facet)) {
    facet = as.formula(paste("~", facet))
    p = p + facet_wrap(facet, ncol = facet_cols, scale = scale)
  }

  p = p +
    theme_bw() +
    theme(legend.key = element_blank(), legend.title = element_blank(), legend.position = "bottom") +
    guides(shape = guide_legend(ncol = legend_cols))
  print(p)

  ggsave(file = paste("../diagrams/", filename, ".pdf", sep = ""), width = width, height = height, units = "mm")
}

####################################################################################################

heatmap = function(df, attributes, map.from = NULL, map.to = NULL, title, filename, width = 210, height = 100, ncol = 3, legend.position = "bottom") {
  df$Artifact = discretize(
    df$Artifact,
    "fixed",
    categories = c(-Inf,16,256,Inf),
    labels = c("small", "medium", "large"))
  
  df$Time = discretize(
    df$Time,
    "fixed",
    categories = c(-Inf,0.2,1,5,Inf),
    labels = c("instantaneous", "fast", "acceptable", "slow"))
  
  if (!is.null(map.from)) {
    attribute = attributes[1];
    df[[attribute]] = mapvalues(df[[attribute]], from = map.from, to = map.to)
  }
  
  frequencies = as.data.frame(table(df[, c("Artifact", "Time", attributes)]))
  total.frequencies = ddply(frequencies, attributes, summarize, Total = sum(Freq))
  frequencies = merge(frequencies, total.frequencies)
  frequencies$Freq = frequencies$Freq / frequencies$Total
  
  p = ggplot(na.omit(frequencies)) +
    geom_tile(aes(x = Artifact, y = Time, fill = Freq)) +
    labs(title = title, x = "Model size", y = "Execution time") +
    scale_fill_gradient(low = "white", high = "darkred")
  
  if (length(attributes) == 1) {
    p = p + facet_wrap(as.formula(paste("~" ,attributes[1])), ncol = ncol)
  } else {
    p = p + facet_grid(as.formula(paste(attributes[1], "~" ,attributes[2])))
  }
  
  p = p +
    theme_bw() +
    theme(
      legend.key = element_blank(), 
      legend.title = element_blank(), 
      legend.position = legend.position,
      axis.text.x = element_text(angle = 90, hjust = 1),
      strip.text.x = element_text(size = 7),
      strip.text.y = element_text(size = 7)
    )
  print(p)
  
  ggsave(file = paste("../diagrams/heatmap-", filename, ".pdf", sep = ""), width = width, height = height, units = "mm")
}

####################################################################################################
