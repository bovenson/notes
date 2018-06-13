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

## 便利字典

```javascript
let a = {b: 1, c: 2};
for (let key in a) {
  console.log(key + " - " + a[key]);
}
```

## `=>`

不要在选项属性或回调上使用[箭头函数](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Functions/Arrow_functions)，比如

-  `created: () => console.log(this.a)` 
- `vm.$watch('a', newValue => this.myMethod())`

**因为箭头函数是和父级上下文绑定在一起的**，`this` 不会是如你所预期的 Vue 实例，经常导致 `Uncaught TypeError: Cannot read property of undefined` 或 `Uncaught TypeError: this.myMethod is not a function` 之类的错误。

# Date

## 格式化日期

