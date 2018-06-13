---
title: forms
date: 2017-07-24 17:48:54
tags: Python, Django, Forms
---

# forms

## 定义

```python
class ArticleForm(forms.Form):

    class Meta:
        model = Article
        fields = ['title', 'content']
```

## 使用

```python
# print(request.POST)
form = MusicForm(request.POST or None, request.FILES or None)
if form.is_valid():
    music = form.save(commit=False)

    music_file = form.cleaned_data.get('name')
    if not is_music_file(music_file):
        # 如果文件格式不正确
        res["res"] = "error"
        res["msg"] = "文件格式不正确"
    elif Music.objects.filter(name=music_file):
            res["res"] = "error"
            res["msg"] = "文件已存在"
    else:
        music.save()

        all_music_album = get_all_music_album_instance()
        if isinstance(all_music_album, Album):
            all_music_album.musics.add(music)

        # 获取专辑id
        album_id = request.POST.get('album')
        # print(album_id)
        if album_id:
            album = Album.objects.filter(id=album_id).first()
            if album:
                album.musics.add(music)

        res["res"] = "success"
        res["msg"] = "添加成功"
else:
    res["res"] = "error"
    res["msg"] = "填写内容不符合要求"
```

### 更新

```python
question = Question.objects.get(pk=id)
if request.method == 'POST':
    form = QuestionForm(request.POST, instance=question)
    form.save()
    
# 另一个示例
# forms.py
# ...
class MyForm(forms.ModelForm):
    class Meta:
        model = MyModel

# views.py
# ...    
def my_view(request, id): 
    instance = get_object_or_404(MyModel, id=id)
    form = MyForm(request.POST or None, instance=instance)
    if form.is_valid():
        form.save()
        return redirect('next_view')
    return direct_to_template(request, 'my_template.html', {'form': form}     
```

