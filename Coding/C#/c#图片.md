[TOC]

# c#图片

## pictureBox

### 显示

```c#
pictureBox.Image = Image.FromFile(@"这里填写你的图片的位置"); 	// 如果图片为导入工程的资源
pictureBox.Image = Image.FromFile("这里填写你的图片的位置"); 	// 图片
```

### image转为byte[]

```c#
Image vcodeImg = Image.FromFile("vcode.jpg");
MemoryStream ms = new MemoryStream();
Bitmap bmp = new Bitmap(vcodeImage);
bmp.Save(ms, System.Drawing.Imaging.ImageFormat.Jpeg);
byte[] data = new byte[ms.Length];
ms.Position = 0;
ms.Read(data, 0, Convert.ToInt32(ms.Length));
bmp.Dispose();
```

