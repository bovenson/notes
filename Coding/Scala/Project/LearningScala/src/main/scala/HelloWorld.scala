object HelloWorld {
    def main(args: Array[String]): Unit = {
//        val add = (i:Int, j:Int) => i + j
//        println("Hello World!")
//        println(add(1, 2))
//
//        var i: Int = 0
//
//        do {
//            i += 1
//        } while (i < 10)
//
//        for (i <- 1 to 3) { // to
//            println(i)
//        }
//
//        println("*" * 30)
//
//        for (_ <- 1 to 3) {
//            println(i)
//            i += 1
//        }
//
//        println("*" * 30)
//
//        for (j <- 1 until 10 by 2) {  // until
//            println(j)
//        }
//
//        println("*" * 30)
//
//        println(i)
//
//        println("*" * 30)
//
//        for (x <- List(1, 3, 4 ,5 ,6)) {
//            println(x)
//        }

//        for (i <- 1 to 10 if i % 2 == 0; if i >= 6) {
//            println(i)
//        }

//        for (i <- 1 to 10; j <- 1 to 10; k <- List("Hi", "Hello") if i%2==0; if i>4; if i<10; if j<4 && j%2==1) {
//            println("i:" + i + "\tj:" + j + "\tmessage:" + k)
//        }

        val forYield = for { x <- 1 to 10 if x % 2 == 0 } yield x
        println(forYield)
        println("length:" + forYield.length)
        for (a <- forYield) {
            println(a)
        }
    }
}