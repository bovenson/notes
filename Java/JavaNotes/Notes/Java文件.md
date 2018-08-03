# Java文件

## File

```java
File file = new File("/home/t.c");
String fileName = file.getName();			// 文件名
String fileParent = file.getParent();		// 文件所在目录(除去文件名的路径)
String filePath = file.getAbsolutePath();	// 包含文件名及路径的路径
```
