---
title: ps-plus服务注册与发现
---

# ServerService

```flow
1=>start: Start
2=>operation: call ServerService::RegisterServer()
3=>operation: connect scheduler
4=>operation: request scheduler
0=>end: End

1->2->3->4->0
```

# Scheduler

```flow
1=>start: Start
2=>operation: SchedulerService::Start()
3=>operation: RegisterServerFunc
4=>operation: RegisterServer
5=>operation: add server
0=>end: End

1->2->3->4->5->0
```

