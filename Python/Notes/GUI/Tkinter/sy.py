# coding: utf-8

from tkinter import *
import os
from PIL import ImageTk, Image

root = Tk()
root.title('驾驶员疲劳检测系统')
root.resizable(width=False, height=False)
# root.geometry('600x300')

# ~/Video/背光.mp4

dir_name = '背光'
img_format = 'jpg'
print('~/Video/%s.mp4' % dir_name)
img_path = os.path.join('.', 'img', dir_name)
img_video = Image.open(os.path.join(img_path, dir_name + '.' + img_format))
img_video = img_video.resize((400, 400), Image.ANTIALIAS)
img_face = Image.open(os.path.join(img_path, dir_name + '人脸.' + img_format))
img_face = img_face.resize((150, 150), Image.ANTIALIAS)
img_eye = Image.open(os.path.join(img_path, dir_name + '人眼.' + img_format))
img_eye = img_eye.resize((150, 75), Image.ANTIALIAS)
img_mouth = Image.open(os.path.join(img_path, dir_name + '人嘴.' + img_format))
img_mouth = img_mouth.resize((150, 75), Image.ANTIALIAS)

left_frame = Frame(root)
left_frame.grid(row=0, column=0, sticky=NSEW, padx=25, pady=25)

right_frame = Frame(root)
right_frame.grid(row=0, column=1, sticky=NSEW, padx=25, pady=25)

video_frame = LabelFrame(left_frame, text='视频', padx=10, pady=10)
video_frame.grid(row=0, column=0, rowspan=3, sticky=N, padx=10)
img1 = ImageTk.PhotoImage(img_video)
panel = Label(video_frame, image = img1, width=400, height=400)
panel.grid(row=0, column=0, sticky=NSEW)

face_frame = LabelFrame(left_frame, text='Face', padx=10, pady=10)
face_frame.grid(row=0, column=1, sticky=NSEW)
img2 = ImageTk.PhotoImage(img_face)
panel = Label(face_frame, image = img2, width=150, height=150)
panel.grid(row=0, column=0, sticky=NSEW)

eye_frame = LabelFrame(left_frame, text='Eye', padx=10, pady=10)
eye_frame.grid(row=1, column=1, sticky=NSEW)
img3 = ImageTk.PhotoImage(img_eye)
panel = Label(eye_frame, image = img3, width=150, height=75)
panel.grid(row=0, column=0, sticky=NSEW)

mouth_frame = LabelFrame(left_frame, text='Mouth', padx=10, pady=10)
mouth_frame.grid(row=2, column=1, sticky=NSEW)
img4 = ImageTk.PhotoImage(img_mouth)
panel = Label(mouth_frame, image = img4, width=150, height=75)
panel.grid(row=0, column=0, sticky=NSEW)

label_frame_1 = LabelFrame(right_frame, text='检测结果', padx=10, pady=10)
label_frame_1.grid(row=0, column=0, sticky=NSEW)
entry1 = Entry(label_frame_1)
entry1.grid(row=0, column=0, sticky=NSEW)

label_frame_2 = LabelFrame(right_frame, text='检测方式', padx=10, pady=10)
label_frame_2.grid(row=1, column=0, sticky=NSEW, pady=50)
checkbox_1 = Checkbutton(label_frame_2, text='摄像头')
checkbox_1.grid(row=0, column=0, sticky=NSEW)
checkbox_2 = Checkbutton(label_frame_2, text='视频')
checkbox_2.grid(row=1, column=0, sticky=NSEW)
entry2 = Entry(label_frame_2)
entry2.grid(row=2, column=0, sticky=NSEW)
button_1 = Button(label_frame_2, text='开始检测')
button_1.grid(row=0, column=1)
button_2 = Button(label_frame_2, text='结束检测')
button_2.grid(row=1, column=1)
button_3 = Button(label_frame_2, text='选择视频')
button_3.grid(row=2, column=1)

label_frame_3 = LabelFrame(right_frame, text='设置', padx=10, pady=10)
label_frame_3.grid(row=2, column=0, sticky=NSEW, ipady=10)
frame_1 = Frame(label_frame_3)
frame_1.pack()
button_1 = Button(frame_1, text='开启语音')
button_1.grid(row=0, column=0, sticky=NSEW)
button_2 = Button(frame_1, text='关闭语音')
button_2.grid(row=0, column=1, sticky=NSEW)
button_3 = Button(frame_1, text='退出', pady=20)
button_3.grid(row=1, column=0, columnspan=2, sticky=NSEW)

root.mainloop()