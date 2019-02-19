package collect

import java.text.SimpleDateFormat
import java.util.Date

/**
    *
  */

object DateTimeUtil {
  /**
    *获取当前系统时间
    * @return String
    */
  def getNowDate(): String ={
      val now:Date = new Date()
      val dateFormat:SimpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss")
       dateFormat.format( now )
    }
  def getBeforeOneHour(): String ={
    val now:Date = new Date()
    val beforeDate = now.getTime - 60*60*1000
    val dateFormat:SimpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/HH")
    dateFormat.format(beforeDate )
  }
  def getBeforeOneDay(): String ={
    val now:Date = new Date()
    val beforeDate = now.getTime - 24*60*60*1000
    val dateFormat:SimpleDateFormat = new SimpleDateFormat("yyyy/MM/dd")
    dateFormat.format(beforeDate )
  }
  def getNowDay(): String ={
    val now:Date = new Date()
    val dateFormat:SimpleDateFormat = new SimpleDateFormat("yyyy/MM/dd")
    dateFormat.format(now )
  }
  def getBeforeOneMM(): String ={
    val now:Date = new Date()
    val  beforeDate = now.getMonth -1
    val dateFormat:SimpleDateFormat = new SimpleDateFormat("yyyy/MM")
    dateFormat.format(beforeDate )
  }

  /**
    * 计算时间差
    * @param start_time 开始时间
    * @param end_Time   结束时间
    * @return  Long
    */
    def getCoreTime(start_time:String,end_Time:String): Long ={
      val df:SimpleDateFormat=new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss")
      val begin:Date=df.parse(start_time)
      val end:Date = df.parse(end_Time)
      val between:Long=(end.getTime-begin.getTime)/1000//转化成秒
      between
     }

    def getLongTime(longTime:Long): String ={
      val startTime = "1970-01-01 00:00:00"
      val df:SimpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
      val begin:Date = df.parse(startTime)
      val times: Date = new Date( begin.getTime()+longTime)
      df.format(times)


    }

}
