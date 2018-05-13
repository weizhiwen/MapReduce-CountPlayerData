package com.weizhiwen.count;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.weizhiwen.bean.NBAPlayer;
import com.weizhiwen.map.NBAPlayerCountMapper;
import com.weizhiwen.reducer.NBAPlayerCountReducer;

public class NBAPlayerCount {
  public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf);
    
    // 指定本类、mapper、reducer类
    job.setJarByClass(NBAPlayerCount.class);
    job.setMapperClass(NBAPlayerCountMapper.class);
    job.setReducerClass(NBAPlayerCountReducer.class);
    
    // 指定 mapper 输出 key 和 value 类
    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(NBAPlayer.class);
    
    // 指定 reducer 输出 key 和 value 类
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(NBAPlayer.class);
    
    // 制定输入和输出路径
    FileInputFormat.setInputPaths(job, new Path("hdfs://localhost:9000/input/nba"));
    FileOutputFormat.setOutputPath(job, new Path("hdfs://localhost:9000/output/nba"));
    
    // 在 hadoop 中运行结果
    boolean flag = job.waitForCompletion(true);
    if (flag) {
      System.out.println("Job is complete!");
    } else {
      System.out.println("Job is fail!");
    }
  }
}
