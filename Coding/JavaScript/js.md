---
title: JS 笔记
tatgs:
	- Javascript
categories:
	- Javascript
---

# 动态创建元素

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

# 数组

```javascript
// 添加元素
arr.push("A");	// 尾部添加
```

# 字典

## 遍历

```javascript
let a = {b: 1, c: 2};
for (let key in a) {
  console.log(key + " - " + a[key]);
}
```

# `=>`

不要在选项属性或回调上使用[箭头函数](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Functions/Arrow_functions)，比如

-  `created: () => console.log(this.a)` 
- `vm.$watch('a', newValue => this.myMethod())`

**因为箭头函数是和父级上下文绑定在一起的**，`this` 不会是如你所预期的 Vue 实例，经常导致 `Uncaught TypeError: Cannot read property of undefined` 或 `Uncaught TypeError: this.myMethod is not a function` 之类的错误。

# Date

## 格式化日期

```javascript
function formatDate(date) {
  var monthNames = [
    "January", "February", "March",
    "April", "May", "June", "July",
    "August", "September", "October",
    "November", "December"
  ];

  var day = date.getDate();
  var monthIndex = date.getMonth();
  var year = date.getFullYear();

  return day + ' ' + monthNames[monthIndex] + ' ' + year;
}

console.log(formatDate(new Date()));  // show current date-time in console
```

```javascript
var datestring = d.getDate()  + "-" + (d.getMonth()+1) + "-" + d.getFullYear() + " " +
d.getHours() + ":" + d.getMinutes();
```

```javascript
> new Date().toISOString().split(/[T.]/)		// 非本地时间
> (3) ["2018-06-27", "01:48:19", "961Z"]
```

```javascript
var today = new Date()
today.toLocalString()
...
```



**使用moment**

```javascript
/**
 * 格式化时间
 * @param stamp         时间戳
 * @param withoutTime   false、undefined: 有时、分、秒; true: 截止到day
 * @returns {string}
 */
export function formatDate(stamp, withoutTime) {
    return isUndefinedOrNull(withoutTime) || !withoutTime ? moment(stamp).format('YYYY-MM-DD HH:mm') : moment(stamp).format('YYYY-MM-DD');
}

/**
 * 格式化时间
 * @param stamp         时间戳
 * @param withoutTime   false、undefined: 有时、分、秒;
 * @returns {string}
 */
export function formatDateHMS(stamp, withoutTime) {
    return isUndefinedOrNull(withoutTime) || !withoutTime ? moment(stamp).format('HH:mm:ss') :'';
}
```

# 定时器

**设置定时器**

```javascript
var timer = window.setInterval(function () {}, 1000);
```

**清除定时器**

```javascript
window.clearInterval(timer);
```

