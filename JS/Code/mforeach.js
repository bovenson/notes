function forEach(arr, func) {
    for (let i = 0; i < arr.length; ++i) {
        func(arr[i]);
    }
}

function print(v) {
    return console.log(v);
}

let l = [1, 2, 3, 4, 5];
forEach(l, print);

