class C (val s:String)

object D {
    def f()(implicit c : C) {
        println(c.s)
    }
}

object B {
    def f()(implicit c : C) {
        println(c.s)
        D.f()
    }
}

object F {
    implicit val c3 = new C("c3")
    def f() {
        B.f()
    }
}

object A {
    implicit val c1 = new C("c1");
    implicit val c2 = new C("c2");
    def f() {
        B.f()(c1)
    }
}

object G {
    implicit val c4 = new C("c4")
    implicit def c5 = new C("c5")
    // case class C5(c5=new C("5"))

    def f() {
        D.f()(c4)
        D.f()(c5)
    }
}

object H {
    implicit val c6 = new C("c6")
    def f() {
        D.f()
    }
}

A.f()
F.f()
G.f()
H.f()
/** Output
c1
c1
*/
