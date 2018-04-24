package expression

import context._
import value._

case class FunCall(val operator: Identifier, val operands: List[Expression]) extends Expression {
  def execute(env: Environment): Value = {
    val arguments = operands.map( _.execute(env) )
    try {
      val maybeClosure = operator.execute(env)
      if (!maybeClosure.isInstanceOf[Closure]) throw new TypeException
      maybeClosure.asInstanceOf[Closure](arguments) // = closure.apply(args)
    } catch {
      case e: UndefinedException => alu.execute(operator, arguments) // = closure.apply(args)
    }
    // 2 way override contains
    /*
    if (env.contains(operator)) {
      val maybeClosure = operator.execute(env)
      if (maybeClosure.isInstanceOf[Closure]) throw new TypeException
      else maybeClosure.asInstanceOf[Closure](arguments)
    } else {
      alu.execute(operator, arguments)
    }
    * 
    */
  }
}