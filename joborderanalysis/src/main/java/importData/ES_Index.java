package importData;

import org.elasticsearch.action.admin.cluster.state.ClusterStateResponse;
import org.elasticsearch.client.Client;

import java.util.ArrayList;
import java.util.List;

public class ES_Index {

    /**
     * 查找索引
     * @return 索引列表
     */
    public static List<String> findIndex(Client client){
        List<String> indexList = new ArrayList<>();
        return indexList;
    }


    /**
     * 构建索引
     * @return boolean
     */
    static boolean createIndex(String indexName, Client client) {
        client.admin().indices().prepareCreate(indexName).execute().actionGet();
        return true;
    }

    /**
     * 获取索引
     * @param client esClient
     * @return allIndex
     */
    public static List<String> getAllIndex(Client client) {
        List<String> list = new ArrayList<>();
        ClusterStateResponse response = client.admin().cluster().prepareState()
                .execute().actionGet();
        String[] indexs = response.getState().getMetaData()
                .getConcreteAllIndices();
        for(int i = 0 ; i < indexs.length;i++){
            list.add(indexs[i]);
        }
        return list;
    }
}
