function getURL(link, params) {
    let apd = '?';
    for (let i in params) {
        if (apd.length > 1) {
            apd += '&'
        }
        apd += encodeURI(i) + '=' + encodeURI(params[i])
    }
    return link + apd;
}

let res = getURL('www.baid.com', {'a': 1, 'b': 2});
console.log(res);
