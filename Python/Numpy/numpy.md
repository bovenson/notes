# numpy

## random

### rand

创建一个给定shape的数组，并用[0,1]上的均匀分布的随机样本填充它。

```python
#### usage
rand(d0, d1, ..., dn)
```

```shell
>>> np.random.rand(3,2)
array([[ 0.14860045,  0.12085391],
       [ 0.60111547,  0.22030079],
       [ 0.06661041,  0.37634434]])
>>> np.random.rand(3)
array([ 0.00750005,  0.05250986,  0.79538867])
>>> np.random.rand(1)
array([ 0.47296359])
>>> np.random.rand(3, 2, 4)
array([[[ 0.66151425,  0.66879573,  0.38385449,  0.33088673],
        [ 0.95109961,  0.05667467,  0.15692034,  0.72006635]],

       [[ 0.7792517 ,  0.61330583,  0.78158503,  0.00783331],
        [ 0.29853045,  0.31145736,  0.91850084,  0.98779325]],

       [[ 0.12620205,  0.34380978,  0.77586762,  0.78010287],
        [ 0.65628447,  0.37491602,  0.91287225,  0.37615829]]])
```

### randn

```python
#### usage
randn(d0, d1, ..., dn)	# Return a sample (or samples) 
						# from the “standard normal” distribution.
```

### randint

```python
#### usage
randint(low[, high, size, dtype])	# Return random integers from low (inclusive) 
									# to high (exclusive).
```

### random_integers

```python
#### usage
random_integers(low[, high, size])	# Random integers of type np.int 
									# between low and high, inclusive.
```

### random_sample

```python
#### usage
random_sample([size])	# Return random floats in the half-open interval [0.0, 1.0).
```

### random

```python
#### usage
random([size])	# Return random floats in the half-open interval [0.0, 1.0).
```

### ranf

```python
#### usage
Return random floats in the half-open interval [0.0, 1.0).
```

### sample

```python
sample([size])	# Return random floats in the half-open interval [0.0, 1.0).
```

### choice

```python
choice(a[, size, replace, p])	# Generates a random sample from a given 1-D array
```

### byte

```python
#### usage
bytes(length)	# Return random bytes.
```

