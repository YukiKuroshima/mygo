package value

import expression._

case class Boole(val value: Boolean) extends Literal {
  override def toString = this.value.toString

  def &&(other: Boole) = Boole(value && other.value)
  def ||(other: Boole) = Boole(value || other.value)
  def unary_! = Boole(!value)
}