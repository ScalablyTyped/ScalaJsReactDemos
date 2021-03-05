# Working with objects

Javascript is remarkably flexible. When we integrate with arbitrary Javascript code in Scala.js, we need a very flexible
encoding to tag along. The encoding chosen for ScalablyTyped is the result of years of experimentation, and has
a much more dynamic feeling than what you may be used to.

Let's start with an example of a type definition we want to use:

```scala
@js.native
trait Point extends StObject {

 var x: Double = js.native

 var y: Double = js.native
}
object Point {

 @scala.inline
 def apply(x: Double, y: Double): Point = {
  val __obj = js.Dynamic.literal(x = x.asInstanceOf[js.Any], y = y.asInstanceOf[js.Any])
  __obj.asInstanceOf[Point]
 }

 @scala.inline
 implicit class PointMutableBuilder[Self <: Point] (val x: Self) extends AnyVal {

  @scala.inline
  def setX(value: Double): Self = StObject.set(x, "x", value.asInstanceOf[js.Any])

  @scala.inline
  def setY(value: Double): Self = StObject.set(x, "y", value.asInstanceOf[js.Any])
 }
}
```

We notice several things:
- it's a `@js.native` trait, so we cannot `new` it ourselves. This can be [`changed`](conversion-options.md#stenablescalajsdefined), but it's not recommended.
- it has two required members (`x` and `y`). Optional members would typically be wrapped in `js.UndefOr`
- it has an `object` with syntax to help us work with it
- the entire syntax is built on mutability. It's Javascript, after all. more on that further down

### Basic usage

```scala
// At construction time we need to supply all required parameters
val p = Point(x = 1,y = 2)

// we can mutate what we have
// this is equivalent to typescript `p.x = 3
val p2 = p.setX(3) 

// or we can duplicate and then mutate.
// this is equivalent to typescript `const p2 = {...p, x: 3}
val p3 = p.duplicate.setX(3) 

// we can combine with other javascript objects. 
// this is equivalent to javascript `const p3 = {...p, {}}`
val p4: Point with TickOptions = p.combineWith(TickOptions())

// fallback, if the type definitions are wrong or for any other reason you can break the contract
val p5: p.duplicate.set("x", "foo")

// you can also set any other property
val p6: p.duplicate.set("x2", "foo")
```
