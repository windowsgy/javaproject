package apps

import base.Log
import org.apache.hadoop.io.{LongWritable, Text}
import org.apache.hadoop.mapred.TextInputFormat
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

/**
  * Created by jlgaoyuan on 2018/11/8.
  */
object ReaderGBK {
  /**
    * 方法实现Spark读取GBK编码格式文件
    * @param sc sparkContext
    * @param path 文件路径
    * @return RDD[String]
    */
  def transfer(sc:SparkContext,path:String):RDD[String]={
    Log.debug("load read GBK")
    sc.hadoopFile(path,classOf[TextInputFormat],classOf[LongWritable],classOf[Text],1)
      .map(p => new String(p._2.getBytes, 0, p._2.getLength, "GBK"))
  }
}
