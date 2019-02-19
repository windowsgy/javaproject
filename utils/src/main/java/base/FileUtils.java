package base;

import java.io.*;
import java.util.*;

public class FileUtils {
    /**
     * 判断路径是否为一个文件
     *
     * @param filePath 文件路径
     * @return boolean
     */
    public boolean isFile(String filePath) {
        try{
            File path = new File(filePath);
            return path.exists() && path.isFile();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }


    /**
     * 判断路径是否为一个目录
     *
     * @param dirPath 目录路径
     * @return boolean
     */
    public boolean isDir(String dirPath) {
        try {
            File path = new File(dirPath);
            return path.exists() && path.isDirectory();
        } catch (Exception e) {
            Log.error(e.getMessage());
            return false;
        }

    }

    /**
     * 创建目录
     *
     * @param path 路径
     */
    public boolean createDir(String path) {
        try {
            File file = new File(path);
            if (file.exists() && file.isDirectory()) {
                Log.warn("isExists :"+path);
                return false;
            } else {
                return file.mkdir();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除目录
     *
     * @param path 路径
     */
    public boolean delDir(String path) {
        try{
            File file = new File(path);
            if (!file.exists() || !file.isDirectory()) {//如果目录不存在返回
                Log.warn("Dir Not Exists :"+path);
                return false;
            }
            return file.delete();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除目录中的文件
     *
     * @param path 路径
     */
    public boolean deleteFiles(String path) {
        boolean bool = false;
        try {
            File files = new File(path);
            if (!files.exists() || !files.isDirectory()) {//如果目录不存在返回
                Log.warn("dir not exists :" + path);
                return bool;
            }
            String[] file = files.list();
            if (file == null) {
                Log.warn("dir is null :" + path);
                return bool;
            } else {
                for (String aFile : file) {
                    String full_filePath = files.getPath() + "/" + aFile;
                    File f = new File(full_filePath);
                    bool = f.delete();
                    if(!bool){
                        return bool;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return bool;
    }

    /**
     * @param path       文件路径
     * @param lineNumber 从第几行开始读取
     * @param code       文件编码
     * @return list
     */
    public List<String> read2List(String path, long lineNumber, String code) {
        List<String> list = new ArrayList<>();//返回结果
        BufferedReader br;
        try {
            File file = new File(path);
            if (!file.exists() || !file.isFile()) {
                Log.warn("path is exists"+path);
                return list;
            }
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), code));
            String line;
            int lineCtl = 0; //读取位置控制
            //int loadLineCount = 0;//读取行数计数
            // 一次读入一行，直到读入null为文件结束
            while ((line = br.readLine()) != null) {
                lineCtl++;
                if (lineCtl >= lineNumber) {//如果当前行号大于指定行号
                    //loadLineCount++;
                    list.add(line.trim());
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * 创建文件
     *
     * @param path Path
     */
    public boolean createFile(String path) {
        try{
            File file = new File(path);
            if (file.exists() && file.isFile()) {
                Log.warn(path +" : path is exists");
                return false;
            }
            return file.createNewFile();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 删除文件
     *
     * @param path path
     * @return boolean
     */
    public boolean delFile(String path) {
        try {
            File file = new File(path);
            if (!file.exists() && !file.isFile()) {
                Log.warn(path+" :not exists");
                return false;
            }else {
                return file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 字符串写入文件方法
     *
     * @param str  字符串
     * @param path 文件路径
     */
    public boolean wrStr2File(String str, String path) {
        try {
            File file = new File(path);
            if(!file.exists() && !file.isFile()){
                Log.error(path+" :not exists");
                return false;
            }
            PrintWriter outToFile = new PrintWriter(new BufferedWriter(new FileWriter(file.getPath())));
            outToFile.print(str);
            outToFile.flush();
            outToFile.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param str  字符串
     * @param path 文件路径
     * @param code 字符编码
     */
    public boolean wrStr2File(String str, String path, String code) {
        try {
            File file = new File(path);
            if(!file.exists() && !file.isFile()){
                Log.error("not exists :"+path);
                return false;
            }
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), code));
            writer.write(str);
            writer.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 追加写入文件
     *
     * @param path 文件路径
     * @param str  追加字符串
     */
    public boolean wrStrAdd2File(String str, String path) {
        try {
            File file = new File(path);
            if(!file.exists() && !file.isFile()){
                Log.error(path+" :not exists");
                return false;
            }
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(path, true);
            writer.write(str);
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false ;
        }
    }

    /**
     * 获取文件名称到List
     *
     * @param path 路径
     * @return List
     */
    public List<String> getFilesName(String path) {
        List<String> list = new ArrayList<>();//返回结果
        try {
            File file = new File(path);
            if(!file.exists() && !file.isDirectory()){
                Log.error(path+" :not exists");
            }else {
                list = Arrays.asList(Objects.requireNonNull(file.list()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 判断文件的编码格式
     *
     * @param filePath filePath
     * @return 文件编码格式
     */
    public String codeString(String filePath) {
        String code = "dont know";
        File file = new File(filePath);
        try {
            BufferedInputStream bin = new BufferedInputStream(new FileInputStream(file));
            int p = (bin.read() << 8) + bin.read();
            //其中的 0xefbb、0xfffe、0xfeff、0x5c75这些都是这个文件的前面两个字节的16进制数
            switch (p) {
                case 0xefbb:
                    code = "UTF-8";
                    break;
                case 0xfffe:
                    code = "Unicode";
                    break;
                case 0xfeff:
                    code = "UTF-16BE";
                    break;
                case 0x5c75:
                    code = "ANSI|ASCII";
                    break;
                default:
                    code = "GBK";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }

    /**
     * 读取文件第一行
     *
     * @param path 文件路径
     * @return L
     */
    public String readFirstLine(String path, String code) {
        String firstLine = null;//返回结果
        try {
            File file = new File(path);
            if(!file.exists() && !file.isFile()){
                Log.error(path+" :not exists");
            }else {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), code));
                firstLine = br.readLine().trim();
                br.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return firstLine;
    }


    /**
     * 判断目录中全是文件
     *
     * @return boolean
     */
    private boolean allFileInSubDir(String path) {
        File dir = new File(path);
        if (!dir.exists() && !dir.isDirectory()) {
            Log.warn("dir not exists :"+path);
            return false;
        }
        String[] fileArr = dir.list();
        if (fileArr != null && fileArr.length > 0) {
            for (String aFileArr : fileArr) {
                File file = new File(path + "\\" + aFileArr);
                if (!file.isFile()) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }


    /**
     * file read to str
     *
     * @param path       path
     * @param lineNumber lineNumber
     * @param code       coder
     * @return string
     */
    public String read2Str(String path, long lineNumber, String code) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br;
        try {
            File file = new File(path);
            if(!file.exists() && !file.isFile()){
                Log.error("not exists :"+path);
                return null;
            }

            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), code));
            String line;
            int lineCtl = 0; //读取位置控制
            //int loadLineCount = 0;//读取行数计数
            // 一次读入一行，直到读入null为文件结束
            while ((line = br.readLine()) != null) {
                lineCtl++;
                if (lineCtl >= lineNumber && !("".equals(line.trim()))) {//如果当前行号大于指定行号
                    //loadLineCount++;
                    sb.append(line.trim()).append("\r\n");
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return sb.toString();
    }


    /**
     * @param path     dir
     * @param charCode charCode
     * @return map
     */
    public Map<String, List<String>> loadDirFiles(String path, String charCode) {
        // format source data path is exist
        if (!isDir(path)) {
            Log.error("Path does not exist\n" +
                    "Path is not a directory");
            return null;
        }
        //如果分析数据目录中全部都是文件
        if (!allFileInSubDir(path)) {
            Log.error("This path contains directories, not all files ");
            return null;
        }
        Map<String, List<String>> filesMap = new HashMap<>();
        List<String> files = getFilesName(path);
        for (String fileName : files) {
            String filePath = path + "\\" + fileName;
            List<String> fileList = read2List(filePath, 0, charCode);
            filesMap.put(fileName, fileList);
        }
        return filesMap;
    }
}
