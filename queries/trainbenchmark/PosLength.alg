Distinct([segment, segment.length AS length])
 |
Filter(segment.length <= 0)
 |
GetVertices(vertexVar=segment:Segment)
