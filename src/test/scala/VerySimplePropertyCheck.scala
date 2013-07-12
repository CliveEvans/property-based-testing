package test

import org.specs2.mutable.Specification
import org.scalacheck.Prop.forAll
import org.specs2.ScalaCheck

class VerySimplePropertySpec extends Specification with ScalaCheck {
  "the property test" should {

    "prove addition and multiplication are related" in {
      forAll{(a: Int) => a+a must_== 2*a}
    }

    "demonstrate a failing check" in  {
      forAll {(a: Int, b: Int) => a must be_>=(b)}
    }

  }

  "String concatenation" should {
    "add the strings in order" in {
      forAll { (a: String, b: String) =>
        val c = a + b
        c.length must_== a.length + b.length
        c must startWith(a)
        c must endWith(b)
      }
    }
  }
}

