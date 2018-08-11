function add(queryStr, key, val) {
    if (queryStr.length > 0) {
        queryStr += '&';
    }
    queryStr += encodeURI(key) + '=' + encodeURI(key);
    return queryStr;
}

function del(queryStr, key) {
    let re = new RegExp('(^|&)' + key + '=.*?(&|$)');
    queryStr.replace()
    return queryStr.replace(re, '')
}

console.log(add('a=1&b=c', 'd', 3));
console.log(del('d=3&a=1&b=c', 'a'))
