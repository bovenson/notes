# PIL

## 读取

```python
# 读取
img = Image.open('/home/bovenson/Downloads/Other/mnist-test/4.3192.jpg')
```

## 图片信息

```python
from PIL import Image

# 读取
img = Image.open('/home/bovenson/Downloads/Other/mnist-test/4.3192.jpg')

# 打印图片信息
print('Filename:\t', img.filename)
print('Format:\t', img.format)
print('Mode:\t', img.mode)
print('Size:\t', img.size)
print('Width:\t', img.width)
print('Height:\t', img.height)
print('Palette:\t', img.palette)
print('Info:\t', img.info)
```

输出:

```shell
/usr/bin/python3.5 /home/bovenson/Git/notes/Python/PythonPycharmProject/pic/pic_example.py
Filename:	 /home/bovenson/Downloads/Other/mnist-test/4.3192.jpg
Format:	 JPEG
Mode:	 L
Size:	 (28, 28)
Width:	 28
Height:	 28
Palette:	 None
Info:	 {'jfif_density': (1, 1), 'jfif_unit': 0, 'jfif': 257, 'jfif_version': (1, 1)}
```

## 旋转

```python
from PIL import Image

# 读取
img = Image.open('/home/bovenson/Downloads/Other/mnist-test/4.3192.jpg')

# 旋转图片
img.rotate(180).show()
```

## 保存

API: `Image.save(fp, format=None, **params)`

- fp: 可以是文件名(字符串类型), pathlib.Path对象或者file对象.
- format: 保存的图片格式.

```pyhton

```

