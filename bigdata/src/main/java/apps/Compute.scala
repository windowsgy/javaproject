package apps

import base.Log
import org.apache.spark.SparkContext
import org.apache.spark.sql.{DataFrame, SparkSession}
import params.Params._

object Compute {

  def runTest(sc: SparkContext): Unit = {
    Log.info("Compute")
    val path = paramsMap.get("sourcePath")+"*"
    Log.info("load source filePath :"+path)
    val rdd = ReaderGBK.transfer(sc,path)
    val rddFilter = rdd.filter(x => x.length>0 && x.contains(" "))
    val rddFields = rddFilter.map(x => x.split(" ",-1)).map(x=> (x(1).trim,x(1).trim))
    val rddCount = rddFields.map(x => (x._1,1)).reduceByKey(_+_)
    Log.info(rddCount.count())
  }

  def runAccessOrdersJson(sc: SparkSession): Unit = {
    Log.info("Compute")
    val path = paramsMap.get("sourcePath")+"*"
    Log.info("load source filePath :"+path)
    val rdd: DataFrame = sc.read.json(path)
    rdd.printSchema();
    rdd.take(10).foreach { println }
  }



  def runAccessOrders(sc: SparkContext): Unit = {
    Log.info("Compute")
    val path = paramsMap.get("sourcePath")+"*"
    Log.info("load source filePath :"+path)
    val file = sc.textFile(path)
    file.take(10).foreach { println }
    val rdd = file.map(x => x.split("\\|\\|",-1))
    val rdd1 = rdd.map(x =>(x(35),1))
    rdd1.take(10).foreach{println}
    val rddCount = rdd1.map(x => (x._1,1)).reduceByKey(_+_).sortBy(_._2,false)

    rddCount.take(100).foreach{println}


  }
}
