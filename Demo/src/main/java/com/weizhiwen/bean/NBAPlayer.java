package com.weizhiwen.bean;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Writable;

public class NBAPlayer implements Writable{
  private int points; // 得分
  private int rebounds; // 篮板
  private int assists; // 助攻
  private int steals; // 抢断
  private int blocks; // 盖帽
  
  public NBAPlayer() {

  }

  public NBAPlayer(int points, int rebounds, int assists, int steals, int blocks) {
    this.points = points;
    this.rebounds = rebounds;
    this.steals = steals;
    this.blocks = blocks;
    this.assists = assists;
  }

  public int getPoints() {
    return points;
  }

  public void setPoints(int points) {
    this.points = points;
  }

  public int getRebounds() {
    return rebounds;
  }

  public void setRebounds(int rebounds) {
    this.rebounds = rebounds;
  }

  public int getSteals() {
    return steals;
  }

  public void setSteals(int steals) {
    this.steals = steals;
  }

  public int getBlocks() {
    return blocks;
  }

  public void setBlocks(int blocks) {
    this.blocks = blocks;
  }

  public int getAssists() {
    return assists;
  }

  public void setAssists(int assists) {
    this.assists = assists;
  }
  
  @Override
  public String toString() {
    return "    球员数据总栏 [总得分=" + points + ", 总篮板=" + rebounds + ", 总助攻=" + assists + ", 总抢断=" + steals
        + ", 总盖帽=" + blocks + "]";
  }

  public void write(DataOutput out) throws IOException {
    out.writeInt(points);
    out.writeInt(rebounds);
    out.writeInt(steals);
    out.writeInt(blocks);
    out.writeInt(assists);
  }

  @Override
  public void readFields(DataInput in) throws IOException {
    points = in.readInt();
    rebounds = in.readInt();
    steals = in.readInt();
    blocks = in.readInt();
    assists = in.readInt();
  }
}
