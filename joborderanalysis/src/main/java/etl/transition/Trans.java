package etl.transition;

import java.util.List;

public class Trans {

    /**
     * 工单系统下载文件转换方法
     *
     * @param list 文件内容
     * @return String
     */

    public static String accessOrders(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String line : list) {
            if (line.contains("\r")) {
                line = line.replace("\r", "");
            }
            if (line.contains("\n")) {
                line = line.replace("\n", "");
            }
            if (line.contains("<th>")) {
                line = line.replace("<th>", "");
            }
            if (line.contains("</th>")) {
                line = line.replace("</th>", "");
            }
            if (line.contains("<td>")) {
                line = line.replace("<td>", "");
            }
            if (line.contains("</td>")) {
                line = line.replace("</td>", "||");
            }
            if (line.contains("<div")) {
                line = line.replace("<div", "");
            }
            if (line.contains("</div>")) {
                line = line.replace("</div>", "");
            }
            if (line.contains("<tr>")) {
                line = line.replace("<tr>", "");
            }
            if (line.contains("</tr>")) {
                line = line.replace("</tr>", "\r\n");
            }
            if (line.contains("align=\"center\">")) {
                line = line.replace("align=\"center\">", "");
            }
            if (line.contains("&nbsp;")) {
                line = line.replace("&nbsp;", "");
            }
            if (line.contains("<td style=\"vnd.ms-excel.numberformat:@\">")) {
                line = line.replace("<td style=\"vnd.ms-excel.numberformat:@\">", "");
            }
            sb.append(line);
        }
        return sb.toString();
    }
}
