MATCH (segment:Segment)
WHERE segment.length <= 0
RETURN DISTINCT segment.id, segment.length AS length
