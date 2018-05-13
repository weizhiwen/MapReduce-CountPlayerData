package com.weizhiwen.map;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.weizhiwen.bean.NBAPlayer;

public class NBAPlayerCountMapper extends Mapper<LongWritable, Text, Text, NBAPlayer> {

  @Override
  protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, NBAPlayer>.Context context)
      throws IOException, InterruptedException {
    // 将一行的内容转成 string 
    String line = value.toString();

    // 用空格切割字段
    String[] fileds = StringUtils.split(line, " ");

    // 取出球员名字
    String PlayerName = fileds[0];
    
    // 取出球员的各项数据
    int points = Integer.parseInt(fileds[1]);
    int rebounds = Integer.parseInt(fileds[2]);
    int assists = Integer.parseInt(fileds[3]);
    int steals = Integer.parseInt(fileds[4]);
    int blocks = Integer.parseInt(fileds[5]);
    
    context.write(new Text(PlayerName), new NBAPlayer(points, rebounds, assists, steals, blocks));
  }
}
