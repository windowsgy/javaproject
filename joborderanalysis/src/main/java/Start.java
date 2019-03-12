import base.Log;
import db.ImportData;
import web.GetWeb;
import etl.ETL;
import init.Init;
import init.Params;

import java.util.List;
import java.util.Map;


public class Start {

    /**
     * @param args 输入参数
     */
    public static void run(String[] args) {
        Log.setDebug(true);
        Log.linel0();
        if (!Init.run(args)) {
            return;
        }
        Log.linel0();
        if (!Params.loadLocalData) {
            if (!GetWeb.run()) {
                return;
            }
        }
        Log.linel0();
        Map<String, List<String>> map = ETL.run();
        if (null == map) {
            return;
        }
        Log.linel0();
        ImportData.run(map);
    }
}
