package hu.bme.mit.oc2sql

import org.cytosm.cypher2sql.PassAvailables
import org.scalatest.FunSuite

class Oc2SqlTest extends FunSuite {

  test("use class") {
    println(PassAvailables.parseCypher("MATCH (n) RETURN (n)"))
  }

}
