import org.specs2.mutable.Specification
import org.specs2.ScalaCheck
import org.scalacheck.Prop._

import org.scalacheck._

class ComplexGeneratorSpec extends Specification with ScalaCheck {


  val validNumers =
    for (n <- Gen.choose(Integer.MIN_VALUE + 1, Integer.MAX_VALUE)) yield n
  val validDenoms =
    for (d <- validNumers if d != 0) yield d

  "fractions" should {
    "be the correct sign" >> {
      forAll (validNumers, validDenoms) { (n: Int, d: Int) =>
        (d != 0 && d != Integer.MIN_VALUE
          && n != Integer.MIN_VALUE) ==> {
          val f = new Fraction(n, d)

          if (n < 0 && d < 0 || n > 0 && d > 0)
            f.numer must be_>(0)
          else if (n != 0)
            f.numer must be_<(0)
          else
            f.numer must_==  0

          f.denom must be_>(0)
        }
      }

    }
  }

}

class Fraction(n: Int, d: Int) {

  require(d != 0)
  require(d != Integer.MIN_VALUE)
  require(n != Integer.MIN_VALUE)

  val numer = if (d < 0) -1 * n else n
  val denom = d.abs

  override def toString = numer + " / " + denom
}