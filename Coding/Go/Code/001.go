package main;

import "fmt";

func main() {
    // Hello world
    fmt.Println("Hello, world!");

    // variable
    //// 1
    var name0 int
    name0 = 0
    //// 2
    var name1 = 1.01
    //// 3
    name2 := "2"    // 这种不带声明格式的只能在函数体中出现
    println(name0, name1, name2);

    // multible variable
    var name01, name02, name03 int
    name01, name02, name03 = 1, 2, 3
    var name11, name12, name13 = 1.1, 2.2, 3.3
    name21, name22, name23 := "1", "2", "3"
    println(name01, name02, name03);
    println(name11, name12, name13);
    println(name21, name22, name23);
}
