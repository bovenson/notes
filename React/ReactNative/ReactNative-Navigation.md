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

## 返回

```react
class HomeScreen extends React.Component {
  render() {
    const {goBack} = this.props.navigation;
    return (
      <View>
        <Button
          onPress={() => goBack()}
          title="Go back from this HomeScreen"
        />
        <Button
          onPress={() => goBack(null)}
          title="Go back anywhere"
        />
        <Button
          onPress={() => goBack('screen-123')}
          title="Go back from screen-123"
        />
      </View>
     )
   }
}
```

```react
this.props.navigation.goBack()
```



## 错误

### 一

`react native navigator:Undefined is not an object(evaluating this.props.navigation.navigate)`

```shell
#### 1
## 尽量以这种方式使用导航
const App = StackNavigator({
   Login: {screen: Login},
   Home: {screen: Home},
});

export default class ECart extends Component {
  render() {
    return (
      <App />
    );
  }
}

#### 2
## onPress指定的函数没绑定this
<TouchableOpacity 
	onPress={this._onPress.bind(this)}
/>
```



