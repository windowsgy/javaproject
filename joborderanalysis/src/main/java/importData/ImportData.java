package importData;

import base.FileUtils;
import base.JsonUtils;
import base.Log;
import buildParams.Params;
import org.elasticsearch.client.Client;
import java.util.List;
import java.util.Map;

public class ImportData {

    /**
     * @return boolean
     */
    public static boolean run() {
        FileUtils fileUtils = new FileUtils();
        JsonUtils jsonUtils = new JsonUtils();
        List<String> fileNameList = fileUtils.getFilesName(Params.jsonPath);
        for (String aFileList : fileNameList) {
            String filePath = Params.jsonPath + aFileList;
            String fileCode = fileUtils.getFileCode(filePath);
            List<String> list = fileUtils.read2List(filePath, 0, fileCode);
            //json to map ;
            List<Map<String, Object>> map = jsonUtils.jsonToMap(list);
            Log.info("build client");
            Client client = ES_Client.run(Params.es_cluster_name, Params.es_node1, Params.es_node2);
            Log.info("get index");
            List<String> listIndex = ES_Index.getIndex(client);
            for (int i = 0; i < listIndex.size(); i++) {
                if (listIndex.contains(Params.es_index_name)) {
                    Log.error("index existed");
                    client.close();
                    return false;
                } else {
                    Log.info("create index");
                    if(!ES_Index.createIndex(Params.es_index_name, client)){
                        return false;
                    }
                    Log.info("build index mapping");
                    ES_Mapping.buildIndexMap(Params.es_index_name, Params.es_index_type, client);
                    Log.info("bulk");
                    ES_ImportData.bulk(client, map, Params.es_index_name, Params.es_index_type);
                }
            }
            client.close();
        }
        return true;
    }
}
