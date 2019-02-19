package collect.excel;


import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jlgaoyuan on 2018/6/16.
 * EXCEL提取工具
 */
public class ExcelUtils {

    /**
     * EXCEL 文件转换为map  EXCEL文件中每个表格对应一个Key,  Value List 每行 ，List 每列
     * 去掉了表头数据
     *
     * @param filePath excel文件路径
     * @return map
     */
    public static Map<String, List<List<String>>> xlsToString(String filePath) {
        Map<String, List<List<String>>> map = new HashMap<>();
        File xlsFile = new File(filePath);
        if (!xlsFile.exists()) { //判断文件是否存在
            return null;
        }
        try {
            Workbook wb = WorkbookFactory.create(xlsFile);
            int sheetNum = wb.getNumberOfSheets();//获取EXCEL表数量
            Sheet sheet;
            for (int sheetIndex = 0; sheetIndex < sheetNum; sheetIndex++) {//遍历sheet(index 0开始)
                sheet = wb.getSheetAt(sheetIndex);
                Row row;
                List<List<String>> rowList = new ArrayList<>(); //行列表
                int firstRowNum = sheet.getFirstRowNum();//首行号码
                int lastRowNum = sheet.getLastRowNum();//尾行号码
                for (int rowIndex = firstRowNum; rowIndex <= lastRowNum; rowIndex++) {//遍历row(行 0开始)
                    row = sheet.getRow(rowIndex);
                    if (null != row) {
                        List<String> cellList = new ArrayList<>();//列List
                        int firstCellNum = row.getFirstCellNum();//首列号
                        int lastCellNum = row.getLastCellNum();//尾列号
                        for (int cellIndex = firstCellNum; cellIndex < lastCellNum; cellIndex++) {//遍历cell（列 0开始）
                            Cell cell = row.getCell(cellIndex, Row.RETURN_BLANK_AS_NULL);
                            String cellValue;
                            // null 不进行添加
                            if (cell != null) {
                                switch (cell.getCellType()) {//数据类型判断并转换为字符串
                                    case Cell.CELL_TYPE_STRING: //如果是字符串
                                        cellValue = cell.getStringCellValue();
                                        break;
                                    case Cell.CELL_TYPE_NUMERIC://如果是数值类型
                                        if (DateUtil.isCellDateFormatted(cell)) {//如果是日期,按日期格式转换
                                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                            cellValue = formatter.format(cell.getDateCellValue());
                                        } else {//如果是数值，转换为字符串
                                            cellValue = new DecimalFormat("0").format(cell.getNumericCellValue());
                                        }
                                        break;
                                    case Cell.CELL_TYPE_BOOLEAN://boolean 类型
                                        cellValue = cell.getBooleanCellValue() + "";
                                        break;
                                    case Cell.CELL_TYPE_FORMULA://公式类型
                                        cellValue = cell.getCellFormula();
                                        break;
                                    default://其他情况 包括空值、非法字符、未知类型全部标注为 ""
                                        cellValue = "";
                                }
                                cellValue = cellValue.trim();
                                cellValue = cellValue.replace("\r", "");//替换换行
                                cellValue = cellValue.replace("\n", "");//替换回车

                            } else {
                                cellValue = "";
                            }
                            cellList.add(cellValue);//每列添加
                        }//end cells

                        Set set = new HashSet<>(cellList);//list转为set 判断所有字段是否都为""
                        if (!(set.size() == 1 && set.contains(""))) {//如果行内每一列数据不全部为"",才进行添加每行
                            rowList.add(cellList);//每行添加
                        }

                    }//end every row
                }//end rows
                rowList.remove(0);//去掉表头
                map.put(wb.getSheetName(sheetIndex), rowList);
            }//end sheets
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return map;
    }
}
