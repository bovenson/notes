package test

object TestOne {
    def main(args: Array[String]): Unit = {

        val v = {}
        println(v.getClass)

        def test(code: => Unit) {
            println("START")
            code
            println("END")
        }

//        test {
//            println("A")
//            () => println("B")
//        }

        // test(println("C"))
        println({
            1
            2
            3
        })

        def addNum(i: Int, j: Int): Int = {
            i + j
        }

        addNum(1, 2)
    }
}
