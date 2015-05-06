/**
 * Created by Maginecz on 4/28/2015.
 */
object utils {
  def nop(id:String, b:AnyRef) = {}
  def idStringToLong(in: String): Long = {
    in.drop(1).toLong
  }
}
