package sre.task

import org.scalatest.FunSpec

class KleeneSpec extends FunSpec {
  describe("TriValue") {
    it("should work for !=") {
      assert((Kleene.True != Kleene.True).isFalse)
      assert((Kleene.True != Kleene.Null).isNull)
      assert((Kleene.True != Kleene.False).isTrue)
      assert((Kleene.Null != Kleene.Null).isNull)
      assert((Kleene.Null != Kleene.False).isNull)
      assert((Kleene.False != Kleene.False).isFalse)
    }
    it("should work for ==") {
      assert((Kleene.False == Kleene.True).isFalse)
      assert((Kleene.False == Kleene.Null).isNull)
      assert((Kleene.False == Kleene.False).isTrue)
      assert((Kleene.Null == Kleene.Null).isNull)
      assert((Kleene.Null == Kleene.False).isNull)
      assert((Kleene.True == Kleene.False).isFalse)
    }
    it("should work for &&") {
      assert((Kleene.True && Kleene.True).isTrue)
      assert((Kleene.True && Kleene.Null).isNull)
      assert((Kleene.True && Kleene.False).isFalse)
      assert((Kleene.Null && Kleene.Null).isNull)
      assert((Kleene.Null && Kleene.False).isFalse)
      assert((Kleene.False && Kleene.Null).isFalse)
      assert((Kleene.False && Kleene.False).isFalse)
    }
    it("should work for ||") {
      assert((Kleene.True || Kleene.True).isTrue)
      assert((Kleene.True || Kleene.Null).isTrue)
      assert((Kleene.True || Kleene.False).isTrue)
      assert((Kleene.Null || Kleene.Null).isNull)
      assert((Kleene.Null || Kleene.False).isNull)
      assert((Kleene.False || Kleene.Null).isNull)
      assert((Kleene.False || Kleene.False).isFalse)
    }
  }
}
