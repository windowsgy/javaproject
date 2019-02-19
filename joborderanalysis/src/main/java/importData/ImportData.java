package importData;

import base.FileUtils;
import base.JsonUtils;
import base.Log;
import buildParams.Params;
import org.elasticsearch.client.Client;
import java.util.List;
import java.util.Map;

public class ImportData {

    public static boolean run(){
        FileUtils fileUtils = new FileUtils();
        JsonUtils jsonUtils = new JsonUtils();
        List<String> fileList = fileUtils.getFilesName(Params.jsonPath);
        //json to map ;
        List<Map<String,Object>> map = jsonUtils.jsonToMap(fileList);

        for(int i = 0 ; i < fileList.size();i++){
            String filePath = Params.jsonPath+fileList.get(i);
            String fileCode = fileUtils.getFileCode(filePath);
            List<String> list = fileUtils.read2List(filePath,0,fileCode);
            Log.info("build client");
            Client client = ES_Client.run(Params.es_cluster_name,Params.es_node1,Params.es_node2);
            Log.info("create index");
            ES_Index.createIndex(Params.es_index_name,client);
            Log.info("build map");
            ES_Mapping.buildIndexMap(Params.es_index_name,Params.es_index_type,client);
            Log.info("bulk");
            ES_ImportData.bulk(client,list,Params.es_index_name,Params.es_index_type);
            client.close();
        }
        return true;
    }
}
