package expression

import context._
import value._

// &&
class Conjunction(operands: List[Expression]) extends Expression {
  def execute(env: Environment): Value = {
    def helper(ops: List[Expression]): Value = {
      if (ops == Nil) Boole(true)
      // if false
      else if (!ops.head.execute(env).asInstanceOf[Boole].value) Boole(false)
      // true -> check next op
      else helper(ops.tail)
    }
    helper(operands)
  }
}

object Conjunction {
  def apply(operands: List[Expression]) = {
    if (operands.size < 2) throw new Exception("&& requires at least two inputs")
    else new Conjunction(operands)
  }
}