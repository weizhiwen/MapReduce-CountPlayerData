package com.weizhiwen.reducer;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.weizhiwen.bean.NBAPlayer;

public class NBAPlayerCountReducer extends Reducer<Text, NBAPlayer, Text, NBAPlayer> {

  @Override
  protected void reduce(Text key, Iterable<NBAPlayer> values, Reducer<Text, NBAPlayer, Text, NBAPlayer>.Context context)
      throws IOException, InterruptedException {
     int pointSum = 0; 
     int reboundSum = 0;
     int assistSum = 0;
     int stealSum = 0;
     int blockSum = 0;
     
     // 遍历所有的 bean，将球员的各项数据进行累加
     for(NBAPlayer bean : values) {
       pointSum += bean.getPoints();
       reboundSum += bean.getRebounds();
       assistSum += bean.getAssists();
       stealSum += bean.getSteals();
       blockSum += bean.getBlocks();
     }
     
     // 输出计算后的球员数据
     NBAPlayer resultBean = new NBAPlayer(pointSum, reboundSum, assistSum, stealSum, blockSum);
     context.write(key, resultBean);
  }
  

}
