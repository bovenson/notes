jsonStr = "{node: 'root', next: [{node: '1'}, {node: '1'}]}";

res = Array();

function recurveSolve(node, next) {
  if (!next) {return;}
  let jsonObj = JSON.parse(next);
  if (jsonObj.length > 1) {
    res.push(node);
  }
  for (let item in next) {
    recurveSolve(item["node"], item["next"]);
  }
}

function solve(str) {
  let jsonObj = JSON.parse(str);
  console.log(jsonObj);
  recurveSolve(jsonObj["node"], jsonObj["next"]);
}

function parseList(str) {

}

function parse(str) {
}

obj = parse(jsonStr);
solve(jsonStr);
console.log(res);