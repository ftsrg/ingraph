package ldbc

import ingraph.bulkloader.csv.loader.LdbcUpdateStreamCsvLoader
import org.scalatest.FunSuite

import scala.collection.JavaConverters._

class LdbcUpdateLoaderTest extends FunSuite {

//  implicit def longs(x: Any): Long = x.asInstanceOf[Long]
//  implicit def string(x: Any): String = x.asInstanceOf[String]
//  implicit def int(x: Any): Int = x.asInstanceOf[Int]

  val CSV_DIR: String = "../graphs/ldbc-snb-bi/update-streams/"

  test("parse streams") {
    val loader = new LdbcUpdateStreamCsvLoader(CSV_DIR)

    val updates: Iterable[LdbcUpdate] = for (update <- loader.getUpdates.asScala) yield {
      val u = update.asScala
      val updateType = u(2).asInstanceOf[Int]

      import scala.language.implicitConversions

      updateType match {
        case 1 => Update1AddPerson(
          u(3).asInstanceOf[Long],
          u(4).asInstanceOf[String],
          u(5).asInstanceOf[String],
          u(6).asInstanceOf[String],
          u(7).asInstanceOf[Long],
          u(8).asInstanceOf[Long],
          u(9).asInstanceOf[String],
          u(10).asInstanceOf[String],
          u(11).asInstanceOf[Long],
          u(12).asInstanceOf[java.util.List[Object]].asScala.map(_.asInstanceOf[String]).toList,
          u(13).asInstanceOf[java.util.List[Object]].asScala.map(_.asInstanceOf[String]).toList,
          u(14).asInstanceOf[java.util.List[Object]].asScala.map(_.asInstanceOf[Long]).toList,
          null,
          null
        )
        case 2 => Update2AddPostLike(
          u(3).asInstanceOf[Long],
          u(4).asInstanceOf[Long],
          u(5).asInstanceOf[Long]
        )
        case 3 => Update3AddCommentLike(
          u(3).asInstanceOf[Long],
          u(4).asInstanceOf[Long],
          u(5).asInstanceOf[Long]
        )
        case 4 => Update4AddForum(
          u(3).asInstanceOf[Long],
          u(4).asInstanceOf[String],
          u(5).asInstanceOf[Long],
          u(6).asInstanceOf[Long],
          u(7).asInstanceOf[java.util.List[Object]].asScala.map(_.asInstanceOf[Long]).toList
        )
        case 5 => Update5AddForumMembership(
          u(3).asInstanceOf[Long],
          u(4).asInstanceOf[Long],
          u(5).asInstanceOf[Long]
        )
        case 6 => Update6AddPost(
          u(3).asInstanceOf[Long],
          u(4).asInstanceOf[String],
          u(5).asInstanceOf[Long],
          u(6).asInstanceOf[String],
          u(7).asInstanceOf[String],
          u(8).asInstanceOf[String],
          u(9).asInstanceOf[String],
          u(10).asInstanceOf[Int],
          u(11).asInstanceOf[Long],
          u(12).asInstanceOf[Long],
          u(13).asInstanceOf[Long],
          u(14).asInstanceOf[java.util.List[Object]].asScala.map(_.asInstanceOf[Long]).toList
        )
        case 7 => Update7AddComment(
          u(3).asInstanceOf[Long],
          u(4).asInstanceOf[Long],
          u(5).asInstanceOf[String],
          u(6).asInstanceOf[String],
          u(7).asInstanceOf[String],
          u(8).asInstanceOf[Int],
          u(9).asInstanceOf[Long],
          u(10).asInstanceOf[Long],
          u(11).asInstanceOf[Long],
          u(12).asInstanceOf[Long],
          u(13).asInstanceOf[java.util.List[Object]].asScala.map(_.asInstanceOf[Long]).toList
        )
        case 8 => Update8AddFriendship(
          u(3).asInstanceOf[Long],
          u(4).asInstanceOf[Long],
          u(5).asInstanceOf[Long]
        )
      }
    }

  }
}
