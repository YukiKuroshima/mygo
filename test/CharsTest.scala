package test

import value._

object CharsTest extends App {
  
  println("hello")
  val s1 = Chars("California")
  val s2 = Chars("...")
  val s3 = Chars("Dreaming")
  val s4 = s1 + s2 + s3
  val s5 = Chars("California")

  val cat = Chars("cat")
  val dog = Chars("dog")
  
  val a = Chars("a")
  val b = Chars("b")

  println("s4 = " + s4)
  println(s1 + " < " + s3 + " = " + (s1 < s3))
  println(a + " < " + b + " = " + (a < b))
  println(b + " < " + a + " = " + (b < a))
  println(b + " < " + a + " = " + (b < a))
  println(cat + " < " + dog + " = " + (cat < dog))
  
  println(s1 + " == " + s5 + " = " + (s1 == s5))
  println(a + " == " + b + " = " + (a == b))
  println("a" + " == " + a + " = " + ("a" == a))

  println(s4.substring(Integer(3), Integer(8)))
  println(Chars("abc").substring(Integer(1), Integer(2)))
  
}