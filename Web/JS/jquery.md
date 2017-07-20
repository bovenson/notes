# jquery

## 事件

### 绑定事件

```javascript
var confirmCancelModalConfirm = $("#confirmCancelModalConfirm");

confirmCancelModalConfirm.click(function () {
    alert('a');
});
```

### 接触绑定事件

```javascript
var confirmCancelModalConfirm = $("#confirmCancelModalConfirm");

confirmCancelModalConfirm.unbind('click');
```

## 属性

### 获取

```javascript
$("#element-id").attr("src");
```

### 修改属性

```javascript
$("#element-id").attr("src","image/1/gif");
```

## 动画

### 从左到右显示/隐藏

```JavaScript
// 设置宽度为toggle
musicPlayControlerContainer.animate({width: 'toggle'}, "slow");
```

## 表单

### serialize

```javascript
$.post("/sysControl/login/userLogin", $("#loginForm").serialize(), function(data) {
    if (data.msg == "OK") {
		var rolelist=data.data;
		if (redirectUrl == "") {
        	location.href = "/sysControl/main";				
		} else {
			location.href = redirectUrl;
		}
	} else {
		alert("登录失败");
		$("#userName").select();
	}
});
```

