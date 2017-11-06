[TOC]

# 读取配置文件

## configparser

### 简单使用

#### 配置文件示例

```ini
[db]
db_host = localhost  ds
db_port = 3306
db_user = root
db_passwd = "root"

[server]
server_ip = localhost
```

#### 读取程序

```python
# coding: utf-8
from configparser import ConfigParser

cf = ConfigParser()
cf.read("config.ini")

print(cf.get("db", "db_host"))
print(cf.get("db", "db_port"))
print(cf.get("db", "db_user"))
print(cf.get("db", "db_passwd"))

print(cf.get("server", "server_ip"))
```

#### 输出

```shell
localhost  ds
3306
root
"root"
localhost
```

### 更多

- [`configparser`](http://szhkai.win/docs/Python/python-3.6.1rc1-docs-html/library/configparser.html?highlight=configparser#module-configparser)类可以读写配置文件

#### 写一个配置文件

##### 代码

```python
# coding: utf-8
import configparser


config = configparser.ConfigParser()
# DEFAULT section
config['DEFAULT'] = {
    'ServerAliveInterval': '45',
    'Compression': 'yes',
    'CompressionLevel': '9'
}

# bitbucket.org section
config["bitbucket.org"] = {}
config['bitbucket.org']['User'] = 'hg'

# topsecret.server.com section
config['topsecret.server.com'] = {}
topsecret = config['topsecret.server.com']
topsecret['Port'] = '50022'
topsecret['ForwardX11'] = 'no'

# DEFAULT section again
config['DEFAULT']['ForwardX11'] = 'yes'

# 讲配置写入到ini文件
with open('example.ini', 'w') as configfile:
    config.write(configfile)
```

##### 写入结果

`File: example.ini`

```ini
[DEFAULT]
compressionlevel = 9
serveraliveinterval = 45
compression = yes
forwardx11 = yes

[bitbucket.org]
user = hg

[topsecret.server.com]
port = 50022
forwardx11 = no
```

#### 读取生成的配置文件

##### 代码

```python
# coding: utf-8

import configparser
config = configparser.ConfigParser()
config.sections()

config.read('example.ini')
config.sections()
print('bitbucket.org' in config)
print('bytebong.com' in config)

print(config['bitbucket.org']['User'])
print(config['DEFAULT']['Compression'])

topsecret = config['topsecret.server.com']
print(topsecret['ForwardX11'])
print(topsecret['Port'])

for key in config['bitbucket.org']:
    print(key)

print(config['bitbucket.org']['ForwardX11'])
```

##### 读取结果

```shell
True
False
hg
yes
no
50022
user
compressionlevel
serveraliveinterval
compression
forwardx11
yes
```

#### 回退值



## ini配置文件格式

- A configuration file consists of sections, each led by a `[section]` header, followed by key/value entries separated by a specific string (`=` or `:` by default [[1\]](http://szhkai.win/docs/Python/python-3.6.1rc1-docs-html/library/configparser.html?highlight=configparser#id14)).
- By default, section names are case sensitive but keys are not.
- Leading and trailing whitespace is removed from keys and values. 

### 示例

```ini
[Simple Values]
key=value
spaces in keys=allowed
spaces in values=allowed as well
spaces around the delimiter = obviously
you can also use : to delimit keys from values

[All Values Are Strings]
values like this: 1000000
or this: 3.14159265359
are they treated as numbers? : no
integers, floats and booleans are held as: strings
can use the API to get converted values directly: true

[Multiline Values]
chorus: I'm a lumberjack, and I'm okay
    I sleep all night and I work all day

[No Values]
key_without_value
empty string value here =

[You can use comments]
# like this
; or this

# By default only in an empty line.
# Inline comments can be harmful because they prevent users
# from using the delimiting characters as parts of values.
# That being said, this can be customized.

    [Sections Can Be Indented]
        can_values_be_as_well = True
        does_that_mean_anything_special = False
        purpose = formatting for readability
        multiline_values = are
            handled just fine as
            long as they are indented
            deeper than the first line
            of a value
        # Did I mention we can indent comments, too?
```

