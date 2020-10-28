package com.ym123.rdd.keyvalue

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

/**按K重新分区
 * @author ymstart
 * @create 2020-09-23 18:42
 */
object Test01_PartitionBy {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val rdd: RDD[(Int, String)] = sc.makeRDD(List((1,"hello"),(2,"hi"),(2,"Tom"),(3,"Jerry")),3)
    val partitionByRDD: RDD[(Int, String)] = rdd.partitionBy(new HashPartitioner(2))
    //partitionByRDD.collect().foreach(println)
    //打印对应分区数据
    val indexRDD: RDD[(Int, (Int, String))] = partitionByRDD.mapPartitionsWithIndex(
      (index, datas) => datas.map((index, _))
    )
    indexRDD.collect().foreach(println)
    /*
    (0,(2,hi))
    (0,(2,Tom))
    (1,(1,hello))
    (1,(3,Jerry))
     */

    //4.关闭连接
    sc.stop()
  }
}
