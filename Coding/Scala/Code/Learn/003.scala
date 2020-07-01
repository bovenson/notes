import scala.concurrent.{Await, ExecutionContext, Future, TimeoutException}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global



val f = Future[Int] {
    Thread.sleep(1000)
    1 + 1
}

// 3 - this is blocking (blocking is bad)
val result = Await.result(f, 1 second)
println(result)
