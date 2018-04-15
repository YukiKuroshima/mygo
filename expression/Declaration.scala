package expression

import context._
import value._


class Declaration(val identifier: Identifier, val expression: Expression) extends SpecialForm {
  def execute(env: Environment): Value = {
    env(identifier) = expression.execute(env)
    Notification.OK
  }
}

object Declaration {
  def apply(identifier: Identifier, expression: Expression) = new Declaration(identifier, expression)
}