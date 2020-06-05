function add() {
    let sum = 0;
    var _args = Array.prototype.slice.call(arguments); 
    var tmpf = function(){ 
        if(arguments.length === 0) { 
            //reduce() 方法接收一个函数作为累加器，数组中的每个值（从左到右）开始缩减，最终计算为一个值
            sum = _args.reduce((a,b) => {return a + b;},sum);//sum为传递给函数参数的初始值，在这里sum=0
        } 
        _args.push.apply(_args,[].slice.call(arguments));
    }
    tmpf();
}

console.log(add(1,2)());
console.log(add(1,2)(3,4)());
