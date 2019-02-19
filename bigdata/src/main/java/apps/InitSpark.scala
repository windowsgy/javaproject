package apps

import base.Log
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}
import params.Params._



/**
  * Created by jlgaoyuan on 2018/11/8.
  */

object InitSpark {
  def getSession: SparkSession = {
    Log.debug("init Spark Session ")
    val sc = SparkSession.builder().master(paramsMap.get("masterPath"))
      .appName(paramsMap.get("appName"))
      .config("spark.driver.cores", paramsMap.get("cores"))
      .config("spark.executor.memory",paramsMap.get("mem"))
      .config("spark.local.dir", paramsMap.get("tempPath"))
      .config("spark.sql.shuffle.partitions", paramsMap.get("shuffle"))
    System.setProperty("hadoop.home.dir", paramsMap.get("hadoopHome"))
    sc.getOrCreate()
  }

  def getContext: SparkContext = {
    Log.debug("init Spark Context")
    val conf = new SparkConf
    conf.setMaster(paramsMap.get("masterPath")) //master路径
    conf.set("spark.app.name", paramsMap.get("appName")) //应用名称
    conf.set("spark.driver.cores", paramsMap.get("cores")) //cpu内核数
    conf.set("spark.executor.memory", paramsMap.get("mem")) //executor设置内存大小
    conf.set("spark.local.dir", paramsMap.get("tempPath")) //spark临时文件路径
    conf.set("spark.sql.shuffle.partitions", paramsMap.get("shuffle")) //shuffle分区设置
    System.setProperty("hadoop.home.dir", paramsMap.get("hadoopHome"))
    val sc: SparkContext = new SparkContext(conf)
    sc
  }

}
