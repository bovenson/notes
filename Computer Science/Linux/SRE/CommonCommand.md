```shell
#### 查看内存使用
$ free
              total        used        free      shared  buff/cache   available
Mem:      131920400     1039696    60624308    66203260    70256396    64246780
Swap:             0           0           0
## 改变显示单位
$ free -g
              total        used        free      shared  buff/cache   available
Mem:            125           0          57          63          67          61
Swap:             0           0           0
$ free -m
              total        used        free      shared  buff/cache   available
Mem:         128828        1014       59119       64651       68694       62741
Swap:             0           0           0
```

```shell
#### 硬盘使用量
$ df
Filesystem                   1K-blocks      Used  Available Use% Mounted on
/dev/mapper/vg_root-lv_root   20511356   2613276   16833120  14% /
devtmpfs                      65949600         0   65949600   0% /dev
tmpfs                         65960200  65960200          0 100% /dev/shm
tmpfs                         65960200    243060   65717140   1% /run
tmpfs                         65960200         0   65960200   0% /sys/fs/cgroup
/dev/sda1                       194241    157511      22394  88% /boot
/dev/mapper/vg_root-lv_home 2271859724 844917528 1311515280  40% /home
## 人类可读
$ df -h
Filesystem                   Size  Used Avail Use% Mounted on
/dev/mapper/vg_root-lv_root   20G  2.5G   17G  14% /
devtmpfs                      63G     0   63G   0% /dev
tmpfs                         63G   63G     0 100% /dev/shm
tmpfs                         63G  238M   63G   1% /run
tmpfs                         63G     0   63G   0% /sys/fs/cgroup
/dev/sda1                    190M  154M   22M  88% /boot
/dev/mapper/vg_root-lv_home  2.2T  806G  1.3T  40% /home
```

```shell
#### 文件夹总体占用空间
$ du -s work
839499392       work
# -s: summary

## 人类可读
$ du -sh work
801G    work
# -h: human readable

## 多个文件夹
$ du -sh work core
801G    work
4.2G    core

## 统计多个文件夹总占用量
$ du -shc work core
801G    work
4.2G    core
805G    total
```

```shell
#### 查看CPU详细信息
## 处理器数量: CPUs = Threads per core X cores per socket X sockets
$ lscpu | grep -E '^Thread|^Core|^Socket|^CPU\('
CPU(s):                8
Thread(s) per core:    2
Core(s) per socket:    4
Socket(s):             1

## 产看更多cpu概览信息
$ lscpu
...

## 查看cpu详细信息
$ cat /proc/cpuinfo 
processor	: 0
vendor_id	: GenuineIntel
cpu family	: 6
model		: 94
model name	: Intel(R) Core(TM) i7-6700 CPU @ 3.40GHz
stepping	: 3
microcode	: 0xc6
cpu MHz		: 844.289
cache size	: 8192 KB
physical id	: 0
siblings	: 8
core id		: 0
cpu cores	: 4
apicid		: 0
initial apicid	: 0
fpu		: yes
fpu_exception	: yes
cpuid level	: 22
wp		: yes
flags		: fpu vme de pse tsc msr pae mce cx8 apic sep mtrr pge mca cmov pat pse36 clflush dts acpi mmx fxsr sse sse2 ss ht tm pbe syscall nx pdpe1gb rdtscp lm constant_tsc art arch_perfmon pebs bts rep_good nopl xtopology nonstop_tsc aperfmperf pni pclmulqdq dtes64 monitor ds_cpl vmx smx est tm2 ssse3 sdbg fma cx16 xtpr pdcm pcid sse4_1 sse4_2 x2apic movbe popcnt tsc_deadline_timer aes xsave avx f16c rdrand lahf_lm abm 3dnowprefetch epb invpcid_single intel_pt ssbd ibrs ibpb stibp kaiser tpr_shadow vnmi flexpriority ept vpid fsgsbase tsc_adjust bmi1 hle avx2 smep bmi2 erms invpcid rtm mpx rdseed adx smap clflushopt xsaveopt xsavec xgetbv1 dtherm ida arat pln pts hwp hwp_notify hwp_act_window hwp_epp flush_l1d
bugs		: cpu_meltdown spectre_v1 spectre_v2 spec_store_bypass l1tf
bogomips	: 6815.75
clflush size	: 64
cache_alignment	: 64
address sizes	: 39 bits physical, 48 bits virtual
power management:

...
```

```shell
#### 搜索路径下符合规则的文件名
$ find . -name "*2\.cpp"
./PerformanceOptimizaition/Code/timecompare/02.cpp
./PerformanceOptimizaition/Code/itoa/src/sse2.cpp
./PerformanceOptimizaition/Code/itoa/src/tmp/branchlut2.cpp
./Algorithm/Other/02.cpp
./Algorithm/LeetCode/0000-0050/00152.cpp
./Algorithm/LeetCode/0000-0050/0012.cpp
./Algorithm/LeetCode/0000-0050/002.cpp
./Algorithm/SwordToOffer/12.cpp
./C++/Code/Pointer/02.cpp
./C++/Code/12.cpp
./C++/Code/NowCode/002.cpp
./C++/Code/Learn/02.cpp

#### 搜索路径下包含特定字符串的文件
$ grep -rnwl . -e 'greater' --include=\*.cpp
./C++/Code/NowCode/002.cpp
## 参数说明
#	-r OR -R: recursive
#	-i: 忽略大小写
#   -n: line number
#   -w: match whole word
#   -l: 只给出文件名
## 筛选文件
#   --include
#		grep --include=\*.{c,h} -rnw '/path/to/somewhere/' -e "pattern"
#   --exclude
#		grep --exclude=*.o -rnw '/path/to/somewhere/' -e "pattern"
#   --exclude-dir
#		grep --exclude-dir={dir1,dir2,*.dst} -rnw '/path/to/somewhere/' -e "pattern"
```

