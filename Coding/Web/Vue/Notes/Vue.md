---
title: Vue笔记
tags:
	- Vue
categories:
	- Vue
---

# 组件

## 父组件向子组件传参

**父组件**

```vue
<template>
    <div class="user-info-wrp">
        <div v-for="(item, index) in activities" :key="index">
            <activity-info :activity="item"></activity-info> <!-- :activity 绑定参数 -->
	    </div>
    </div>
</template>
<script>
// 导入
import ActivityInfo from '@/components/activity/ActivityInfo'
export default {
  data: function () {
    return {
      activities: [
        {
          title: 'TITLE',
          region: 'REGION',
          date: 'DATE',
          pic: 'PIC_URL'
        }, {
          title: 'TITLE',
          region: 'REGION',
          date: 'DATE',
          pic: 'PIC_URL'
        }
      ]
    }
  },
  components: {
    ActivityInfo
  }
}
</script>
```

**子组件**

```vue
<template>
  <div>
    <div class="activity-item">
      <img :src="activity.pic" />
      <div class="activity-info">
        <p class="title">{{activity.title}}</p>
        <p class="region">{{activity.region}}</p>
        <p class="date">{{activity.date}}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    activity: {
      type: Object,
      default: () => { // 默认值
        return {
          title: 'TITLE',
          region: 'REGION',
          date: 'DATE',
          pic: 'PIC_URL'
        }
      }
    }
  }
}
```

