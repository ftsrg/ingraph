import hu.bme.mit.incqueryds.trainbenchmark.PosLength
import hu.bme.mit.incqueryds.{HashJoiner, RDFReader}
import ingraph.trainbenchmark.TrainBenchmarkUtil

object Wrapper {
  TrainBenchmarkUtil.posLength()
  new PosLength()
}