package test

import expression._
import context._
import value._


object FunCallTest extends App {
  val globalEnvironment = new Environment
  val operands = List(Integer(6), Integer(7))
  val operands2 = List(Integer(10), Integer(7))
  val operands3 = List(Integer(10), Integer(7), Integer(9))
  val operands5 = List(Integer(7), Integer(7))

  var exp = FunCall(Identifier("add"), operands)
  println(exp.execute(globalEnvironment))
  TestRunner.run(exp.execute(globalEnvironment), "13")

  exp = FunCall(Identifier("add"), operands3)
  println(exp.execute(globalEnvironment))

  exp = FunCall(Identifier("sub"), operands)
  println(exp.execute(globalEnvironment))
  exp = FunCall(Identifier("sub"), operands3)
  println(exp.execute(globalEnvironment))
  exp = FunCall(Identifier("sub"), operands2)
  println(exp.execute(globalEnvironment))

  exp = FunCall(Identifier("div"), List(Integer(4), Integer(2)))
  TestRunner.run(exp.execute(globalEnvironment), "2")
  exp = FunCall(Identifier("div"), List(Integer(4), Integer(3)))
  TestRunner.run(exp.execute(globalEnvironment), "1")
  exp = FunCall(Identifier("div"), List(Real(4), Real(3)))
  TestRunner.run(exp.execute(globalEnvironment), (4.0/3).toString)

  exp = FunCall(Identifier("mul"), operands3)
  println(exp.execute(globalEnvironment))

  val operands4 = List(Integer(6), Integer(7), exp)
  exp = FunCall(Identifier("mul"), operands4)
  println(exp.execute(globalEnvironment))
  
  val strOperands = List(Chars("aa"), Chars("aa"))
  val strOperands2 = List(Chars("ab"), Chars("aa"))

  exp = FunCall(Identifier("less"), operands)
  println(exp.execute(globalEnvironment))
  exp = FunCall(Identifier("less"), operands2)
  println(exp.execute(globalEnvironment))

  exp = FunCall(Identifier("less"), strOperands)
  TestRunner.run(exp.execute(globalEnvironment), "false")

  exp = FunCall(Identifier("less"), strOperands2)
  TestRunner.run(exp.execute(globalEnvironment), "false")

  exp = FunCall(Identifier("more"), operands)
  TestRunner.run(exp.execute(globalEnvironment), "false")

  exp = FunCall(Identifier("more"), operands2)
  TestRunner.run(exp.execute(globalEnvironment), "true")

  exp = FunCall(Identifier("more"), strOperands)
  TestRunner.run(exp.execute(globalEnvironment), "false")

  exp = FunCall(Identifier("more"), strOperands2)
  TestRunner.run(exp.execute(globalEnvironment), "true")

  exp = FunCall(Identifier("equals"), operands)
  TestRunner.run(exp.execute(globalEnvironment), "false")
  exp = FunCall(Identifier("equals"), operands5)
  TestRunner.run(exp.execute(globalEnvironment), "true")

  exp = FunCall(Identifier("equals"), strOperands)
  TestRunner.run(exp.execute(globalEnvironment), "true")
  exp = FunCall(Identifier("equals"), strOperands2)
  TestRunner.run(exp.execute(globalEnvironment), "false")

  /*
  val operands6 = List(Integer(7), Boole(true))

  exp = FunCall(Identifier("equals"), operands6)
  TestRunner.run(exp.execute(globalEnvironment), "false")
  */

  val falseOperands = List(Boole(false))
  val trueOperands = List(Boole(true))

  exp = FunCall(Identifier("not"), falseOperands)
  TestRunner.run(exp.execute(globalEnvironment), "true")
  exp = FunCall(Identifier("not"), trueOperands)
  TestRunner.run(exp.execute(globalEnvironment), "false")

  exp = FunCall(Identifier("unequals"), operands)
  TestRunner.run(exp.execute(globalEnvironment), "true")
  exp = FunCall(Identifier("unequals"), operands5)
  TestRunner.run(exp.execute(globalEnvironment), "false")

  exp = FunCall(Identifier("unequals"), strOperands)
  TestRunner.run(exp.execute(globalEnvironment), "false")
  exp = FunCall(Identifier("unequals"), strOperands2)
  TestRunner.run(exp.execute(globalEnvironment), "true")
}