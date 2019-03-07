package etl.orders;

import java.util.List;

public interface Orders{


    List<String> getList() ;

    /**
     * 设置路径
     */
    void setFilePath(String filePath);

    /**
     * 设置分割字符
     */
    void setSplitChar(String splitChar);

    /**
     * 设置分隔字符
     */
    void setIsolationChar(String isolationChar) ;

    /**
     * 加载爬虫数据方法
     */
    void loadData();

    /**
     * 加载excel数据方法
     */
    void loadExcelData();

    /**
     * 数据转换方法
     */
    void toList();

    /**
     * excel数据转换方法
     */
    void excelToList();

    /**
     * 添加字段方法
     */
    void add();

    /**
     * List<String>转换为 List<List<String>>
     */
    void toFieldsList();

    /**
     * 数据转换为Bean结构
     */
    void toBean();

    /**
     * 设置字段
     */
    void set();

    /**
     * 过滤方法
     */
    void filter();

    /**
     * 结构体List 转字JSON符串
     */
    void toJson();


}
