import apps.{Compute, InitSpark}
import base.{FileUtils, LoadProperties, Log}
import params.Params._
//隐式转换java 与 scala集合类
//import collection.JavaConversions._

/**
  * Created by jlgaoyuan on 2018/11/8.
  */
object BigDataMain {
  def main(args: Array[String]): Unit = {
    if(args.length ==1){
      if("debug" == args(0)|| "DEBUG"==args(0)){
        Log.setDebug(true)
      }
    }
    val configFile = "spark.properties"
    LoadProperties.paramMap(configFile,paramsMap)
    val filesUtils = new FileUtils
    val filesPath =paramsMap.get("sourcePath")
    val filesName = filesUtils.getFilesName(filesPath)
    Log.debug("fileCount :"+filesName.size())
    val sc = InitSpark.getSession
    Compute.runHistoryInstallOrdersJson(sc)

  }
}
