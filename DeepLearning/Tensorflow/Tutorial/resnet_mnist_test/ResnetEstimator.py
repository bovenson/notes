import tensorflow as tf
from tensorflow.contrib import slim
from tensorflow.contrib.slim import nets
from tensorflow.contrib.learn.python.learn.estimators import model_fn as model_fn_lib

from resnet_mnist_test import cifar10_input


def resnet_50_layers(x, number_of_classes, mode):
    with slim.arg_scope(nets.resnet_utils.resnet_arg_scope(is_training=mode == tf.contrib.learn.ModeKeys.TRAIN,
                                                           batch_norm_decay=0.85)):
        with slim.arg_scope([slim.conv2d], biases_initializer=None):
            out, end_points = nets.resnet_v2.resnet_v2_50(x, num_classes=number_of_classes, global_pool=True)
            out = tf.squeeze(out, squeeze_dims=[1, 2])
    return out, end_points


class ResnetEstimator():
    def __init__(self, number_of_classes=1):
        self.number_of_classes = number_of_classes

    def __call__(self, model_dir='model/', params=(('learning_rate', 0.001))):
        return tf.contrib.learn.Estimator(self.create_estimator, model_dir=model_dir, params=dict(params))

    def create_estimator(self, features, targets, mode, params):
        if params is None:
            params = {'learning_rate': 0.001}
        elif 'learning_rate' not in params:
            params['learning_rate'] = 0.001

        predictions, end_points = resnet_50_layers(features, self.number_of_classes, mode)
        predictions_dict = {'regression': predictions}
        loss = tf.losses.mean_squared_error(targets[:, tf.newaxis], predictions)
        eval_metric_ops = {
            "rmse":
                tf.metrics.root_mean_squared_error(
                    tf.to_float(targets), predictions)
        }

        train_op = tf.contrib.layers.optimize_loss(
            loss=loss,
            global_step=tf.contrib.framework.get_global_step(),
            learning_rate=params["learning_rate"],
            optimizer="Adam")

        return model_fn_lib.ModelFnOps(mode, predictions_dict, loss, train_op,
                                       eval_metric_ops=eval_metric_ops)


if __name__ == '__main__':
    tf.logging.set_verbosity(tf.logging.INFO)
    model_params = [('learning_rate', 0.001), ('other_parameter', 5)]
    estimator = ResnetEstimator(number_of_classes=1)
    regressor = estimator(params=model_params)

    cifar10_input.maybe_download_and_extract()
    test_x, test_y = cifar10_input.test_inputs()

    regressor.fit(input_fn=cifar10_input.train_inputs, steps=5000)
    regressor.evaluate(input_fn=cifar10_input.test_inputs)
    pred = regressor.predict(test_x)
