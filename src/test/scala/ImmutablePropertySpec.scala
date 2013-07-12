import org.specs2._

object FragmentsSpec extends Specification with ScalaCheck {

  def multiply(a: Int, b: Int): Int = a * b

  def is =
    "addition and multiplication are related" ! prop {(a: Int) => a + a must_== 2 * a} ^
      "multiply by two is a + a" ! prop {(a: Int) => multiply(a, 2) must_== a + a} ^
      "string contatenation must create a string containing both strings" ! prop {(a: String, b: String) =>
        val c = a + b
        c.length must_== a.length + b.length
        c must startWith(a)
        c must endWith(b)
      } ^
      //"a must be greater than b" ! prop {(a: Int, b: Int) => a must be_>=(b)} ^
      end
}

object SnippetSpec extends Specification with ScalaCheck {
  def is = s2"""
    addition and multiplication are related ${ prop {(a : Int) => a + a must_== 2 * a }}

  """
}
