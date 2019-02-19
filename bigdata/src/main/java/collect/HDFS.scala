package collect

import java.io._
import java.util

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, FileUtil, Path}
import org.apache.hadoop.io.IOUtils


/**
  * hdfs文件夹操作类
  */
object HDFS {


  def getFS(): FileSystem = {
    System.setProperty("hadoop", "hdfs")
    val conf = new Configuration()
    FileSystem.get(conf)
  }

  /**
    * 关闭FileSystem
    * @param fileSystem HDFS文件系统
    */
  def closeFS(fileSystem: FileSystem) {
    if (fileSystem != null) {
      try {
        fileSystem.close()
      } catch {
        case e: IOException => e.printStackTrace()
      }
    }
  }

  def isExists (hdfsFilePath:String):Boolean ={
    val fileSystem = getFS()
    fileSystem.exists(new Path(hdfsFilePath))
  }

  def filesIsExists (hdfsFilePath:String): Boolean ={
    val fileSystem = getFS()
    fileSystem.isFile(new Path(hdfsFilePath))
  }


  ///////////////////////////////////////////////////////////////////////////


  /**
    * ls
    * @param hdfsFilePath HDFS文件路径
    */
  def listFiles(hdfsFilePath: String): Unit = {
    val fileSystem = getFS()
    val fstats = fileSystem.listStatus(new Path(hdfsFilePath))
    try {
      for (fstat <- fstats) {
        if (fstat.isDirectory()) {
          println("directory")
        } else {
          println("file")
        }
        println("Permission:" + fstat.getPermission())
        println("Owner:" + fstat.getOwner())
        println("Group:" + fstat.getGroup())
        println("Size:" + fstat.getLen())
        println("Replication:" + fstat.getReplication())
        println("Block Size:" + fstat.getBlockSize())
        println("Name:" + fstat.getPath())
        println("#############################")
      }

    } catch {
      case e: IOException => e.printStackTrace()
    } finally {
      if (fileSystem != null) {
        try {
          fileSystem.close()
        } catch {
          case e: IOException => e.printStackTrace()
        }
      }
    }
  }

  def ls(fileSystem: FileSystem, path: String)= {
    println("list path:" + path)
    val fs = fileSystem.listStatus(new Path(path))
    val listPath: Array[Path] = FileUtil.stat2Paths(fs)
    for (p <- listPath) {
      println(p)
    }
    println("----------------------------------------")
  }

  /**
    * 创建目录
    * @param hdfsFilePath
    */
  def mkdir(hdfsFilePath: String) = {
    val fileSystem = getFS()
    try {
      val success = fileSystem.mkdirs(new Path(hdfsFilePath))
      if (success) {
        println("Create directory or file successfully")
      }
    } catch {
      case e: IllegalArgumentException => e.printStackTrace()
      case e: IOException => e.printStackTrace()
    } finally {
      this.closeFS(fileSystem)
    }
  }

  /**
    * 删除文件或目录
    *
    * @param hdfsFilePath
    * @param recursive 递归
    */
  def rm(hdfsFilePath: String, recursive: Boolean): Unit = {
    val fileSystem = this.getFS()
    val path = new Path(hdfsFilePath)
    try {
      if (fileSystem.exists(path)) {
        val success = fileSystem.delete(path, recursive)
        if (success) {
          System.out.println("delete successfully")
        }
      }

    } catch {
      case e: IllegalArgumentException => e.printStackTrace()
      case e: IOException => e.printStackTrace()
    } finally {
      this.closeFS(fileSystem)
    }
  }


  /**
    * 上传文件到HDFS
    * @param localPath
    * @param hdfspath
    */
  def write(localPath: String, hdfspath: String) {

    val inStream = new FileInputStream(
      new File(localPath)
    )
    val fileSystem = this.getFS()
    val writePath = new Path(hdfspath)
    val outStream = fileSystem.create(writePath)

    try {
      IOUtils.copyBytes(inStream, outStream, 4096, false)
    } catch {
      case e: IOException => e.printStackTrace()
    } finally {
      IOUtils.closeStream(inStream)
      IOUtils.closeStream(outStream)
    }

  }

  /**
  //    * 上传文件到HDFS
//    *
//    * @param localFilePath
//    * @param hdfsFilePath
//    */
  //  def put(localFilePath: String, hdfsFilePath: String) = {
  //    val fileSystem = this.getFS()
  //    try {
  //      val fdos = fileSystem.create(new Path(hdfsFilePath))
  //      val fis = new FileInputStream(new File(localFilePath))
  //      IOUtils.copyBytes(fis, fdos, 1024)
  //
  //    } catch {
  //      case e: IllegalArgumentException => e.printStackTrace()
  //      case e: IOException => e.printStackTrace()
  //    } finally {
  //      IOUtils.closeStream(fileSystem)
  //    }
  //  }


  /**
    * 打印hdfs上的文件内容
    *
    * @param hdfsFilePath
    */
  def cat(hdfsFilePath: String) {
    val fileSystem = this.getFS()
    //val readPath = new Path(hdfsFilePath)
    //val inStream = fileSystem.open(readPath)
    val inStream = fileSystem.open(new Path(hdfsFilePath))



    try {
      IOUtils.copyBytes(inStream, System.out, 4096, false)
    } catch {
      case e: IOException => e.printStackTrace()
    } finally {
      IOUtils.closeStream(inStream)
    }
  }

  /***
    * 读取HDFS文件到LIST
    * @param hdfsFilePath
    */
  def read2list(hdfsFilePath: String) :util.ArrayList[String]={
    val resultList = new util.ArrayList[String]
    val fileSystem = this.getFS()
    //val readPath = new Path(hdfsFilePath)
    //val inStream = fileSystem.open(readPath)
    val fs = fileSystem.open(new Path(hdfsFilePath))



    val bis = new BufferedReader(new InputStreamReader(fs, "UTF-8"))
    var line=bis.readLine

    try {
      while ((line != null)) {
        resultList.add(line)
        line=bis.readLine
      }
    } catch {
      case e: IOException => e.printStackTrace()
    } finally {
      IOUtils.closeStream(fs)
      bis.close()
    }
    resultList
  }


  /**
    * 下载文件到本地
    *
    * @param localFilePath
    * @param hdfsFilePath
    */
  def get(localFilePath: String, hdfsFilePath: String) {
    val fileSystem = this.getFS()
    try {
      val fsis = fileSystem.open(new Path(hdfsFilePath))
      val fos = new FileOutputStream(new File(localFilePath))
      IOUtils.copyBytes(fsis, fos, 1024)
    } catch {
      case e: IllegalArgumentException => e.printStackTrace()
      case e: IOException => e.printStackTrace()
    } finally {
      IOUtils.closeStream(fileSystem)
    }
  }
}