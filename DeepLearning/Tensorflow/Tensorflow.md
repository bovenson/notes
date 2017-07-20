# Tensorflow

## zeros

```python
zeros(
    shape,
    dtype=tf.float32,
    name=None
)
```

- Creates a tensor with all elements set to zero.


- This operation returns a tensor of type `dtype` with shape `shape` and all elements set to zero.

```python
tf.zeros([3, 4], tf.int32) ==> [[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]]
```

#### Args:

- **shape**: Either a list of integers, or a 1-D `Tensor` of type `int32`.
- **dtype**: The type of an element in the resulting `Tensor`.
- **name**: A name for the operation (optional).

#### Returns:

A `Tensor` with all elements set to zero.

