def func() : Int = {
    println("Call func")
    10086
}

class A {
    def _v = func()
}
implicit val s = "hello world"
println(s)
val a = new A
implicit val b = new A
println("HEL")
println(a._v)
println(b._v)