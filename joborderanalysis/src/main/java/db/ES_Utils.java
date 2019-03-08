package db;

import base.Log;
import org.elasticsearch.action.admin.cluster.state.ClusterStateResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

class ES_Utils {

    /**
     * get client
     * @param clusterName clusterName
     * @param host1 host1
     * @param host2 host2
     * @return Client
     */
     Client getClient(String clusterName, String host1, String host2){

        Settings settings = Settings.builder()
                .put("cluster.name", clusterName).build();
        TransportClient client = new PreBuiltTransportClient(settings);
        // on startup
        try{
            client = client
                    .addTransportAddress(new TransportAddress(InetAddress.getByName(host1), 9300))
                    .addTransportAddress(new TransportAddress(InetAddress.getByName(host2), 9300));
        }catch (Exception e){
            e.printStackTrace();
        }
        return client;
    }


    /**
     * 批量导入数据
     * @param client client
     * @param list list
     * @param indexName 索引名
     * @param type 类型
     */
    void  bulk(Client client, List<Map<String, Object>> list, String indexName, String type) {
        try {
            Log.info("data size is :"+list.size());
            int count = 0;
            // 开启批量插入
            BulkRequestBuilder bulkRequest = client.prepareBulk();
            for (Map<String, Object> maps : list) {
                count++;
                // log.info("JSON : "+value);
                bulkRequest.add(client.prepareIndex(indexName, type,maps.get("id").toString()).setSource(maps));
            }
            bulkRequest.execute().actionGet();
            bulkRequest.request().requests().clear();
            Log.info("bulk to es data size is :"+count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查找索引
     * @return 索引列表
     */
     List<String> getIndex(Client client){

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
     boolean createIndex(String indexName, Client client) {
        client.admin().indices().prepareCreate(indexName).execute().actionGet();
        return true;
    }

    /**
     * 根据文档名、字段名、字段值查询某一条记录的详细信息；query查询
     *
     * @param type  文档名，相当于oracle中的表名，例如：ql_xz；
     * @param key   字段名，例如：bdcqzh
     * @param value 字段值，如：“”
     * @return List
     * @author Lixin
     */
     SearchResponse getQueryDataBySingleField(Client client, String index, String type, String key, String value) {
        QueryBuilder qb = QueryBuilders.termQuery(key, value);
        SearchResponse response = client.prepareSearch(index).setTypes(type)
                .setQuery(qb)
                .setFrom(0).setSize(10000).setExplain(true)
                .execute()
                .actionGet();
        return response;
    }


    /**
     * 多条件查询
     * @param client es client
     * @param index es index
     * @param type es type
     * @param map map 中设置 key value ,key 字段名称  value 值名称
     * @return 查询结果
     */
     SearchResponse getBoolDataByMuchField(Client client,String index, String type, Map<String, String> map) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        for (String in : map.keySet()) {
            String str = map.get(in);
            //构建查询条件
            boolQueryBuilder.must(QueryBuilders.termQuery(in, str));
        }
        SearchResponse response = client.prepareSearch(index).setTypes(type)
                .setQuery(boolQueryBuilder)
                .setFrom(0).setSize(10000).setExplain(true)
                .execute()
                .actionGet();
        return response;
    }


    /**
     *
     * @param client es client
     * @param index es index
     * @param type  index type
     * @param key  字段名
     * @param value 模糊值：如 *123* ;?123*;?123?;*123?;
     * @return 查询结果
     */
     SearchResponse getDataByillegible(Client client,String index, String type, String key, String value) {
        WildcardQueryBuilder wBuilder = QueryBuilders.wildcardQuery(key, value);
        SearchResponse response = client.prepareSearch(index).setTypes(type)
                .setQuery(wBuilder)
                .setFrom(0).setSize(10000).setExplain(true)
                .execute()
                .actionGet();
        return response;
    }

    /**
     *
     * @param client es client
     * @param index es index
     * @param type index type
     * @param map key value key 字段名 ， value 模糊查询值
     * @return 查询结果
     */
     SearchResponse getDataByMuchillegible(Client client, String index, String type, Map<String, String> map) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        for (String in : map.keySet()) {
            String str = map.get(in);
            boolQueryBuilder.must(QueryBuilders.wildcardQuery(in, str));
        }
        SearchResponse response = client.prepareSearch(index).setTypes(type)
                .setQuery(boolQueryBuilder)
                .setFrom(0).setSize(10000).setExplain(true)
                .execute()
                .actionGet();
        return response;
    }

}
