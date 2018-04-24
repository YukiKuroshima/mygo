package expression

import context._
import value._

case class FunCall(val operator: Identifier, val operands: List[Expression]) extends Expression {
  def execute(env: Environment): Value = {
    val arguments = operands.map( _.execute(env) )
    
    /*
    println("FunCall: " + operator.toString())
    println("env size: " + env.size)
    env foreach (x => println (x._1 + "-->" + x._2))
    */

    /*
    println("classname: " + env(operator).getClass.getName)
    println("closure?: " + env(operator).isInstanceOf[Closure])
    * 
    */
    // 1 way
    /*
    try {
      val maybeClosreu = operator.execute(env)
      if maybeCloser not a closure throw type exception
      else maybeClosure(args)
    } catch {
      case e: undefinined => alu.execute(operator, args)
    }
    * 
    */
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

    /*
    try {
      env.apply(operator).asInstanceOf[Closure].apply(arguments)
    } catch {
      case e: UndefinedException => alu.execute(operator, arguments)
    }
    */
    // this is bug
    // use env.apply() == null
    /*
    if (!env.contains(operator)) alu.execute(operator, arguments)
    else env(operator).asInstanceOf[Closure].apply(arguments)
    * 
    */
    // else env(operator).asInstanceOf[Closure].defEnv
    /*
    if (env(operator).isInstanceOf[Closure]) env(operator).asInstanceOf[Closure].apply(arguments)
    else alu.execute(operator, arguments)
    * 
    */
  }
}