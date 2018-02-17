package ldbc

import ingraph.bulkloader.csv.loader.LdbcUpdateStreamCsvLoader
import org.scalatest.FunSuite

import scala.collection.JavaConverters._

class LdbcUpdateLoaderTest extends FunSuite {

  implicit def longs(x: Any): Long = x.asInstanceOf[Long]
  implicit def string(x: Any): String = x.asInstanceOf[String]
  implicit def int(x: Any): Int = x.asInstanceOf[Int]
  implicit def longList(x: Any): List[Long] = x.asInstanceOf[java.util.List[Object]].asScala.map(_.asInstanceOf[Long]).toList
  implicit def stringList(x: Any): List[String] = x.asInstanceOf[java.util.List[Object]].asScala.map(_.asInstanceOf[String]).toList

  val CSV_DIR: String = "../graphs/ldbc-snb-bi/update-streams/"

  test("parse streams") {
    val loader = new LdbcUpdateStreamCsvLoader(CSV_DIR)

    val updates: Iterable[LdbcUpdate] = for (update <- loader.getUpdates.asScala) yield {
      val u = update.asScala
      val updateType = u(2).asInstanceOf[Int]

      updateType match {
        case 1 => Update1AddPerson         (u(3), u(4), u(5), u(6), u(7), u(8), u(9), u(10), u(11), u(12), u(13), u(14), null, null)
        case 2 => Update2AddPostLike       (u(3), u(4), u(5))
        case 3 => Update3AddCommentLike    (u(3), u(4), u(5))
        case 4 => Update4AddForum          (u(3), u(4), u(5), u(6), u(7))
        case 5 => Update5AddForumMembership(u(3), u(4), u(5))
        case 6 => Update6AddPost           (u(3), u(4), u(5), u(6), u(7), u(8), u(9), u(10), u(11), u(12), u(13), u(14))
        case 7 => Update7AddComment        (u(3), u(4), u(5), u(6), u(7), u(8), u(9), u(10), u(11), u(12), u(13))
        case 8 => Update8AddFriendship     (u(3), u(4), u(5))
      }
    }

  }
}
