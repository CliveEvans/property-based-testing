package test

import org.specs2.mutable.Specification
import org.scalacheck.{Prop, Gen}
import org.scalacheck.Prop._
import org.specs2.matcher.MatchResult
import org.specs2.ScalaCheck

class GeneratorSpec extends Specification with ScalaCheck {

  val smallIntegers = Gen.choose(0, 500)

  val smallEvenInteger = Gen.choose(0,200) suchThat (_ % 2 == 0)

  "simple maths rules" should {
    "work for small integers" in {
      forAll(smallIntegers) { i =>
        validateSimpleMathsRules(i)
      }
    }
    "work for small even integers" in {
      forAll(smallEvenInteger) { i =>
        validateSimpleMathsRules(i)
      }
    }
  }


  def validateSimpleMathsRules(i: Int): MatchResult[Any] = {
    i + i must_== 2 * i
    i - i must_== 0
  }

  val vowels = Gen.oneOf("A", "E", "I", "O", "U", "Y")

  "vowels" should {
    "follow normal string concatenation rule" >> {
      forAll(vowels, vowels) { (a:String,b:String) =>
        verifyStringConcatenationWorksFor(a, b)
        verifyStringConcatenationWorksFor(b, a)
      }
    }
  }


  def verifyStringConcatenationWorksFor(a: String, b: String): MatchResult[Any] = {
    val c = a + b
    c.size must_== a.size + b.size
    c must startWith(a)
    c must endWith(b)
  }

  val distributedVowels = Gen.frequency(
    (3, "A"),
    (4, "E"),
    (2, "I"),
    (3, "O"),
    (1, "U"),
    (1, "Y")
  )
  "distributed vowels" should {
    "follow normal string concatenation rule" >> {
      forAll(distributedVowels, distributedVowels) { (a:String,b:String) =>
        verifyStringConcatenationWorksFor(a, b)
        verifyStringConcatenationWorksFor(b, a)
      }
    }
  }
}
