---
title: Vue插件
tags:
	- Vue
categories:
	- Vue
---

# 页面无限加载

**插件**

[vue-infinite-scroll](https://github.com/ElemeFE/vue-infinite-scroll)

**配置**

在`main.js`

```javascript
import infiniteScroll from 'vue-infinite-scroll'

Vue.use(infiniteScroll)
```

**实例**

```vue
<!-- 书单列表 -->
<style rel="stylesheet/scss" lang="scss">
</style>

<template>
  <div class="row mt-3">
    <div :class="CONTAINER_CLASSES"  v-infinite-scroll="loadMore" infinite-scroll-disable="busy">
      <book-item v-for="(item, index) in books" v-bind:key="index" :bookInfo="item"/>
    </div>
  </div>
</template>

<script>
import { getBooks } from '@/api/book'
import BookItem from './components/BookItem'
import {CONTAINER_CLASSES} from '@/styles/common'

export default {
  name: 'bookList',
  data: () => {
    return {
      CONTAINER_CLASSES: CONTAINER_CLASSES,
      books: [],
      busy: false,
      page: 1
    }
  },
  methods: {
    loadMore: function () {
      let that = this
      that.busy = true
      setTimeout(() => {
        getBooks({page: that.page}).then((res) => {
          if (res.status === 200 && res.statusText === 'OK') {
            this.page += 1
            let data = res.data.results
            data.forEach((item) => {
              that.books.push(item)
            })
          }
        }).catch((error) => {
          console.log(error)
        })
      })
    }
  },
  components: {
    BookItem
  }
}
</script>
```

# Font Awesome

## 安装

```shell
$ npm install vue-awesome --save-dev
```

`main.js`

```javascript
import Icon from 'vue-awesome'
Vue.component('icon', Icon)
```

**usage**

```html
<!-- basic -->
<icon name="beer"></icon>

<!-- with options -->
<icon name="sync" scale="2" spin></icon>
<icon name="comment" flip="horizontal"></icon>
<icon name="code-branch" label="Forked Repository"></icon>

<!-- stacked icons -->
<icon label="No Photos">
  <icon name="camera"></icon>
  <icon name="ban" scale="2" class="alert"></icon>
</icon>
```

## 旋转

```html
<icon name="spinner" spin/>
```

## 参考

- [Github - vue-awesome](https://github.com/Justineo/vue-awesome)

- [Font Awesome](https://fontawesome.com/icons)

# EditorMD

```vue
<template>
  <div id="editor-md">
    <link href="/static/editormd/css/editormd.min.css" rel="stylesheet">
    <textarea title="md-article"></textarea>
  </div>
</template>

<script>
  import $script from 'scriptjs'

  export default {
    name: 'EditorMd',
    data() {
      return {
        instance: null,
      };
    },
    props: {
      editorPath: {
        type: String,
        default: '/static/editormd'
      },
      editorConfig: {
        type: Object,
        default: function () {
          return {
            width: '88%',
            height: 530,
            path: '/static/editormd/lib/', // Autoload modules mode, codemirror, marked... dependents libs path
            codeFold: true,
            saveHTMLToTextarea: true,
            searchReplace: true,
            htmlDecode: 'style,script,iframe|on*',
            emoji: true,
            taskList: true,
            tocm: true,                  // Using [TOCM]
            tex: true,                   // 开启科学公式TeX语言支持，默认关闭
            flowChart: true,             // 开启流程图支持，默认关闭
            sequenceDiagram: true,       // 开启时序/序列图支持，默认关闭,
            imageUpload: true,
            imageFormats: ['jpg', 'jpeg', 'gif', 'png', 'bmp', 'webp'],
            imageUploadURL: 'examples/php/upload.php',
            onload: () => {
              // eslint-disable-next-line
              console.log('onload', this);
            }
          }
        }
      }
    },
    mounted () {
      // async loading js dependencies
      // editormd depdend on jquery and zepto
      $script([
        `${this.editorPath}/js/jquery.min.js`,
        `${this.editorPath}/js/zepto.min.js`
      ], () => {
        $script(`${this.editorPath}/js/editormd.min.js`, () => {
          this.initEditor()
        })
      })
    },
    methods: {
      initEditor() {
        // eslint-disable-next-line
        this.$nextTick((editorMD = window.editormd) => {
          if (editorMD) {
            // Vue 异步执行 DOM 更新，template 里面的 script 标签异步创建
            // 所以，只能在 nextTick 里面初始化 editor.md
            this.instance = editorMD('editor-md', this.editorConfig);
          }
        });
      },
    }
  }
</script>

<style scoped>
</style>
```

**参考**

- [Github](https://github.com/pandao/editor.md/issues/447)
- [Github](https://github.com/star7th/showdoc/blob/master/web_src/src/components/common/Editormd.vue)
- [SegmentFault](https://segmentfault.com/q/1010000009687169)
- [lwl](http://lwl.tech/post/3)