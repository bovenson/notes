# Keyed Segmented Context

创建keyed segmented context语法如下:

```sql
create context context_name partition [by]
  event_property [and event_property [and ...]] from stream_def
  [, event_property [...] from stream_def]
  [, ...]
```

下面的语句创建一个SegmentedByCustomer context, 根据BankTxn事件类型custId属性的值来选择该事件所属的context partition.

```sql
create context SegmentedByCustomer partition by custId from BankTxn
```

下面的语句引用上面创建的context计算每个客户的每个账户的总提款量.

```sql
context SegmentedByCustomer
select custId, account, sum(amount) from BankTxn group by account
```

下面的句子引用上面创建的context, 检测同一个客户第一次提款超过400, 十分钟内紧接着第二次提款也超过400.

```sql
context SegmentedByCustomer
select * from pattern [
  every a=BankTxn(amount > 400) -> b=BankTxn(amount > 400) where timer:within(10 minutes)
]
```

The EPL statement that refers to a keyed segmented context must have at least one filter expression, at any place within the EPL statement that looks for events of any of the event types listed in the context declaration.

下面的例子是错的:

```sql
// Neither LoginEvent nor LogoutEvent are listed in the context declaration
context SegmentedByCustomer
select * from pattern [every a=LoginEvent -> b=LogoutEvent where timer:within(10 minutes)]
```

