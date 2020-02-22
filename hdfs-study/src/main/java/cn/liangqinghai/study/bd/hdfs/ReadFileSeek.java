package cn.liangqinghai.study.bd.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * 定位文件
 *
 * @author Mr.Liang
 * @date 2020/2/22
 */
public class ReadFileSeek {

    public static void main(String[] args) throws Exception{
        readFirstBlock();
        readSecondBlock();
    }

    /**
     * 读取第一块文件，假设hdfs block大小是128M
     */
    private static void readFirstBlock() throws URISyntaxException, IOException, InterruptedException {

        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://hadoop10:9000"), configuration, "root");

        FSDataInputStream fsDataInputStream = fileSystem.open(new Path("/one.tar.gz"));

        FileOutputStream fileOutputStream = new FileOutputStream(new File("d:\\one.tar.gz.part1"));

        byte[] buff = new byte[1024];

        for (int i = 0; i < 1024 * 128; i++) {
            fsDataInputStream.read(buff);
            fileOutputStream.write(buff);
        }

        IOUtils.closeStream(fileOutputStream);
        IOUtils.closeStream(fsDataInputStream);

        fileSystem.close();
    }


    /**
     * 读取第二段
     */
    public static void readSecondBlock() throws Exception{
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop10:9000"), configuration, "root");

        FSDataInputStream fsDataInputStream = fs.open(new Path("one.tar.gz"));

        // 移动读取位置
        fsDataInputStream.seek(1024 * 1024 * 128);

        FileOutputStream fileOutputStream = new FileOutputStream(new File("d:\\one.tar.gz.part2"));

        IOUtils.copyBytes(fsDataInputStream, fileOutputStream, configuration);

        IOUtils.closeStream(fileOutputStream);
        IOUtils.closeStream(fsDataInputStream);

        fs.close();

    }

}
