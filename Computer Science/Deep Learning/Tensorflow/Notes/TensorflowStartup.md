# tensorflow开始

## 安装

### 使用Anaconda

在Anaconda环境中使用Tensorflow

- 下载 [Anacoda](https://www.continuum.io/downloads)

- 创建环境 `conda create python=3.6 -n tensorflow`; 删除环境`conda env remove -n tensorflow`

- 激活环境 `source activate tensorflow`

- 安装Tensorflow

  ```shell
  # 升级pip，这里安装python3.x版本tensorflow，可以使用pip -V命令查看pip版本
  # 确认是python3.x版本的pip，或者用pip3命令代替pip
  pip install --upgrade pip	

  # tfBinaryURL: tensorflow 二进制文件URL地址
  # 这里找对应版本的url：
  # https://www.tensorflow.org/install/install_linux#the_url_of_the_tensorflow_python_package
  pip install --ignore-installed --upgrade tfBinaryURL	
  # 以安装python3.6 cpu版本为例
  pip install --ignore-installed --upgrade https://storage.googleapis.com/tensorflow/linux/cpu/tensorflow-1.2.1-cp36-cp36m-linux_x86_64.whl
  ```

- 验证安装

  ```python
  # python程序：

  #！/usr/bin/python3
  # File: t1.py
  import tensorflow as tf
  hello = tf.constant('Hello, TensorFlow!')
  sess = tf.Session()
  print(sess.run(hello))

  # 运行
  python3 t1.py

  # 输出
  Hello, TensorFlow!
  ```

  ​

## 基本用法

- 使用图（graph）来表示计算任务；
- 在被称之为回话（Session）的上下文（context）中执行图；
- 使用tensor表示数据；
- 使用变量（Variable）维护状态；
- 使用feed和fetch可以为任意的操作（arbitrary operation）赋值或者从其中获取数据。

## Tensors

- The central unit of data in TensorFlow is the **tensor**. 

- A tensor consists of a set of primitive values shaped into an array of any number of dimensions. 

- A tensor's **rank** is its number of dimensions. 

- Examples：

  ```python
  3 # a rank 0 tensor; this is a scalar with shape []
  [1. ,2., 3.] # a rank 1 tensor; this is a vector with shape [3]
  [[1., 2., 3.], [4., 5., 6.]] # a rank 2 tensor; a matrix with shape [2, 3]
  [[[1., 2., 3.]], [[7., 8., 9.]]] # a rank 3 tensor with shape [2, 1, 3]
  ```



## Tutorial

**导入Tensorflow**

`import tensorflow as tf`

**Tensorflow程序包括两部分**:

- Building the computational graph
- Running the computational graph

A **computational graph** is a series of TensorFlow operations arranged into a graph of nodes.

Example:

```python
node1 = tf.constant(3.0, dtype=tf.float32)
node2 = tf.constant(4.0) # also tf.float32 implicitly
print(node1, node2)

# 输出
(tensorflow) bovenson@ThinkCentre:~/Git/Tensorflow/tutorial$ python3 t1.py 
Tensor("Const:0", shape=(), dtype=float32) Tensor("Const_1:0", shape=(), dtype=float32)

# 创建Session并调用其run函数计算node1和node2的值
sess = tf.Session()
print(sess.run([node1, node2]))

# 输出
[3.0, 4.0]

# 把Tensor和运算结合，来完成更复杂的计算操作：
node3 = tf.add(node1, node2)
print("node3:", node3)
print("sess.run(node3):", sess.run(node3))

# 输出
node3: Tensor("Add:0", shape=(), dtype=float32)
sess.run(node3): 7.0
    
# 一个图可以被参数化以接受外部输入，被称作占位符。一个占位符用来声明稍后会被赋值。
a = tf.placeholder(tf.float32)
b = tf.placeholder(tf.float32)
adder_node = a + b # + provides a shortcut for tf.add(a, b)
# 使用
print(sess.run(adder_node, {a: 3, b:4.5}))
print(sess.run(adder_node, {a: [1,3], b: [2, 4]}))
# 输出
7.5
[ 3.  7.]

# 还可以更复杂
add_and_triple = adder_node * 3.
print(sess.run(add_and_triple, {a: 3, b:4.5}))
# 输出
22.5

# 向图添加可训练的参数
W = tf.Variable([.3], dtype=tf.float32)
b = tf.Variable([-.3], dtype=tf.float32)
x = tf.placeholder(tf.float32)
linear_model = W * x + b

# 常量在调用tf.constant时被初始化,且不可改变. 相反, 变量在调用tf.Variable时不会被初始化, 必须显式地调用特定操作
init = tf.global_variables_initializer()
sess.run(init)
# It is important to realize init is a handle to the TensorFlow sub-graph that initializes all the global variables. Until we call sess.run, the variables are uninitialized.
# Since x is a placeholder, we can evaluate linear_model for several values of x simultaneously as follows:
print(sess.run(linear_model, {x:[1,2,3,4]}))
# 输出
[ 0.          0.30000001  0.60000002  0.90000004]

# We've created a model, but we don't know how good it is yet. To evaluate the model on training data, we need a y placeholder to provide the desired values, and we need to write a loss function.
# A loss function measures how far apart the current model is from the provided data. We'll use a standard loss model for linear regression, which sums the squares of the deltas between the current model and the provided data. linear_model - y creates a vector where each element is the corresponding example's error delta. We call tf.square to square that error. Then, we sum all the squared errors to create a single scalar that abstracts the error of all examples using tf.reduce_sum:
y = tf.placeholder(tf.float32)
squared_deltas = tf.square(linear_model - y)
loss = tf.reduce_sum(squared_deltas)
print(sess.run(loss, {x:[1,2,3,4], y:[0,-1,-2,-3]}))
# 输出
23.66

# We could improve this manually by reassigning the values of W and b to the perfect values of -1 and 1. A variable is initialized to the value provided to tf.Variable but can be changed using operations like tf.assign. For example, W=-1 and b=1 are the optimal parameters for our model. We can change W and b accordingly:
fixW = tf.assign(W, [-1.])
fixb = tf.assign(b, [1.])
sess.run([fixW, fixb])
print(sess.run(loss, {x:[1,2,3,4], y:[0,-1,-2,-3]}))
# 输出
0.0

# We guessed the "perfect" values of W and b, but the whole point of machine learning is to find the correct model parameters automatically. We will show how to accomplish this in the next section.
```


