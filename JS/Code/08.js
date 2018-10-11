var obj ={
    h:1,
    e:1,
    l:3,
    o:2,
    w:1,
    r:1,
    d:1
};

let k, v;
Object.keys(obj).forEach(function(key, index) {
    if (index == 0 || v < obj[key]) {
        k = key;
        v = obj[key];
    }
});

console.log(k, v);
