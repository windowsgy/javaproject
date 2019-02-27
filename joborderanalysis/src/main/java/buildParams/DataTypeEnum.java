package buildParams;

public enum DataTypeEnum {

    INSTALLORDER(0,"安装工单"),
    HISINSTALLORDER(1,"历史安装工单");

    private final int id;
    private final String value;

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    DataTypeEnum(int i, String value) {
        this.id = i;
        this.value = value;
    }




}
