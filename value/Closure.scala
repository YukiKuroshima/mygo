package value

import expression._
import context._

class Closure(
    val defEnv: Environment, 
    val body: Expression, 
    val parameters: List[Identifier]) extends Value {

  def apply(args: List[Value]): Value = {

    // println("Closure defEnv " + defEnv.size) 
    // defEnv foreach (x => println (x._1 + "-->" + x._2))

    val tempEnv = new Environment(defEnv)
    // println("Closure before bulk tempEnv " + tempEnv.size) 
    // tempEnv foreach (x => println (x._1 + "-->" + x._2))

    // binds its parameters to its arguments
    tempEnv.bulkPut(parameters, args)

    // println("Closure tempEnv " + tempEnv.size) 
    // tempEnv foreach (x => println (x._1 + "-->" + x._2))

    body.execute(tempEnv)
  }
  /*
  def apply(args: List[Value]): Value = {
    val tempEnv = new Environment(defEnv)

    // make sure the lists have the same size
    if (args.size != parameters.size) 
      throw new TypeException("arguments size (" + args.size + ") is different from parameter size (" + parameters.size + ")")
    // binds its parameters to its arguments
    tempEnv.bulkPut(parameters, args)

    println("Closure " + tempEnv.size) 
    tempEnv foreach (x => println (x._1 + "-->" + x._2))

    body.execute(tempEnv)
  }
  * 
  */
}