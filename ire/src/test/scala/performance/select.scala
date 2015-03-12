package performance

/**
 * Created by janosmaginecz on 12/03/15.
 */
import org.scalatest.FlatSpec

import scala.collection.mutable.Stack

class StackSpec extends FlatSpec {


  "A Stack" should "pop values in last-in-first-out order" in {
  val stack = new Stack[Int]
  stack.push(1)
  stack.push(2)
  assert(stack.pop() === 2)
  assert(stack.pop() === 1)
  }

  it should "throw NoSuchElementException if an empty stack is popped" in {
  val emptyStack = new Stack[String]
  intercept[NoSuchElementException] {
    emptyStack.pop()
  }
  }
}
