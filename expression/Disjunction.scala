package expression

import context._
import value._

// ||
class Disjunction(operands: List[Expression]) extends SpecialForm {
  def execute(env: Environment): Value = {
    def helper(ops: List[Expression]): Value = {
      if (ops == Nil) Boole(false)
      // if true
      else if (ops.head.execute(env).asInstanceOf[Boole].value) Boole(true)
      // false -> check next op
      else helper(ops.tail)
    }
    helper(operands)
  }
}
object Disjunction {
  def apply(operands: List[Expression]) = {
    if (operands.size < 2) throw new Exception("|| requires at least two inputs")
    else new Disjunction(operands)
  }
}