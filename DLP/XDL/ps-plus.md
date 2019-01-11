---
tiltle: ps-plus
tags:
	- 深度学习平台
---

# main

```flow
st=>start: start
LoadPlugins=>operation: Load Plugins
ParseOptions=>operation: Parse Options
roleJudge=>condition: rule define is scheduler?
RunScheduler=>operation: Run Scheduler
RunServer=>operation: Run Server
e=>end: end

st->LoadPlugins
LoadPlugins->ParseOptions
ParseOptions->roleJudge
roleJudge(yes)->RunScheduler->e
roleJudge(no)->RunServer->e
```

## ServerRun

**获取参数**

```c++
google::InitGoogleLogging("ps-plus");
//  FLAGS_logtostderr = 1;
ps::OptionParser optParser;
optParser.addOption("-sp", "--scheduler_kv_path", "scheduler_kv_path", ps::OptionParser::OPT_STRING, true);
optParser.addOption("-si", "--server_id", "server_id", ps::OptionParser::OPT_INT32, true);
optParser.addOption("-smdense", "--streaming_model_dense", "streaming_model_dense", "");
optParser.addOption("-smsparse", "--streaming_model_sparse", "streaming_model_sparse", "");
optParser.addOption("-smhash", "--streaming_model_hash", "streaming_model_hash", "");
optParser.addOption("-bc", "--bind_cores", "bind_cores", ps::OptionParser::OPT_STRING, true);
if (!optParser.parseArgs(argc, argv)) {
  LOG(ERROR) << "Parse Server Args Error";
  return -1;
}

std::string scheduler_kv_path;
int server_id;
std::string streaming_model_dense;
std::string streaming_model_sparse;
std::string streaming_model_hash;
std::string bind_cores;

optParser.getOptionValue("scheduler_kv_path", scheduler_kv_path);
optParser.getOptionValue("server_id", server_id);
optParser.getOptionValue("streaming_model_dense", streaming_model_dense);
optParser.getOptionValue("streaming_model_sparse", streaming_model_sparse);
optParser.getOptionValue("streaming_model_hash", streaming_model_hash);
optParser.getOptionValue("bind_cores", bind_cores);
```

**启动Server**

```c++
ps::server::ServerService service(
    scheduler_kv_path, server_id,
    streaming_model_dense, streaming_model_sparse, 
    streaming_model_hash, bind_cores == "True" ? true : false);
ps::Status st = service.Init();
```

## SchedulerRun

**Parse Options**

```c++
ps::OptionParser optParser;
optParser.addOption("-sp", "--scheduler_kv_path", "scheduler_kv_path", ps::OptionParser::OPT_STRING, true);
optParser.addOption("-sn", "--server_num", "server_num", ps::OptionParser::OPT_STRING, true);
optParser.addOption("-snet", "--server_network_limit", "server_network_limit", ps::OptionParser::OPT_INT32, true); //MB/s
optParser.addOption("-smem", "--server_memory_limit", "server_memory_limit", ps::OptionParser::OPT_INT32, true); //MB
optParser.addOption("-sqps", "--server_query_limit", "server_query_limit", ps::OptionParser::OPT_INT32, true);
optParser.addOption("-cp", "--checkpoint_path", "checkpoint_path", "none://");
optParser.addOption("-smdense", "--streaming_model_dense", "streaming_model_dense", "");
optParser.addOption("-smsparse", "--streaming_model_sparse", "streaming_model_sparse", "");
optParser.addOption("-smhash", "--streaming_model_hash", "streaming_model_hash", "");
optParser.addOption("-bc", "--bind_cores", "bind_cores", ps::OptionParser::OPT_STRING, true);

if (!optParser.parseArgs(argc, argv)) {
  LOG(ERROR) << "argument error";
  return -1;
}

std::string scheduler_kv_path;
std::string checkpoint_path;
std::string server_num;
int server_network_limit;
int server_memory_limit;
int server_query_limit;
std::string streaming_model_dense;
std::string streaming_model_sparse;
std::string streaming_model_hash;
std::string bind_cores;

optParser.getOptionValue("scheduler_kv_path", scheduler_kv_path);
optParser.getOptionValue("checkpoint_path", checkpoint_path);
optParser.getOptionValue("server_num", server_num);
optParser.getOptionValue("server_network_limit", server_network_limit);
optParser.getOptionValue("server_memory_limit", server_memory_limit);
optParser.getOptionValue("server_query_limit", server_query_limit);
optParser.getOptionValue("streaming_model_dense", streaming_model_dense);
optParser.getOptionValue("streaming_model_sparse", streaming_model_sparse);
optParser.getOptionValue("streaming_model_hash", streaming_model_hash);
optParser.getOptionValue("bind_cores", bind_cores);

ps::scheduler::Placementer::Arg placement_arg {
  .net = (size_t)server_network_limit * (1 << 20),
  .mem = (size_t)server_memory_limit * (1 << 20),
  .query = (size_t)server_query_limit
};
```

**启动服务**

```c++
ps::scheduler::SchedulerImpl service(
    server_num, scheduler_kv_path, checkpoint_path, placement_arg,
    streaming_model_dense, streaming_model_sparse, 
    streaming_model_hash, bind_cores == "True" ? true : false);
ps::Status st = service.Start();
```

