let arr = [];

function returnRecivedFunc (funct) {
    console.log(this.v);
    return function () {
        funct();
    }
}

for (let i = 0; i < 10; i++) {
    arr.push(function returnRecivedFunc(recivedFunc) {
        console.log(i);
        return function() {
            recivedFunc();
        }
    });
}

var func = arr.reduce((pre, cur) => {
    return cur(pre);
});
 
// func();
cur和pre是一个函数
这个函数是这样的的:

function (func) {
    return function () {
        func()
    }
}

返回一个函数
这个函数和输入参数的结构是一样的

cur(pre) // 返回和pre结构上一样的函数 只不过不是同一个对象
         // 这个返回值作为reduce下一次迭代的 pre 参数
// 传入 finish0 finish1 
// 执行 finish1(finish0)
//      console.log(1)
//      reutrn funciton () { finish0() }
//

// pre     cur
// finish0 finish1
// finish0 finish2
// ...
// finish0 finish9
