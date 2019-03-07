import base.Log;
import db.ImportData;
import web.Crawler;
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

        if (!Init.run(args)) {
            return;
        }
        Log.info("runTime :"+Params.runTime);

        if (!Params.loadLocalData) {
            if (!Crawler.run()) {
                return;
            }
        }



        Map<String, List<String>> map = ETL.run();

        if (null == map) {
            return;
        }

        ImportData.run(map);

    }
}
