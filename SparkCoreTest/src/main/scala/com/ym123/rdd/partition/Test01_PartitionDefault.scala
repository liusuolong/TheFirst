package com.ym123.rdd.partition

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**默认分区 RDD从集合中创建
 *
 * @author ymstart
 * @create 2020-09-22 18:29
 */
object Test01_PartitionDefault {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    val rdd: RDD[Int] = sc.makeRDD(Array(1,2,3,4))
    //默认分区根据cpu核数分区
    rdd.saveAsTextFile("output") //保存到本地文件

    //4.关闭连接
    sc.stop()
  }
}
