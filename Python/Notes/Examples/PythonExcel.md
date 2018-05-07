# Python处理Excel文件(xlsx文件格式)

## 各个库对比

![](python-excel-01.png)

## openpyxl

### 读取文件

```python
from openpyxl import load_workbook

workbook = load_workbook(file_name)
```

### 读取所有sheet名

```python
from openpyxl import load_workbook

workbook = load_workbook(file_name)
print(workbook.get_sheet_names())
```

#### 输出

```shell
['工作表3', '工作表4', '工作表2']
```

###  简单示例

```python
from openpyxl import load_workbook

# 读取文件
workbook = load_workbook(file_name)
# 获取所有sheet名称
sheet_names = workbook.get_sheet_names()
for sheet_name in sheet_names:
    print('Sheet name:', sheet_name)
	# 通过名称获取特定sheet
    worksheet = workbook.get_sheet_by_name(sheet_name)
    # 获取所有行
    rows = worksheet.rows
    for row in rows:
        # 将一行不为None的列组成列表
        print([col.value for col in row if col.value is not None])
```

#### 输出

```shell
bovenson@ThinkCentre:~/git/cloudlibrary/other/import_data$ python3 import_booklist.py booklist.xlsx 
Sheet name: 工作表3
['5座城市5本书，带你领略不同的地域风情', '《带一本书去巴黎》', '《罗马帝国衰亡史》', '《战争与和平》', '《城门开》', '《伊斯坦布尔：一座城市的记忆》']
```

