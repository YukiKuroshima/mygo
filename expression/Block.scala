package expression

import context._
import value._

case class Block(val expressions: List[Expression]) extends SpecialForm {
  def execute(env: Environment): Value = {
    val tempEnv = new Environment(env)
    // expressions.map(_.execute(env))
    // print("Block") 
    // tempEnv foreach (x => println (x._1 + "-->" + x._2))
    expressions.map(_.execute(tempEnv)).last
  }
}