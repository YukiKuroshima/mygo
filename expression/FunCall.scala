package expression

import context._
import value._

case class FunCall(val operator: Identifier, val operands: List[Expression]) extends Expression {
  def execute(env: Environment): Value = {
    val arguments = operands.map( _.execute(env) )
    alu.execute(operator, arguments)
    /*
     * TODO: come up with a way to see if operator is lambda or not
     * operator.apply()
     */
  }
}