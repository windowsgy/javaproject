package crawler;

import base.Log;
import buildParams.Params;

public class GetHistoryOrders {

    /**
     * 爬取客调系统历史数据方法
     * @return boolean
     */
    public static boolean  run() {
        String url = Params.url;
        String driverPath = Params.driverPath;
        String username = Params.userName;
        String password = Params.passWord;
        String savePath = Params.webPath;
        String startTime = "2019-02-01";
        String endTime = "2019-02-01";
   
        HistoryOrdersStep step = new HistoryOrdersStep();
        //初始化
        if (!step.ieInit(driverPath)) {
            Log.error("init error");
            return false;
        }
     
        //登陆
        if (!step.login(url,username,password)) {
            Log.error("login error");
            return false;
        }
        //进入报表平台
        if (!step.gotoReportForms()) {
            Log.error("进入报表平台失败");
            return false;
        }
        if (!step.gotoUserReportFroms()) {
            Log.error("进入用户层报表失败");
            return false;
        }
        if (!step.gotoUserReportFromsDetail()) {
            Log.error("进入用户层明细表失败");
            return false;
        }

        //查询工单循环获取工单内容
        int pageIndex = step.gotoHisOrders(startTime,endTime);
        if (0 ==pageIndex ) {
            Log.error("获取省内装移机历史工单明细表失败");
            return false;
        }
        else{
            Log.info("执行获取每页工单,按页保存");
            String fileName = startTime+"_"+endTime;
            step.getOrders(pageIndex,savePath,fileName);
        }
        return true;
    }
}
