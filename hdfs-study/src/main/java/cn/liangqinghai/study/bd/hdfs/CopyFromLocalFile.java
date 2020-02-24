package cn.liangqinghai.study.bd.hdfs;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Mr.Liang
 * @date 2020/2/22
 */
public class CopyFromLocalFile {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        Configuration configuration = new Configuration();
//        configuration.set("dfs.replication", "1");

        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop10:9000"), configuration, "root");

        fs.copyFromLocalFile(new Path("C:\\Users\\LiangQinghai\\Desktop\\hello.txt"), new Path("/liangqinghai/study/hello.txt"));

        fs.close();

    }

}
