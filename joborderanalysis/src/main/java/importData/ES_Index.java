package importData;

import org.elasticsearch.action.admin.cluster.state.ClusterStateResponse;
import org.elasticsearch.client.Client;

import java.util.Arrays;
import java.util.List;

class ES_Index {

    /**
     * 查找索引
     * @return 索引列表
     */
    static List<String> getIndex(Client client){

        ClusterStateResponse response = client.admin().cluster().prepareState()
                .execute().actionGet();
        String[] index = response.getState().getMetaData()
                .getConcreteAllIndices();
        return Arrays.asList(index);
    }


    /**
     * 构建索引
     * @return boolean
     */
    static boolean createIndex(String indexName, Client client) {
        client.admin().indices().prepareCreate(indexName).execute().actionGet();
        return true;
    }

}
