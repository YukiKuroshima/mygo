package value

import expression._
import context._

class Closure(
    val defEnv: Environment, 
    val body: Expression, 
    val parameters: List[Identifier]) extends Value {

  def apply(args: List[Value]): Value = {
    val tempEnv = new Environment(defEnv)
    // make sure the lists have the same size
    if (args.size != parameters.size) 
      throw new TypeException("arguments size (" + args.size + ") is different from parameter size (" + parameters.size + ")")
    // binds its parameters to its arguments
    tempEnv.bulkPut(parameters, args)
    body.execute(tempEnv)
  }
}