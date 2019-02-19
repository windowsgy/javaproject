package importData;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

/**
 * Created by jlgaoyuan on 2018/11/9.
 *
 */
public class ES_Client {

    public static Client run(String clusterName,String host1, String host2){

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
}