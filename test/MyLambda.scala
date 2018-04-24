package test

import context._

object MyLambda extends App {
  val myCon = console
  /*
  TestRunner.run(myCon.execute("def cube = lambda (x) x * x * x"), "ok")
  TestRunner.run(myCon.execute("cube(3 + 2)"), "125")
  TestRunner.run(myCon.execute("def f2c = lambda (ft) {def c = 0.55; c * (ft - 32)}"), "ok")
  // TestRunner.run(myCon.execute("f2c(212)"), "100.0")
  // TestRunner.run(myCon.execute("f2c(98.6)"), "37.0")
  // TestRunner.run(myCon.execute("c"), "Undefined identifier: c")
  
  
  TestRunner.run(myCon.execute("def timesN = lambda(n) lambda (x) n * x"), "ok")
  TestRunner.run(myCon.execute("def times5 = timesN(5)"), "ok")
  TestRunner.run(myCon.execute("times5(3 + 2)"), "25")
  TestRunner.run(myCon.execute("def compose = lambda(f, g) lambda (x) f(g(x))"), "ok")
  TestRunner.run(myCon.execute("def twiceCube = compose(lambda(x) 2 * x, cube)"), "ok")

  TestRunner.run(myCon.execute("twiceCube(10)"), "2000")
  // TestRunner.run(myCon.execute("twiceCube(5)"), "250")
  
  */

  // TestRunner.run(myCon.execute("def fact = lambda(n) if (n == 0) 1 else n * fact(n - 1)"), "ok")
  // TestRunner.run(myCon.execute("fact(1)"), "ok")
  TestRunner.run(myCon.execute("{write(1); write(2); write(3); 1 + 2 + 3}"), "ok")
}