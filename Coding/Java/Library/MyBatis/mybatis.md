# mybatis

## mapper接口中

| 方法                                       | 功能说明                                    |
| ---------------------------------------- | --------------------------------------- |
| int countByExample(UserExample example) thorws SQLException | 按条件计数                                   |
| int deleteByPrimaryKey(Integer id) thorws SQLException | 按主键删除                                   |
| int deleteByExample(UserExample example) thorws SQLException | 按条件查询                                   |
| String/Integer insert(User record) thorws SQLException | 插入数据（返回值为ID）                            |
| User selectByPrimaryKey(Integer id) thorws SQLException | 按主键查询                                   |
| ListselectByExample(UserExample example) thorws SQLException | 按条件查询                                   |
| ListselectByExampleWithBLOGs(UserExample example) thorws SQLException | 按条件查询（包括BLOB字段）。只有当数据表中的字段类型有为二进制的才会产生。 |
| int updateByPrimaryKey(User record) thorws SQLException | 按主键更新                                   |
| int updateByPrimaryKeySelective(User record) thorws SQLException | 按主键更新值不为null的字段                         |
| int updateByExample(User record, UserExample example) thorws SQLException | 按条件更新                                   |
| int updateByExampleSelective(User record, UserExample example) thorws SQLException | 按条件更新值不为null的字段                         |

## example实例

```java
// mybatis的逆向工程中会生成实例及实例对应的example，example用于添加条件，相当where后面的部分 
xxxExample example = new xxxExample(); 
Criteria criteria = new Example().createCriteria();
```

| 方法                                       | 说明                           |
| ---------------------------------------- | ---------------------------- |
| example.setOrderByClause(“字段名 ASC”);     | 添加升序排列条件，DESC为降序             |
| example.setDistinct(false)               | 去除重复，boolean型，true为选择不重复的记录。 |
| criteria.andXxxIsNull                    | 添加字段xxx为null的条件              |
| criteria.andXxxIsNotNull                 | 添加字段xxx不为null的条件             |
| criteria.andXxxEqualTo(value)            | 添加xxx字段等于value条件             |
| criteria.andXxxNotEqualTo(value)         | 添加xxx字段不等于value条件            |
| criteria.andXxxGreaterThan(value)        | 添加xxx字段大于value条件             |
| criteria.andXxxGreaterThanOrEqualTo(value) | 添加xxx字段大于等于value条件           |
| criteria.andXxxLessThan(value)           | 添加xxx字段小于value条件             |
| criteria.andXxxLessThanOrEqualTo(value)  | 添加xxx字段小于等于value条件           |
| criteria.andXxxIn(List<？>)               | 添加xxx字段值在List<？>条件           |
| criteria.andXxxNotIn(List<？>)            | 添加xxx字段值不在List<？>条件          |
| criteria.andXxxLike(“%”+value+”%”)       | 添加xxx字段值为value的模糊查询条件        |
| criteria.andXxxNotLike(“%”+value+”%”)    | 添加xxx字段值不为value的模糊查询条件       |
| criteria.andXxxBetween(value1,value2)    | 添加xxx字段值在value1和value2之间条件   |
| criteria.andXxxNotBetween(value1,value2) | 添加xxx字段值不在value1和value2之间条件  |

## 示例

### 查询

```java
// 查询一 selectByPrimaryKey()
User user = XxxMapper.selectByPrimaryKey(100); //相当于select * from user where id = 100

// 查询二 selectByExample() 和 selectByExampleWithBLOGs()
UserExample example = new UserExample();
Criteria criteria = example.createCriteria();
criteria.andUsernameEqualTo("bovenson");
criteria.andUsernameIsNull();
example.setOrderByClause("username asc,email desc");
List<?>list = XxxMapper.selectByExample(example);
//相当于：select * from user where username = 'bovenson' and  username is null order by username asc,email desc

// 注：在iBator逆向工程生成的文件XxxExample.Java中包含一个static的内部类Criteria，Criteria中的方法是定义SQL 语句where后的查询条件。

// 查询三 模糊查询
sysUserExample.createCriteria().andUsernameLike(String.format("%%%s%%", "username");
```

### 插入数据

```java
// 使用 insert()
User user = new User();
user.setId("dsfgsdfgdsfgds");
user.setUsername("admin");
user.setPassword("admin")
user.setEmail("bovenson@163.com");
XxxMapper.insert(user);
//相当于：insert into user(ID,username,password,email) values ('dsfgsdfgdsfgds','admin','admin','bovenson@126.com');
```

### 更新数据

```java
// 一 使用 updateByPrimaryKey()
User user =new User();
user.setId("dsfgsdfgdsfgds");
user.setUsername("bovenson");
user.setPassword("bovenson");
user.setEmail("bovenson@163.com");
XxxMapper.updateByPrimaryKey(user);
//相当于：update user set username='bovenson', password='bovenson', email='bovenson@163.com' where id='dsfgsdfgdsfgds'

// 二 使用 updateByPrimaryKeySelective
User user = new User();
user.setId("dsfgsdfgdsfgds");
user.setPassword("bovenson");
XxxMapper.updateByPrimaryKeySelective(user);
//相当于：update user set password='bovenson' where id='dsfgsdfgdsfgds'

// 三 updateByExample() 和 updateByExampleSelective()
UserExample example = new UserExample();
Criteria criteria = example.createCriteria();
criteria.andUsernameEqualTo("admin");
User user = new User();
user.setPassword("bovenson");
XxxMapper.updateByExampleSelective(user,example);
//相当于：update user set password='bovenson' where username='admin'
// updateByExample()更新所有的字段，包括字段为null的也更新，建议使用 updateByExampleSelective()更新想更新的字段
```

### 删除数据

```java
// 一 deleteByPrimaryKey()
XxxMapper.deleteByPrimaryKey(1);  //相当于：delete from user where id=1

// 二 deleteByExample
UserExample example = new UserExample();
Criteria criteria = example.createCriteria();
criteria.andUsernameEqualTo("admin");
XxxMapper.deleteByExample(example);
//相当于：delete from user where username='admin'
// 这里的XxxMapper示例(在XxxServiceImpl中):
import com.neu.cse.sysControl.mapper.TbUserMapper;
@Autowired(required = true)
private TbUserMapper userMapper;
userMapper.updateByExampleSelective(user, example);
```

### 查询数据数量

```java
// countByExample()
UserExample example = new UserExample();
Criteria criteria = example.createCriteria();
criteria.andUsernameEqualTo("bovenson");
int count = XxxMapper.countByExample(example);
//相当于：select count(*) from user where username='bovenson'
```

# 错误

## 一

```java
org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.reflection.ReflectionException: There is no getter for property named '__frch_criterion_1' in 'class com.neu.cse.powercloud.pojo.sysmanage.SysUserExample'
```

**原因：**

- 判断条件类型不正确（例如：criteria.andStatusNotEqualTo（"Wrong Type"））