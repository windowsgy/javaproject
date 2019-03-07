package init;

public enum DataTypeEnum {

    InstallOrders(0,"InstallOrders"),
    HistoryInstallOrders(1,"HistoryInstallOrders");

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

    public static boolean in (String str){
        for (DataTypeEnum c : DataTypeEnum.values()) {//循环判断
            if (c.name().equals(str)) {
                return true;
            }
        }
        return false;
    }

}
