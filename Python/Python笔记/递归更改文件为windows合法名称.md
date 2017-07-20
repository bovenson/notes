# 递归更改文件为windows合法名称

## 代码

```python
#!/usr/bin/python3
# codding: utf-8
import datetime
import os
import random
import re
import sys

random.seed(datetime.datetime.now().microsecond)


def get_win_legal_name(file_name):
    file_name = str(file_name).strip()
    res = re.sub(r'[\\/:*?"<>|]', r'', file_name)
    if len(res) == 0:
        res = str(random.random())[2:]
    return res
    pass


def change_name_recursion(absolute_file_path):
    for file_name in os.listdir(absolute_file_path):
        t_file_ab_path = os.path.join(absolute_file_path, file_name)
        if os.path.isdir(t_file_ab_path):
            change_name_recursion(t_file_ab_path)
            pass
        new_name = get_win_legal_name(file_name)
        print("new name:", new_name, " old name:", file_name)
        if new_name != file_name:
            os.rename(os.path.join(absolute_file_path, file_name), os.path.join(absolute_file_path, new_name))
        pass
    pass

if __name__ == "__main__":
    file_name = '.' if len(sys.argv) == 1 else sys.argv[1]
    print("Rename file/directory(Recursion): ", file_name)
    change_name_recursion(os.path.join(os.getcwd(), file_name))
    
# Usage: python3 python3_script_name.py filename
```

