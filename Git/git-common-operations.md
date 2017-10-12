# 移除某文件的版本控制

还没有加到版本控制中：

- 还没有`git  add`
  - 在`.gitignore`中添加
- 已经git add
  - 先`git  rm  -r  --cached`文件
  - 在`.gitignore`中添加

已经加到版本控制中

- 先`git  rm  -r  --cached`文件  
- 在`.gitignore`中添加
- 最后`git commit -m`提交`.gitignore`

