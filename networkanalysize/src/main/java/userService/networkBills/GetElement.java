package userService.networkBills;

import userService.BaseOperation;
import userService.Params;
import org.openqa.selenium.*;
import base.LoadProperties;
import base.FileUtils;


import java.util.*;

public class GetElement {
    private static WebDriver bro;
    private static JavascriptExecutor broJs;
    //脚本
    private static final String CLICK = "arguments[0].click()";
    private static final String EDIT_BILL_JS = "setTimeout(function(){document.getElementsByTagName('button')[1].click()},100)";
    private static final String EDIT_STEP_JS = "setTimeout(function(){document.getElementsByTagName('button')[2].click()},100)";
    private static final String EDIT_STEP_ELENIUM_XPATH = "//FONT |//H3 | //P | //INPUT[@type = 'text'] | //TEXTAREA";
    private static final String EDIT_STEP_QUIT_XPACH = "//div[@align = 'center']/input[3]";
    private static String editBill;
    private static String editStep;
    private static FileUtils fileUtils = new FileUtils();

    public static void run() {
        String configFile = "userService.properties";
        LoadProperties.paramMap(configFile,Params.map);
        String outPath = Params.map.get("getOutPath");//获取采集基本路径
        String filePath = Params.map.get("getBillFilePath");//采集文件路径
        System.out.println(filePath);
        List<String> list = fileUtils.read2List(filePath, 0);
        System.out.println("查询工单数量 :" + list.size());
        String url = Params.map.get("url");
        String driverPath = Params.map.get("driverPath");
        String username = Params.map.get("username");
        String password = Params.map.get("password");
        BaseOperation baseOperation = new BaseOperation(driverPath, url, username, password);
        //初始化
        if (!baseOperation.ieInit()) {
            System.out.println("init error");
            return;
        }
        bro = baseOperation.getBro();
        broJs = baseOperation.getBroJs();
        //登陆
        if (!baseOperation.login()) {
            System.out.println("login error");
            return;
        }
        //进入工单管理页面
        if (!baseOperation.gotoBillManage()) {
            System.out.println("进入工单管理失败");
            return;
        }
        if (!baseOperation.gotoIntegratedQuery()) {
            System.out.println("进入综合查询失败");
            return;
        }
        //循环所有工单
        for (int i = 0; i < list.size(); i++) {
            System.out.println("number :" + i);
            if (!baseOperation.searchNetwokBill(list.get(i))) {//如果查询失败继续
                return;
            }
            if (!gotoEditBill()) {
               return;
            }
            if(!gotoEditStep()){
                return;
            }
            baseOperation.goBack();
            String outFilePath = outPath + list.get(i) + ".txt";
            fileUtils.createFile(outFilePath);
            String fileStr = editBill + editStep;
            fileUtils.wrStr2File(fileStr, outFilePath, "GBK");
        }

    }

    private static void sleep(long timeLong) {
        timeLong = timeLong * 1000;
        try {
            Thread.sleep(timeLong);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static boolean gotoEditBill() {
        System.out.println("进入修改工单页面");
        try {
            bro.switchTo().defaultContent();
            bro.switchTo().frame("mainFrame");
            bro.switchTo().frame("worktable");
            bro.switchTo().frame("workarea");
            sleep(1);
            String beginClickWindow = bro.getWindowHandle();//当前窗口
            //点击进入
            broJs.executeScript(EDIT_BILL_JS);
            sleep(1);
            bro.switchTo().alert();
            Set<String> handlers = bro.getWindowHandles();
            for (String winHandler : handlers) {
                if (!winHandler.equals(beginClickWindow)) {
                    bro.switchTo().window(winHandler);
                    //编辑工单
                    if(!getBill()){
                     return false;
                    }
                }
            }
            bro.switchTo().window(beginClickWindow);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }


    private static boolean gotoEditStep() {
        System.out.println("进入修改步骤信息页面");
        try {
            bro.switchTo().defaultContent();
            bro.switchTo().frame("mainFrame");
            bro.switchTo().frame("worktable");
            bro.switchTo().frame("workarea");
            sleep(3);
            String beginClickWindow = bro.getWindowHandle();
            broJs.executeScript(EDIT_STEP_JS);
            sleep(3);
            bro.switchTo().alert();
            Set<String> handlers = bro.getWindowHandles();
            for (String winHandler : handlers) {
                if (!winHandler.equals(beginClickWindow)) {
                    bro.switchTo().window(winHandler);
                    if(!getStep()){
                        return false;
                    }
                }
            }
            bro.switchTo().window(beginClickWindow);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    private static boolean getBill() {
        System.out.println("采集工单信息");
        StringBuilder sb = new StringBuilder();
        try {
            WebElement line = bro.findElement((By.xpath("//td[contains(text(),'故障类型')]")));
            sb.append(line.getText());
            line = bro.findElement(By.id("circuitCode"));
            sb.append(line.getAttribute("value")).append("\r\n");
            line = bro.findElement((By.xpath("//td[contains(text(),'故障位置')]")));
            sb.append(line.getText());
            line = bro.findElement(By.id("equipInfo"));
            sb.append(line.getAttribute("value")).append("\r\n");
            line = bro.findElement((By.xpath("//td[contains(text(),'故障描述')]")));
            sb.append(line.getText());
            line = bro.findElement(By.name("complaintCauseDesc"));
            sb.append(line.getText()).append("\r\n");
            line = bro.findElement((By.xpath("//td[contains(text(),'初次定位')]")));
            sb.append(line.getText());
            line = bro.findElement(By.name("preDetailInfo"));
            sb.append(line.getText()).append("\r\n");
            line = bro.findElement((By.xpath("//td[contains(text(),'二次定位')]")));
            sb.append(line.getText());
            line = bro.findElement(By.name("waveGuide"));
            sb.append(line.getText()).append("\r\n");
            line = bro.findElement((By.xpath("//td[contains(text(),'三次定位')]")));
            sb.append(line.getText());
            line = bro.findElement(By.name("linecode"));
            sb.append(line.getText()).append("\r\n");
            editBill = sb.toString();

            WebElement quit = bro.findElement(By.name("backButton"));
            broJs.executeScript(CLICK, quit);
            sleep(2);
          /*  Alert alert = bro.switchTo().alert();
            alert.accept();*/
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    private static boolean getStep() {
        System.out.println("采集步骤信息");
        //获取Tag 为 FONT 或 H3 或  P 或 INPUT 类型 = text 或 TEXTAREA 的元素
        try {
            List<WebElement> webElements = bro.findElements(By.xpath(EDIT_STEP_ELENIUM_XPATH));
            StringBuilder sb = new StringBuilder();
            for (WebElement webElement : webElements) {
                String text;
                if ("input".equals(webElement.getTagName())) {
                    text = webElement.getAttribute("value");
                } else {
                    text = webElement.getText();
                }
                text = text.replace("\n", "");
                text = text.replace("\r", "");
                sb.append(text).append("\r\n");
            }
            WebElement quit = bro.findElement(By.xpath(EDIT_STEP_QUIT_XPACH));
            broJs.executeScript(CLICK, quit);
            sleep(2);
            editStep = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }


}
