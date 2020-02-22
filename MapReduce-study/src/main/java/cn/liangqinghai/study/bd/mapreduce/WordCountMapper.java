package cn.liangqinghai.study.bd.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author Mr.Liang
 * @date 2020/2/22
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private Text text = new Text();

    private IntWritable count = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String s = value.toString();

        String[] strings = s.split(" ");

        for (String string : strings) {
            text.set(string);
            context.write(text, count);
        }

    }
}
