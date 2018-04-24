package expression

import context._
import value._


class Declaration(val identifier: Identifier, val expression: Expression) extends SpecialForm {
  def execute(env: Environment): Value = {
    env(identifier) = expression.execute(env)

    /*
    println("Declaration " + env.size) 
    env foreach (x => println (x._1 + "-->" + x._2))
    */

    Notification.OK
  }
}

object Declaration {
  def apply(identifier: Identifier, expression: Expression) = new Declaration(identifier, expression)
}