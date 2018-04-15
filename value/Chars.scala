package value

import expression._

case class Chars(val value: String) extends Literal with Ordered[Chars] with Equals {

  override def <(other: Chars) = compare(other) < 0
  def +(other: Chars): Chars = Chars(value + other.value)
  // def ==(other: Chars) = equals(other)
  def substring(start: Integer, end: Integer) = Chars(value.substring(start.value, end.value))

  def compare(other: Chars): Int = this.value.compare(other.value)

  override def canEqual(other: Any) =  other.isInstanceOf[Chars]

  override def equals(other: Any): Boolean = 
    other match {
       case other: Chars => this.canEqual(other) && (other.value == this.value)
       case _ => false
    }

  override def toString = value
  
}