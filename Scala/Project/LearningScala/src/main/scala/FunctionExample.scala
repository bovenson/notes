object FunctionExample {

    def main(args: Array[String]): Unit = {
        // println(addInt(1, 2))
        delayedByName(time())
        println("\n" + "*" * 30 + "\n")
        delayedByValue(time())
    }

    def addInt(a:Int, b:Int) : Int = {
        var sum:Int = 0
        sum = a + b
        sum
    }

    def time(): Long = {
        println("获取时间")
        System.nanoTime
    }

    def delayedByName(t: => Long): Long = {
        println("In delayedByName")
        t
    }

    def delayedByValue(t: Long): Long = {
        print("In delayedByValue")
        t
    }
}
