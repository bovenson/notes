# 保存信息到session并在jsp中获取

## 保存

```java
// 在controller中:
request.getSession().setAttribute("username","admin");
```

## 在jsp中获取

```jsp
${sessionScope.get('username').username}
```

# controller 获取post/get数据

```java
String oldPassword = request.getParameter("oldPassword");
String newPassword = request.getParameter("newPassword");
String newPasswordRepeat = request.getParameter("newPasswordRepeat");
```

## 返回json格式数据

### 示例

```java
	// in controller
	// 使用 produces="application/json"
	@RequestMapping(value="/user/list/show", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	private ResponseResult showUserList(HttpServletRequest request, HttpServletResponse response) {
		try {
			return userManageService.getUserList();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return ResponseResult.no();
	}
```

# 将接收到的参数转换为Java对象

- [参考一](http://www.cnblogs.com/zj0208/p/6290252.html)

