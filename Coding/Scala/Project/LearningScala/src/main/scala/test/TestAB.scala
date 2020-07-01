package test

object TestAB {
    def main(args: Array[String]): Unit = {
        def varParams(a:Int, ss:String*) = {
            var res = "" + a + "\t"
            for (s <- ss) {
                res += s + "\t"
            }
            res
        }
        println(varParams(1, "hello", "world"))
    }
}
