import scala.util.Random

/**
 * Created by janosmaginecz on 12/03/15.
 */
object container_performance {

  def time[R](block: => R): R = {
    val t0 = System.nanoTime()
    val result = block // call-by-name
    val t1 = System.nanoTime()
    println("Elapsed time: " + (t1 - t0) + "ns")
    result
  }

  def main(args: Array[String]) {
    val size = 1000000
    val tuples = Stream.continually( (Random.nextLong, Random.nextLong, Random.nextLong, Random.nextLong) ).take(size)
    val vectors = Stream.continually(Vector(Random.nextLong, Random.nextLong, Random.nextLong, Random.nextLong)).take(size)

    val selectionVector = List(0, 2, 3)
    val lists = Stream.continually( List(Random.nextLong, Random.nextLong, Random.nextLong, Random.nextLong) ).take(size)

    def selectTuple = tuples.foreach(tup => (tup._1, tup._3, tup._4))
    def selectVector = vectors.foreach(vec => selectionVector.foreach(i => vec(i)))
    def selectList = lists.foreach(list => selectionVector.foreach(i => list(i)))

    time {
      selectTuple
    }
    time {
      selectVector
    }
    time {
      selectList
    }
  }
}
