package value

import context._

class Notification( message: String ) extends Value {
  override def toString = message.toString
  
}
object Notification {
  def apply(msg: String) = new Notification(msg)
  def OK: Value = apply("ok").asInstanceOf[Value]
  def DONE: Value = apply("done").asInstanceOf[Value]
  def UNSPECIFIED: Value = apply("Undefined identifier: ").asInstanceOf[Value]
}