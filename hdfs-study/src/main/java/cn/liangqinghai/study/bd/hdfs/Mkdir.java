package cn.liangqinghai.study.bd.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * 创建目录
 * @author Mr.Liang
 * @date 2020年2月22日
 */
public class Mkdir {

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {

        System.setProperty("HADOOP_HOME", "C:\\Coding\\hadoop");

        Configuration configuration = new Configuration();

//        configuration.set("fs.defaultFS", "hdfs://hadoop10:9000");

        FileSystem fileSystem = FileSystem.get(new URI("hdfs://hadoop10:9000"), configuration, "root");

        fileSystem.mkdirs(new Path("/liangqinghai/study"));

        fileSystem.close();

    }

}
