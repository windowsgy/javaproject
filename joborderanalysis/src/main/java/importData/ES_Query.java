package importData;


import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import java.util.Map;

public class ES_Query {

    /**
     * 根据文档名、字段名、字段值查询某一条记录的详细信息；query查询
     *
     * @param type  文档名，相当于oracle中的表名，例如：ql_xz；
     * @param key   字段名，例如：bdcqzh
     * @param value 字段值，如：“”
     * @return List
     * @author Lixin
     */
    public SearchResponse getQueryDataBySingleField(Client client, String index, String type, String key, String value) {
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
    public SearchResponse getBoolDataByMuchField(Client client,String index, String type, Map<String, String> map) {
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
    public SearchResponse getDataByillegible(Client client,String index, String type, String key, String value) {
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
    public SearchResponse getDataByMuchillegible(Client client, String index, String type, Map<String, String> map) {
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
