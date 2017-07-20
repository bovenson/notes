# Scala:zipWithIndex

为集合添加序号.

## 代码示例

``` scala
val itemsCosts = Seq(("Pencil", 0.52), ("Paper", 1.35), ("Notebook", 2.43))
val itemsCostsIndices = itemsCosts.zipWithIndex
for (itemCostsIndex <- itemsCostsIndices) {
    itemCostsIndex match {
        case ((item, cost), index) => println(s"$index $item costs $cost each")
    }
}
```



## 运行及输出

```shell
> scala ZipWithIn.scala
0 Pencil costs 0.52 each
1 Paper costs 1.35 each
2 Notebook costs 2.43 each
```



***

_待完善_