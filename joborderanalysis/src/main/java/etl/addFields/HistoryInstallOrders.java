package etl.addFields;

import init.Params;

public class HistoryInstallOrders implements AddFields {
    public String run(String filePath){
        return Params.runTime+"||"+filePath;
    }
}
