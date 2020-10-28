package com.ym123.rdd.action

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author ymstart
 * @create 2020-09-25 11:53
 */
object Test05_save {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val rdd: RDD[Int] = sc.makeRDD(List(1,2,4,22,11,44),2)

    //保存为文件
    //rdd.saveAsTextFile("output5")
    //序列化为对象保存为文件
    //rdd.saveAsObjectFile("output6")
    rdd.map((_,1)).saveAsSequenceFile("output7")
    //4.关闭连接
    sc.stop()
  }
}
