| 版本管理 |  修改日期  | 变化内容 | 撰写人 |
| :------: | :--------: | :------: | :----: |
|   V1.0   | 2020-03-24 |   初稿   | 梁庆海 |



[TOC]

# 1. 介绍

[英文连接](https://rocksdb.org/)

https://rocksdb.org/

[中文连接](https://rocksdb.org.cn/doc.html)

https://rocksdb.org.cn/doc.html

说白了就是一个嵌入式的`kv`存储引擎，`Java`使用是依赖`native`方法库(直接引入`jar`包就`ok`，不依赖系统环境)。

> Flink-1.10.0使用rocksdb状态后端时请在linux环境下使用，在windows中使用会报`can not create directory`错误，希望Flink后期修复这个bug

## 1.1 RocksDB基本组成

- `memtable`: 内存数据结构，即RocksDB内存表
- `logfile`: 顺序写入文件
- `sstfile`:  RocksDB数据存储位置

## 1.2 RocksDB写数据过程

​	写数据时先将数据写进`memtable`内存表中，然后顺序写入`logfile`中，当内存表数据超出阈值时，数据将会被写入到`sstfile`中，同时`logfile`中的数据也将被删除。

> 可以参考hadoop2中的SecondNameNode的fsimage和editslog

# 2. 安装

​	`Java`操作不用安装这个玩意，除非用`c++`/`python`访问就要安装系统类库，着里还是提供一下安装教程吧。

​	琢磨英文的参考一下[GitHub](https://github.com/facebook/rocksdb/blob/master/INSTALL.md)吧，下面提供`Centos`安装步骤，`windows`的有空再加吧，或者参考[GitHub](https://github.com/facebook/rocksdb/blob/master/INSTALL.md)自己琢磨。

- 安装依赖包[zlib](http://www.zlib.net/) 、[bzip2](http://www.bzip.org/) 、[lz4](https://github.com/lz4/lz4) 、[snappy](http://google.github.io/snappy/) 、[zstandard](http://www.zstd.net/) 、[gflags](https://gflags.github.io/gflags/)，安装教程[官网](https://github.com/facebook/rocksdb/blob/master/INSTALL.md)写的很清楚

- 下包，`wget [下载地址]`，这里找[Release](https://github.com/facebook/rocksdb/releases)

- 解压`tar -zxvf [下载包名]`，前提是`tar.gz`格式的，`zip`参考命令`unzip`

- 进入解压目录创建一个`build`目录存放编译后内容，命令不写

- 到`build`目录执行`cmake -DCMAKE_INSTALL_PREFIX=/usr/local/rocksdb ..`

- `make static_lib && make shared_lib && make install`，其实就安装`shared_lib`和`static_lib`就行，当然你也可以`make all`

- 该表环境变量, 请修改`/etc/profile`

  ```shell
  export CPLUS_INCLUDE_PATH=$CPLUS_INCLUDE_PATH:/usr/local/rocksdb/include/
  export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/usr/local/rocksdb/lib64/
  export LIBRARY_PATH=$LIBRARY_PATH:/usr/local/rocksdb/lib64/
  ```

# 3. 教程

## 3.1 术语

参考[这里](https://rocksdb.org.cn/doc/Terminology.html)

## 3.2 RocksDB c++

TODO

## 3.3 RocksDB python

TODO

## 3.4 RocksDB JAVA

### 3.4.1 RocksJava 三层结构

- `org.rocksdb`包里面的Java类，构成`RocksJava` API。Java用户只会直接接触到这一层。
- C++的JNI代码，提供Java API和Rock是DB之间的链接。
- C++层的RocksDB本身，并且编译成了一个native库，被JNI层使用。

### 3.4.2 maven 坐标

```xml
<dependency>
  <groupId>org.rocksdb</groupId>
  <artifactId>rocksdbjni</artifactId>
  <version>5.5.1</version>
</dependency>
```

### 3.4.3 打开库

```java
package cn.liangqinghai.study.rocksdb;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

/**
 * @author LiangQinghai
 * @Title OpenRocksDb
 * @ProjectName study-code
 * @Description
 * @date 3/24/2020 11:46 AM
 */
public class OpenRocksDb {

    public static void main(String[] args) throws RocksDBException {
        // 加载c++库
        RocksDB.loadLibrary();

        // 不存在即创建库
        Options options = new Options().setCreateIfMissing(true);

        //打开库
        RocksDB db = RocksDB.open(options, "/rocksdb");

    }

}
```

### 3.4.4 读写

```
package cn.liangqinghai.study.rocksdb;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

/**
 * @author LiangQinghai
 * @Title ReadWriteExample
 * @ProjectName study-code
 * @Description
 * @date 3/24/2020 11:59 AM
 */
public class ReadWriteExample {

    public static void main(String[] args) throws RocksDBException {

        RocksDB.loadLibrary();

        Options options = new Options().setCreateIfMissing(true);

        RocksDB db = RocksDB.open(options, "D:\\WorkSpace\\IDEA\\study-code\\rocksdb\\src\\main\\resources\\rocksdb");

        byte[] key = "key1".getBytes();
        byte[] value = "value".getBytes();

        db.put(key, value);

        byte[] bytes = db.get(key);

        System.out.println(new String(bytes));

    }

}
```

### 3.4.5 Options配置

- `setCreateIfMissing`:  db不存在即创建，不解释
- `setCompressionType`: 压缩类型，默认：SNAPPY_COMPRESSION
- `setWriteBufferSize`:  转换为sst文件之前内存表的最大存储量，默认64M
- `setMaxWriteBufferNumber`:  最大buffer数，默认2，当一个buffer正在写入数据到sst文件时，新的内存数据将会写入另外一个buffer中。
- `setIncreaseParallelism`:  压缩和刷新线程数，默认只有一个后台线程在工作
- `setMaxOpenFiles`: 最大打开文件数，默认-1
- `setMaxBackgroundCompactions`: 最大后台压缩线程数
- `setMaxBackgroundFlushes`: 最大后台刷新数

## 3.5 RocksDB in Flink

​	Flink中提供的RocksDB配置不多，都集中在类`org.apache.flink.contrib.streaming.state.RocksDBOptions`中

- `LOCAL_DIRECTORIES`: RocksDB数据路径，不设置就是临时路径，`Linux`和`Windows`路径并不通用，目前（`Flink-1.10.0`）`Windows`中使用RockDBStateBackend问题多。
- `TIMER_SERVICE_FACTORY`: 定时器，`ROCKSDB`和`HEAP`
- `CHECKPOINT_TRANSFER_THREAD_NUM`: sst文件和checkpoint转换线程数(download/upload thread)， 默认1
- `TTL_COMPACT_FILTER_ENABLED`: 压缩ttl启用，默认true
- `OPTIONS_FACTORY`: 主要配置，对应RocksDB的Options，默认`DefaultConfigurableOptionsFactory`, 可以自己实现(implements interface org.apache.flink.contrib.streaming.state.ConfigurableRocksDBOptionsFactory)
- `USE_MANAGED_MEMORY`: 使用`task slot`管理的内存空间



