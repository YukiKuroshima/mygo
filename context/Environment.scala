package context

import scala.collection.mutable._
import value._
import expression._

class Environment(var extension: Environment = null)
   extends HashMap[Identifier, Value] with Value {

  // used by closures to bind parameters to arguments
  def bulkPut(params: List[Identifier], args: List[Value]) {
    if (params.length != args.length) throw new TypeException("# arguments != #parameters")
    for(i <- 0 until params.length) this.put(params(i), args(i))
  }
  
  /*
  override def contains(name: Identifier): Boolean = {
    if (super.contains(name)) true
    else if (extension != null) extension.contains(name)
    else false
  }
  */
  override def apply(name: Identifier): Value = {
    if (this.contains(name)) super.apply(name)
    else if (extension != null) extension.apply(name)
    else throw new UndefinedException(name)
  }
}