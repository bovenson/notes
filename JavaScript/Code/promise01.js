function p() {
    return new Promise(function(resolve, reject) {
        setTimeout(function(){
            resolve(true);
        }, 2000);
    });
}

async function t() {
    const result = await p().then(function() {});
    return result;
}

console.log("--- start ---");
let result = t();
console.log(result);
console.log("--- end ---");
