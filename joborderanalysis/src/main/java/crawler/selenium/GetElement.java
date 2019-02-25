package crawler.selenium;

import base.FileUtils;
import buildParams.Params;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;



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
        String url = Params.url;
        String driverPath = Params.driverPath;
        String username = Params.userName;
        String password = Params.passWord;
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
        //进入报表平台
        if (!baseOperation.gotoReportForms()) {
            System.out.println("进入报表平台失败");
            return;
        }
        if (!baseOperation.gotoUserReportFroms()) {
            System.out.println("进入用户层报表失败");
            return;
        }
        if (!baseOperation.gotoUserReportFromsDetail()) {
            System.out.println("进入用户层明细表失败");
            return;
        }

        String startTime = "2019-02-01";
        String endTime = "2019-02-15";
        String localZone = "中国电信吉林分公司";
        if (!baseOperation.gotoHisOrders(startTime,endTime,localZone)) {
            System.out.println("进入省内装移机历史工单明细表失败");
            return;
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

}
