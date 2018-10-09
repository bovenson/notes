let obj = [{
    "id": 1,
    "parent": null,
    "child": [{
        "id": 2,
        "parent": 1,
        "child": [{
            "id": 3,
            "parent": 2
        }]
    }]
}];

function treeObj(o, res) {
    o.forEach(function(item) {
        res.push({id: item.id, parent: item.parent});
        if (item.child) {
            treeObj(item.child, res);
        }
    });
}

let res = [];
treeObj(obj, res);
console.log(res);
