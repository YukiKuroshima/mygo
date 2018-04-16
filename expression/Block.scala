package expression

import context._
import value._

class Block(val expressions: List[Expression]) extends SpecialForm {
  def execute(env: Environment): Value = {
    expressions.map(_.execute(env)).last
  }
}