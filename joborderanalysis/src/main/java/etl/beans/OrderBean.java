package etl.beans;



public interface OrderBean {

    String getOrderNum();

    void setId(String id);

    String getAcceptTime();

    void setCount(int i);

    void setTimesTamp(String timesTamp);

    void setImportTime(String time);

    void setImportFile(String filePath);
}
