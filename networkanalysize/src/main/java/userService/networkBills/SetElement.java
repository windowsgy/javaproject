package userService.networkBills;

import userService.BaseOperation;
import userService.Params;
import userService.Stru_Bill;
import org.openqa.selenium.*;
import base.LoadProperties;
import base.FileUtils;

import java.util.*;

public class SetElement {

    private static WebDriver bro;
    private static JavascriptExecutor broJs;
    //脚本
    private static final String CLICK = "arguments[0].click()";

    private static final String EDIT_BILL_JS = "setTimeout(function(){document.getElementsByTagName('button')[1].click()},100)";
    private static final String EDIT_STEP_JS = "setTimeout(function(){document.getElementsByTagName('button')[2].click()},100)";
    //private static final String EDIT_STEP_QUIT_XPACH = "//div[@align = 'center']/input[3]";
    private static FileUtils fileUtils = new FileUtils();
    private static Stru_Bill stru_bill;
    private static final String EDIT_STEP_SUBMIT_XPACH = "//div[@align = 'center']/input[1]";
    private static final String EDIT_STEP_DELETE_XPACH = "//div[@align = 'center']/input[2]";

    private static List<Stru_Bill> buildStru(String filesPath) {
        List<String> filesName = fileUtils.getFilesName(filesPath);

        List<Stru_Bill> struList = new ArrayList<>();

        for (String fileName : filesName) {
            String filePath = filesPath + fileName;
            List<String> fileList = fileUtils.read2List(filePath, 0, "GBK");
            //创建结构体
            Stru_Bill stru = new Stru_Bill();
            String id = fileName.substring(0, fileName.lastIndexOf("."));
            stru.setId(id);
            Map<String, String> billEditMap = new HashMap<>();
            Map<String, String> stepEditMap = new HashMap<>();
            Map<String, String> stepDelMap = new HashMap<>();
            for (String line : fileList) {
                if(!line.contains("|")){
                    continue;
                }
                //System.out.println(line);
                String lineArr[] = line.split("\\|", -1);
                String key = lineArr[0].trim();
                String value = lineArr[1].trim();
                if (value.length() == 1 && ("y".equals(value) || "Y".equals(value))) {
                    stepDelMap.put(key, value);
                } else if (key.contains("【")) {
                    stepEditMap.put(key, value);
                } else {
                    billEditMap.put(key, value);
                }
            }
            stru.setBillEditMap(billEditMap);
            stru.setStepDelMap(stepDelMap);
            stru.setStepEditMap(stepEditMap);
            struList.add(stru);
        }
        return struList;
    }

    public static void run() {

        String configFile = "userService.properties";
        LoadProperties.paramMap(configFile,Params.map);//初始化参数
        String editPath = Params.map.get("editPath");//获取采集编辑路径
        System.out.println(editPath);
        List<Stru_Bill> list = buildStru(editPath);//构造编辑结构体
        System.out.println("编辑工单数量 :" + list.size());
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

        for (int i = 0; i < list.size(); i++) {
            System.out.println("number :" + i);
            stru_bill = list.get(i);
            if (!baseOperation.searchNetwokBill(stru_bill.getId())) {//如果查询工单失败就返回
                return;
            }
            if (stru_bill.getStepEditMap().size() >0 ) {//如果编辑步骤不为空
                System.out.println("编辑步骤元素数量 :"+stru_bill.getStepEditMap().size());
                if (!gotoEditStepPage()) {
                    return;
                }
            }
            if (stru_bill.getStepDelMap().size()>0) {//如果删除步骤不为空
                System.out.println("删除步骤元素数量 :"+stru_bill.getStepDelMap().size());
                if(!gotoDelStepPage()){
                 return;
                }
            }
            if (stru_bill.getBillEditMap().size() >0) {//如果编辑工单不为空
                System.out.println("编辑工单元素数量 :"+stru_bill.getBillEditMap().size());
                if (!gotoEditBillPage()){
                    return;
                }
            }

            if(!baseOperation.goBack()){
                return;
            }
        }

    }





    private static boolean gotoEditStepPage() {
        System.out.println("进入修改步骤信息页面");
        try {
            sleep(2);
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
                    if (stru_bill.getStepEditMap() != null) {
                        //修改步骤
                        if (!editStep()) {
                            return false;
                        }
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


    /**
     * 编辑步骤
     */
    private static boolean editStep() {
        System.out.println("执行编辑步骤");
        try {
            WebElement webElement;
            Map<String, String> map = stru_bill.getStepEditMap();
            for (String key : map.keySet()) {
                String xpath = "//font[contains(text(),'" + key + "')]/parent::*[1]/input";
                webElement = bro.findElement(By.xpath(xpath));
                broJs.executeScript(CLICK, webElement);
                xpath = "//font[contains(text(),'" + key + "')]/parent::*[1]/parent::*[1]/textarea[1]";
                webElement = bro.findElement(By.xpath(xpath));
                webElement.clear();
                webElement.sendKeys(map.get(key));
            }
            WebElement quit = bro.findElement(By.xpath(EDIT_STEP_SUBMIT_XPACH));
            broJs.executeScript(CLICK, quit);
            sleep(1);
            Alert alert = bro.switchTo().alert();
            alert.accept();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;


    }

    private static boolean gotoDelStepPage() {
        System.out.println("进入修改步骤信息页面");
        try {
            sleep(3);
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
                    if(!delStep()){
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

    /**
     * 删除步骤
     *
     * @return boolean
     */
    private static boolean delStep() {
        System.out.println("执行删除步骤");
        try {
            WebElement webElement;
            Map<String, String> map = stru_bill.getStepDelMap();
            for (String key : map.keySet()) {
                System.out.println("key :" + key + "|" + "value :" + map.get(key));
                String xpath = "//font[contains(text(),'" + key + "')]/parent::*[1]/input";
                List<WebElement> lineList = bro.findElements((By.xpath(xpath)));
                for (WebElement webElementList : lineList) {//所有元素点击确认
                    broJs.executeScript(CLICK, webElementList);
                }
                //点击删除
                webElement = bro.findElement(By.xpath(EDIT_STEP_DELETE_XPACH));
                broJs.executeScript(CLICK, webElement);
                sleep(1);
                Alert alert = bro.switchTo().alert();
                alert.accept();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    private static boolean gotoEditBillPage() {
        System.out.println("进入修改工单页面");
        try {
            bro.switchTo().defaultContent();
            bro.switchTo().frame("mainFrame");
            bro.switchTo().frame("worktable");
            bro.switchTo().frame("workarea");
            sleep(3);
            String beginClickWindow = bro.getWindowHandle();//当前窗口
            //点击进入
            broJs.executeScript(EDIT_BILL_JS);
            sleep(3);
            bro.switchTo().alert();
            Set<String> handlers = bro.getWindowHandles();
            for (String winHandler : handlers) {
                if (!winHandler.equals(beginClickWindow)) {
                    bro.switchTo().window(winHandler);
                    //编辑工单
                    if(!editBill()){
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

    private static boolean editBill() {
        System.out.println("编辑工单");
        WebElement webElement;
        try {
            Map<String, String> map = stru_bill.getBillEditMap();
            for (String key : map.keySet()) {
                //System.out.println("key :"+key+"|"+"value :"+map.get(key));
                String xpath = "//td[contains(text(),'" + key + "')]/following-sibling::*/child::*[1]";
                webElement = bro.findElement((By.xpath(xpath)));
                webElement.clear();
                webElement.sendKeys(map.get(key));
            }
            webElement = bro.findElement(By.name("saveButton"));
            broJs.executeScript(CLICK, webElement);
            sleep(1);
            Alert alert = bro.switchTo().alert();
            alert.accept();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;


    }

    private static void sleep(long timeLong) {
        timeLong = timeLong * 1000;
        try {
            Thread.sleep(timeLong);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
