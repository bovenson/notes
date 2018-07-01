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