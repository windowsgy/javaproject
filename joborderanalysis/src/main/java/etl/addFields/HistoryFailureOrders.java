package etl.addFields;

import init.Params;

public class HistoryFailureOrders implements AddFields {
    public String run(String filePath){
        return Params.runTime+"||"+filePath;
    }
}
