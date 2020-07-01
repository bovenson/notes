# DateTime

```scala
/// 时间遍历
// define
def dayIterator(start: DateTime, end: DateTime): Iterator[DateTime] = Iterator.iterate(start)(_ plusDays 1) takeWhile (_ isBefore end)

// use
val startDate: DateTime = new DateTime("2018-07-31")
val endDate: DateTime = new DateTime("2018-12-31")
dayIterator(startDate, endDate).foreach(dateTime => {
  println(s"year=${dateTime.getYear}/month=${dateTime.getMonthOfYear}/day=${dateTime.getDayOfMonth}")
})

// output
year=2018/month=7/day=31
year=2018/month=8/day=1
year=2018/month=8/day=2
year=2018/month=8/day=3
year=2018/month=8/day=4
year=2018/month=8/day=5
year=2018/month=8/day=6
year=2018/month=8/day=7
year=2018/month=8/day=8
year=2018/month=8/day=9
year=2018/month=8/day=10
...
```



