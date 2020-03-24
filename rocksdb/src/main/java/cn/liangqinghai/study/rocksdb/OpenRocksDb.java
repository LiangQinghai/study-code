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
