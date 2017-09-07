package test

object TestAA {
    def main(args: Array[String]): Unit = {
        def sub(a:Int=20, b:Int): Int = {
            a - b
        }

        println(sub(b = 4, a = 10))
        println(sub(b = 4))
    }
}
