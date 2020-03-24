package cn.liangqinghai.study.rocksdb;

import org.rocksdb.*;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author LiangQinghai
 * @Title ReadWriteExample
 * @ProjectName study-code
 * @Description
 * @date 3/24/2020 11:59 AM
 */
public class ReadWriteExample {

    public static void main(String[] args) throws RocksDBException, InterruptedException {

        RocksDB.loadLibrary();

        Options options = new Options().setCreateIfMissing(true)
                .setCompressionType(CompressionType.SNAPPY_COMPRESSION)
                .setWriteBufferSize(128 * 1024 * 1024)
                .setMaxWriteBufferNumber(2 * 2)
                .setIncreaseParallelism(Runtime.getRuntime().availableProcessors())
                .setMaxOpenFiles(65535)
                .setDbPaths(Arrays.asList(new DbPath(new File("D:\\WorkSpace\\IDEA\\study-code\\rocksdb\\src\\main\\resources\\rocksdb\\data").toPath(), 100 * 1024 * 1024)))
                .setDbLogDir("D:\\WorkSpace\\IDEA\\study-code\\rocksdb\\src\\main\\resources\\rocksdb\\log")
                .setWalDir("D:\\WorkSpace\\IDEA\\study-code\\rocksdb\\src\\main\\resources\\rocksdb\\wal")
                .setStatsDumpPeriodSec(5)
                .setMaxBackgroundCompactions(2)
                .setMaxBackgroundFlushes(2);


        RocksDB db = RocksDB.open(options, "D:\\WorkSpace\\IDEA\\study-code\\rocksdb\\src\\main\\resources\\rocksdb");

        for (int i = 0; i < 300; i++) {
            byte[] key = ("key" + i).getBytes();
            byte[] value = ("value" + i).getBytes();

            db.put(key, value);
        }

        for (int i = 0; i < 300; i++) {
            System.out.println(new String(db.get(("key" + i).getBytes())));
        }

        RocksIterator rocksIterator = db.newIterator();

        rocksIterator.seekToFirst();
        while (rocksIterator.isValid()) {
            System.out.println(new String(rocksIterator.value()));
            rocksIterator.next();
        }

        TimeUnit.SECONDS.sleep(60);

    }

}
