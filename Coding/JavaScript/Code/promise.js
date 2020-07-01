let pm = new Promise(function(resolve, reject) {
    setTimeout(function () {
        console.log("2s passed");
        resolve("resolved data");
    }, 2000);
});


setTimeout(function() {
    console.log(pm);
    pm.then(function(data) {
        console.log(data);
    })
}, 2200);
