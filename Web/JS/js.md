# js

## 动态创建元素

```javascript
// 标签
for (var i=0; i < book["tag"].length; ++i) {
  var newA = document.createElement("a"); // 创建元素
  console.log(newA);
  console.log(book["tag"][i]);
  newA.className = "link-a"; // 设置类
  newA.style.marginRight = "5px"; // 设置css属性

  newA.href = "javascript: void(0)"; // 设置属性
  newA.text = book["tag"][i].name; // 设置text
  document.getElementById('addBookModalInfoBodyTag').appendChild(newA);  // 作为一个元素的子元素添加
}
```

## 数组

```javascript
// 添加元素
arr.push("A");	// 尾部添加
```

## 删除元素

