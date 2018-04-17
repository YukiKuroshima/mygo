package test

import value._

object TestRunner {
  def run[T](value: T, expected: String) {
    if (value.toString == expected) {
      println("PASS")
    } else {
      println("FAIL")
      println("Expected: " + expected + " but found: " + value)
    }
  }
}