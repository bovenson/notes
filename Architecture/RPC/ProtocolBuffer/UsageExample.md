---
title: Protobuf Usage Example
tags:
	- Protobuf
categories:
	- Architecture
---

# 定义

```protobuf
syntax = "proto3";

message ModelDiffSenderConfig {
    string kafkaBrokerList = 1;
    string kafkaTopic = 2;
}
```

# 生成CPP文件

```shell
# generate proto struct
export LD_LIBRARY_PATH=${LD_LIBRARY_PATH}:`pwd`/deps/protobuf/lib `pwd`/deps/protobuf/bin/protoc --proto_path=`pwd`/protobuf/proto --cpp_out=`pwd`/protobuf/gen-cpp diffModel.proto model_diff_sender_config.proto
```

# 输出JSON文件

```cpp
//
// Created by bovenson on 18-10-11.
//
#include <iostream>
#include <fstream>
#include <fcntl.h>
#include <streambuf>

#include "google/protobuf/io/zero_copy_stream_impl_lite.h"
#include "google/protobuf/util/json_util.h"
#include "model_diff_sender_config.pb.h"

int main() {
  ModelDiffSenderConfig modelDiffSenderConfig;
  modelDiffSenderConfig.set_kafkabrokerlist("localhost");

  // write to json file
  std::string jsonStr;
  google::protobuf::util::JsonPrintOptions jsonOptions;
  jsonOptions.add_whitespace = true;
  jsonOptions.always_print_primitive_fields = true;
  google::protobuf::util::MessageToJsonString(modelDiffSenderConfig, &jsonStr, jsonOptions);

  std::ofstream out("config.json", std::ofstream::out | std::ofstream::trunc);
  if (out.is_open()) {
    out << jsonStr;
    std::cout << "Json output OK" << std::endl;
  } else {
    std::cout << "ERROR: open file failed" << std::endl;
  }
  out.close();
  return 0;
}
```

**config.json**

```json
{
 "kafkaBrokerList": "localhost",
 "kafkaTopic": ""
}
```

# 读取JSON配置文件

```c++
#include <iostream>
#include <fstream>
#include <fcntl.h>
#include <streambuf>
#include <google/protobuf/io/zero_copy_stream_impl_lite.h>
#include <google/protobuf/util/json_util.h>

bool loadConfig (const std::string &filePath, ModelDiffSenderConfig &config) {
    std::ifstream in(filePath, std::ofstream::in);
    if (in.is_open()) {
        std::string jsonStr((std::istreambuf_iterator<char>(in)), std::istreambuf_iterator<char>());
        google::protobuf::util::JsonStringToMessage(jsonStr, &config);
    } else {
        std::cout << "[ERROR] config file can't open" << std::endl;
        return false;
    }
    return true;
}

int main() {
  ModelDiffSenderConfig modelDiffSenderConfig;
  loadConfig("config.json", modelDiffSenderConfig);
  std::cout << modelDiffSenderConfig.kafkabrokerlist() << std::endl;
  return 0;
}
/** Output
localhost
*/
```

