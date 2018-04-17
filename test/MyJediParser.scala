package test

import context._

object MyJediParser extends App {

  TestRunner.run(console.execute("1 == 1"), "true")
  TestRunner.run(console.execute("1 == 1 == 1"), "true")
  TestRunner.run(console.execute("1 == 2 == 1"), "false")
  TestRunner.run(console.execute("1 == 2 == \"aa\""), "false")
}