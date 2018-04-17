package expression

import context._
import value._

case class Lambda(val body: Expression, val parameters: List[Identifier]) extends SpecialForm {
  def execute(env: Environment): Value = {
    new Closure(env, body, parameters)
  }
}