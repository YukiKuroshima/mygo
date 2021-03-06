package context

import expression._
import value._

/*
 * Notes:
 * alu implements all low-level arithmetic, logic, and I/O functions
 * alu does lots of type checking
 * alu is a singleton
 */
object alu {
  // dispatcher
  def execute(opcode: Identifier, args: List[Value]): Value = {
    opcode.name match {
      case "add"    => add(args)
      case "mul"    => mul(args)
      case "sub"    => sub(args)
      case "div"    => div(args)

      case "less"   => less(args) //binary
      case "more" => more(args) // binary
      case "equals" => equals(args) // note: equals(7, true) = false, not error
      case "unequals" => unequals(args) // binary, = not(equals(args))?
      case "not" => not(args) // unary

      // primitive I/O ops:
      case "write"  => write(args)
      case "prompt" => prompt(args)
      case "read"   => read(args)
      case _        => throw new UndefinedException(opcode)
    }
  }

  private def toInt(arg: Value): Option[Integer] =
    if (arg.isInstanceOf[Integer]) Some(arg.asInstanceOf[Integer]) else None

  private def toReal(arg: Value): Option[Real] =
    if (arg.isInstanceOf[Real]) Some(arg.asInstanceOf[Real])
    else if (arg.isInstanceOf[Integer]) Some(Integer.intToReal(arg.asInstanceOf[Integer]))
    else None

  private def toChars(arg: Value): Option[Chars] =
    if (arg.isInstanceOf[Chars]) Some(arg.asInstanceOf[Chars]) else None

  private def add(args: List[Value]) = {
    val args2 = args.map(toInt).filter(_ != None)
    if (args2.size == args.size) args2.flatten.reduce(_ + _)
    else {
      val args3 = args.map(toReal).filter(_ != None)
      if (args3.size == args.size) args3.flatten.reduce(_ + _)
      else {
        val args4 = args.map(toChars).filter(_ != None)
        if (args4.size == args.size) args4.flatten.reduce(_ + _)
        else {
          throw new TypeException("Inputs to + must be numbers or texts")
        }
      }
    }
  }

  private def mul(args: List[Value]) = {
    val args2 = args.map(toInt).filter(_ != None)
    if (args2.size == args.size) args2.flatten.reduce(_ * _)
    else {
      val args3 = args.map(toReal).filter(_ != None)
      if (args3.size == args.size) args3.flatten.reduce(_ * _)
      else {
        throw new TypeException("Inputs to * must be numbers")
      }
    }
  }

  private def sub(args: List[Value]) = {
    val args2 = args.map(toInt).filter(_ != None)
    if (args2.size == args.size) args2.flatten.reduce(_ - _)
    else {
      val args3 = args.map(toReal).filter(_ != None)
      if (args3.size == args.size) args3.flatten.reduce(_ - _)
      else {
        throw new TypeException("Inputs to - must be numbers")
      }
    }
  }

  private def div(args: List[Value]) = {
    if (args.length != 2) throw new TypeException("div expects two inputs")
    val args2 = args.map(toInt).filter(_ != None)
    if (args2.size == args.size) args2.flatten.reduce(_/_)
    else {
      val args3 = args.map(toReal).filter(_ != None)
      if (args3.size == args.size) args3.flatten.reduce(_/_)
      else {
        throw new TypeException("Inputs to / must be numbers")
      }
    }
  }

  def less(args: List[Value]): Value = {
    if (args.length != 2) throw new TypeException("less expects two inputs")
    val args2 = args.map(toInt).filter(_ != None)
    if (args2.size == args.size) Boole(args2(0) < args2(1))
    else {
      val args3 = args.map(toReal).filter(_ != None)
      if (args3.size == args.size) Boole(args3(0) < args3(1))
      else {
        val args4 = args.map(toChars).filter(_ != None)
        if (args4.size == args.size) Boole(args4(0) < args4(1))
        else throw new TypeException("Inputs to < must be numbers or texts")
      }
    }
  }

  def more(args: List[Value]): Value = {
    if (args.length != 2) throw new TypeException("more expects two inputs")
    val args2 = args.map(toInt).filter(_ != None)
    if (args2.size == args.size) Boole(args2(0) > args2(1))
    else {
      val args3 = args.map(toReal).filter(_ != None)
      if (args3.size == args.size) Boole(args3(0) > args3(1))
      else {
        val args4 = args.map(toChars).filter(_ != None)
        if (args4.size == args.size) Boole(args4(0) > args4(1))
        else throw new TypeException("Inputs to > must be numbers or texts")
      }
    }
  }

  // more than 2 args ok
  def equals(args: List[Value]): Value = {
    if (args.length == 1) throw new TypeException("equals expects at least two inputs")
    // Boole(args.foldLeft(_ == _))
    def helper(current: Value, rest: List[Value]):Value = {
      if (current != rest.head) Boole(false)
      else if (rest.size == 1) Boole(true)
      else helper(rest.head, rest.tail)
    }
    helper(args.head, args)
  }
  /*
  def equals(args: List[Value]): Value = {
    if (args.length != 2) throw new TypeException("equals expects two inputs")

    val args2 = args.map(toReal).filter(_ != None)
    if (args2.size == args.size) Boole(args2(0) == args2(1))

    else {
      val args3 = args.map(toChars).filter(_ != None)
      if (args3.size == args.size) Boole(args3(0) == args3(1))
      else Boole(args(0) == args(1))
    }
  }
  *
  */

  def unequals(args: List[Value]): Value = not(List(equals(args)))

  def not(args: List[Value]): Value = {
    if (args.length != 1) throw new TypeException("not expects one input")
    else if (!args(0).isInstanceOf[Boole]) throw new TypeException("Inputs to ! must be Boole")
    !(args(0).asInstanceOf[Boole])
  }

  def write(vals: List[Value]): Value = { println(vals(0)); Notification.DONE }
  def read(vals: List[Value]): Value = { val result = io.StdIn.readDouble(); Real(result) }
  def prompt(vals: List[Value]): Value = { print("=> "); Notification.DONE }
}