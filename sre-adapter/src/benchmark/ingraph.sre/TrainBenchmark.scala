package ingraph.sre

import ingraph.sre.plan.{PropertyConstraintBinding, VertexConstraintBinding}

object PosLength {

  val segment = Variable.createVar("segment")
  val segmentKey = Variable.createConst("length")
  val length = Variable.createVar("segment.length")

  val (all, bound) = {
    Lists.newArrayList(
      VertexConstraintBinding.create(segment)
      PropertyConstraintBinding.create(segment, segmentKey, length)
    )

  }
}

class TrainBenchmark {}
