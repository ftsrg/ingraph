Distinct([segment, segment.length AS length]) # TODO: what does it mean to return a complete node/edge?
 |
Filter(segment.length <= 0)
 |
GetVertices(vertexVar=segment:Segment)
