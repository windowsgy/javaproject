package init;

public enum FileType {
    Html("Html","html"),
    Excel("Excel","xsl");

    private final String name;
    private final String value;

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    FileType(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static boolean in (String str){
        for (FileType c : FileType.values()) {//循环判断
            if (c.name().equals(str)) {
                return true;
            }
        }
        return false;
    }
}
