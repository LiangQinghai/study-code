package cn.liangqinghai.study.rocksdb

import org.rocksdb.RocksDB

object OpenDbExample {

  def main(args: Array[String]): Unit = {
    RocksDB.loadLibrary();
  }

}
