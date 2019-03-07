package db.es;

import org.elasticsearch.client.Client;

public interface ES_Mapping {
     void buildIndexMap(String indexName, String typeName, Client client);
}
