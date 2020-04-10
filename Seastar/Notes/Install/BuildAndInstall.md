# Clone

```shell
$ git clone --recurse-submodules -j8 git@github.com:scylladb/seastar.git
```

# Install

## dpdk

```shell
$ git clone http://dpdk.org/git/dpdk
$ cd dpdk
$ export RTE_SDK=$(pwd)
$ export RTE_TARGET=x86_64-native-linuxapp-gcc  # depends on your env
$ make -j8 install T=$RTE_TARGET DESTDIR=/usr/local
```

## alter gcc/g++

```shell
# install alter
sudo update-alternatives --install /usr/bin/gcc gcc /usr/bin/gcc-8 50
sudo update-alternatives --install /usr/bin/g++ g++ /usr/bin/g++-8 50
sudo update-alternatives --install /usr/bin/gcc gcc /usr/bin/gcc-7 40
sudo update-alternatives --install /usr/bin/g++ g++ /usr/bin/g++-7 40

# config alter
sudo update-alternatives --config gcc
```

## seastar

```shell
./cooking.sh -t Release -- -DSeastar_DPDK=ON
```

