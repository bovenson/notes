---
title: xdl source code analysis
---

# core

# data_io

# proto



# python

```flow
cpp=>operation: C++
pybind=>operation: pybind11
python=>operation: Python

cpp->pybind->python
```

## backend

- mxnet
  - hook
    - 重写了mx.sym.BatchNorm函数
  - convert
    - MX2XDL
      - convert_shape
      - convert_type
      - convert_initializer
    - XDL2MX
      - convert_type
  - backend [python adapter for mxnet]
    - create_name
    - recursive_make_placeholder
    - make_placeholder
    - serialize_graph
    - add_variable_inputs
    - get_initializer_and_args
    - flatten
    - flatten_list
    - mxnet_wrapper
    - ams_main
    - ams_gear
  - tf
  - hook
    - 重写 tensorflow.python.ops.variable_scope
  - convert
    - XDL2TF
      - convert_shape
      - convert_type
    - TF2XDL
      - convert_shape
      - convert_type
      - convert_initializer
  - backend  [python adapter for tensorflow]
    - recursive_make_placeholder
    - make_placeholder
    - serialize_graph
    - get_op_name
    - get_op_names
    - flatten
    - flatten_list
    - add_backprop_ops
    - add_variable_inputs
    - tf_wrapper
    - ams_main
    - ams_gear

## framework

- gradient
  - SparseGrad [class]
    - grad
    - indices
  - GradientManager [class]
    - def_gradient
    - def_gradient_internal
    - def_default_gradient
    - gradient_func
    - default_gradient_func
    - gradient
    - current_manager
  - GradientCalc [class]
    - run
    - result
    - feed_output_list
    - get_op_gradient
    - calc_gradient
    - get_gradient
    - simple_gradient_wrapper
  - methods
    - def_gradient
    - def_gradient_internal
    - def_default_gradient
    - gradient
    - get_sparse_grads
- session
  - Hook  [class]
    - create_session
    - run
    - before_run
    - after_run
  - Session [class]
    - _create_session
    - run
- variable
  - VarType [class]	
  - Variable [class]
  - methods
    - variable_info
    - get_variable_infos
    - get_variable_info
    - ...

## io

- DataIO [class]
  - add_path

## lib

## model_server

## ops

## pybind

## sparse_engine

## training

## utils