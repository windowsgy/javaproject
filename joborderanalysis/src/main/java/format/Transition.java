package format;

import base.FileUtils;

import java.util.List;

class Transition {

    /**
     * 工单系统下载文件转换方法
     * @param filePath 文件路径
     * @return String
     */
    static String accessOrders(String filePath){
        FileUtils fileUtils = new FileUtils();
        String fileCode = fileUtils.getFileCode(filePath);
        List<String> list = fileUtils.read2List(filePath,0,fileCode);
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
            line = line.replace(" ","");
            sb.append(line);
        }
        return sb.toString();
    }
}
