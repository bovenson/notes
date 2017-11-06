# shutil:文件操作

## 移动文件

`shutil.move(src, dst, copy_function=copy2)`

-   递归得移动文件/文件夹
-   如果`dst`是一个存在的文件夹, `src`将会被移动到这个文件夹内
-   如果`dst`存在, 但不是一个文件夹, 是否覆盖, 则由`os.rename()`的行为决定
-   如果`os.rename()`不可用, 则会调用`copy_function`, 来拷贝文件, 以完成移动文件的目的


