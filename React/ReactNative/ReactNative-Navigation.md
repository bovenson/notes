---
title: React Native 导航
tags: React Native, 导航
---

# React Native Navigation

## react-navigation

### 不可返回导航

```javascript
// 不可返回导航
const resetAction = NavigationActions.reset({
  	index: 0,
  	actions: [
    	NavigationActions.navigate({
     	routeName: 'Main',
    	})
  	]
});

this.props.navigation.dispatch(resetAction);
// this.props.navigation.navigate('Main');  // 可返回导航
```

### 参考

- [参考一](http://blog.csdn.net/sinat_17775997/article/details/70176688)