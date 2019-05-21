# FileField绝对路径问题

```python
# models.py
class DailyLove(models.Model):
    content = models.CharField(max_length=1000)
    pic = models.FileField(upload_to='upload/api/media/DailyLove/')
    date = models.DateTimeField(auto_created=True)

    def __str__(self):
        return str(self.date)

# serializers.py
class DailyLoveSerializer(serializers.HyperlinkedModelSerializer):
    def to_representation(self, instance):
        representation = super(DailyLoveSerializer, self).to_representation(instance)
        representation['pic_url'] = self.context['request'].build_absolute_uri('/' + instance.pic.url)
        return representation

    class Meta:
        model = DailyLove
        fields = '__all__'

# views.py
class DailyLoveViewSet(viewsets.ModelViewSet):
    queryset = DailyLove.objects.all().order_by('-date')
    serializer_class = DailyLoveSerializer
    
# result
HTTP 200 OK
Allow: GET, POST, HEAD, OPTIONS
Content-Type: application/json
Vary: Accept

[
    {
        "url": "http://localhost:8088/daily/3/",
        "date": "2019-05-04T12:33:00+08:00",
        "content": "123",
        "pic": "http://localhost:8088/daily/upload/api/media/DailyLove/nitish-meena-37745-unsplash.jpg",
        "pic_url": "http://localhost:8088/upload/api/media/DailyLove/nitish-meena-37745-unsplash.jpg"
    }
]
```

