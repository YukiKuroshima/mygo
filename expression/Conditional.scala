package expression

import context._
import value._

class Conditional(
    val condition: Expression, 
    val consequent: Expression, 
    val alternative: Expression
    ) extends SpecialForm {

  def execute(env: Environment): Value = {
    if (condition.execute(env).asInstanceOf[Boole].value) consequent.execute(env)
    else if (alternative != null) alternative.execute(env)
    else Notification.UNSPECIFIED
  }
  
}
object Conditional {
  def apply(condition: Expression, consequent: Expression, alternative: Expression = null) = new Conditional(condition, consequent, alternative)
}