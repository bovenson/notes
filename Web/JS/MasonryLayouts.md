---
title: 原生JS实现瀑布流效果
tags:
	- JS
	- 瀑布流
categories:
	- Web
---

# `masonry-layouts.js`

```javascript
/**
 * 瀑布流布局
 */

var Masonry = function masonry () {};
Masonry.prototype.loading = false;
Masonry.prototype.noMoreData = false;

/**
 * 初始化
 * @method init
 * @for Masonry
 * @param {string} id   需要实现无限加载的 DOM Element id 
 * @param {int} columns 瀑布流列数
 * @param {int} gap     可选, 列间隔; 如果不赋值, 根据列数计算
 * @param {function} loadingDataCBF 加载更多数据的回调函数
 * @param {function}  parseCBF      格式化数据列表每一项的函数
 * @param {int}       nextPage      下一页页码
 */
Masonry.prototype.init = function ({id, columns, gap, loadingDataCBF, parseCBF, nextPage}) {
  var box = document.getElementById(id);
  var items = box.children;
  nextPage = nextPage || 1;
  
  window.onload = function() {
      waterFall();

      // 页面尺寸改变时实时触发
      window.onresize = function() {
          waterFall();
      };
  
      window.onscroll = function() {
        loadingOrNot();
      };
  };

  function waterFall() {
    var boxWidth = box.offsetWidth;
    var itemWidth = items[0].offsetWidth;
    if (gap && !columns) {
        columns = parseInt(boxWidth / (itemWidth + gap));
    } else {
      if (columns > 1) {
        gap = parseInt(boxWidth - itemWidth * columns) / (columns - 1);
      } else {
        gap = 0;
      }
    }
    var arr = [];
    for (var i = 0; i < items.length; i++) {
        if (i < columns) {
            items[i].style.top = 0;
            items[i].style.left = (itemWidth + gap) * i + 'px';
            arr.push(items[i].offsetHeight);
        } else {
            var minHeight = arr[0];
            var index = 0;
            for (var j = 0; j < arr.length; j++) {
                if (minHeight > arr[j]) {
                    minHeight = arr[j];
                    index = j;
                }
            }
            items[i].style.top = arr[index] + 'px';
            items[i].style.left = items[index].offsetLeft + 'px';
            arr[index] = arr[index] + items[i].offsetHeight;
        }
    }
    var maxHeight = Math.max.apply(null, arr);
    box.style.height = maxHeight + 'px'; //因为是绝对定位absolute，所以父级没有高度，需要取最高的一列的高度给父级
  }

  /**
   * 加载更多数据
   * @method loadingData
   */
  function loadingData () {
    let that = this;
    this.loading = true;
    loadingDataCBF(nextPage).then(res => {
      if (res && res.length > 0) {
        res.forEach(item => {
          var html = parseCBF(item);
          box.insertAdjacentHTML('beforeend', html);
        });
        waterFall();
        that.loading = false;
        nextPage += 1;
      } else {
        that.noMoreData = true;
      }
    });
    loadingOrNot();
  }

  /**
   * 判断是否加载更多数据
   * @method loadingOrNot
   */
  function loadingOrNot() {
    if (this.noMoreData || this.loading) {  // 如果没有更多数据or正在加载中,则不加载
      return;
    }
    if (items.length > 0) {
        var scrollTop = document.documentElement.scrollTop || window.pageYOffset || document.body.scrollTop;
        var viewHeight = document.documentElement.clientHeight;
        var lastElement = items[items.length-1];
        var lastTop = lastElement.offsetTop + Math.floor(lastElement.offsetHeight); // 滚动到最后一个元素加载
        if (lastTop < scrollTop + viewHeight) {   // 如果底部滚动到最后一个元素，返回true
          loadingData();
        }
    } else {
      loadingData();
    }
  }
}
```

# 使用

```javascript
var masonry = new Masonry();
masonry.init({id: 'waterFallBox', columns: 2, loadingDataCBF: loadingData, parseCBF: parseData, nextPage: 2});

/**
 * 加载数据
 * @param {int} page 加载的页码
 */
function loadingData (page) {
  return new Promise(function (resolve) {
        $.ajax({
            type: 'get',
            url: '{*******}',
            async: false,
            data: {
                id: 10168,
                type: 1,
                page: page
            },
            dataType: 'json',
            cache: false,
            timeout: 5000,
            success: function(result,data){
                if (data === 'success') {
                    resolve(result.data.forum_list);
                } else {
                    // TODO
                }
            },
            error: function(){
                // TODO
            }
        });
    })
}

/**
 * 格式化返回数据的每个项目
 * @param {dict} item 
 */
function parseData (item) {
    var html = '<div class="article-wrp">' + 
        '<div class="article">' + 
        '<img class="img" src="' + item.cover + '"/>' + 
        '<div class="article-summary">';

        item.mark.forEach(function (tag) {
            html += '<span>#' + tag.tagName + '</span>';
        })
        html += item.title + '</div>';
        html += '<div class="article-user-info-wrp">';
        html += '<div class="article-user-info">';
        html += '<img class="article-user-icon" src="' + item.user_avatar + '"/>';
        html += '<span class="no-wrap article-user-username ">' + item.nickname + '</span>';
        html += '</div>';
        html += '<div class="article-like">';
        html += '<img class="like-icon" src="' + (item.is_like === 0 ? '../img/icon/like.png' : '../img/icon/like-pressed.png') + '"/>';
        html += '<span class="no-wrap like-num">' + item.count_like + '</span>';
        html += '</div></div></div></div>';
  return html;
}
```

