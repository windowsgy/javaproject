import org.apache.spark.sql.{SQLContext, SparkSession}

/**
  * Created by jlgaoyuan on 2019/2/28.
  */
object BigDataMain {

  def main(args: Array[String]): Unit = {
    val sourcePath=args(0)
    val areaPath=args(1)
    val master="local"
    val appName="SparkSpp"
    val cores=8
    val mem ="4g"
    val tempPath = "D:\\sparkTemp\\"
    val shuffle = 1
    val hadoopHome="E:\\Software\\apache\\hadoop-2.6.5\\"
    //初始化sqlContext
    val sc = SparkSession.builder().master(master)
      .appName(appName)
      .config("spark.driver.cores", cores)
      .config("spark.executor.memory", mem)
      .config("spark.local.dir", tempPath)
      .config("spark.sql.shuffle.partitions", shuffle).getOrCreate()
    System.setProperty("hadoop.home.dir", hadoopHome)

    import sc.implicits._
    //加载数据文件
    val dataRdd = sc.sparkContext.textFile(sourcePath).map(x => x.split("\\|", -1)).map(x => (x(3), 1)).map(x => (x._1.substring(0, 4), x._2.toLong))
    println("line count =" + dataRdd.count())
    val dataDf = dataRdd.reduceByKey(_ + _).map(x => dataCase(x._1, x._2)).toDF()
    //创建数据统计临时表
    dataDf.createOrReplaceTempView("dataTable")
    //加载区域文件
    val areaDf = sc.sparkContext.textFile(areaPath).map(x => x.split("=", -1)).map(x => (x(0), x(1))).map(x => areaCase(x._1.substring(5, 9), x._2.trim)).toDF()
    //广播区域信息
    val broadcastAreaDf = sc.sparkContext.broadcast(areaDf)
    //注册临时表
    broadcastAreaDf.value.createOrReplaceTempView("areaTable")
    val sqlContext = new SQLContext(sc.sparkContext)
    val table = sqlContext.sql("select b.areaName,a.count from dataTable a left join areaTable b on a.dataAreaId = b.areaId")
    table.show()
  }

  case class dataCase(dataAreaId: String, count: Long) {}

  case class areaCase(areaId: String, areaName: String) {}

}
