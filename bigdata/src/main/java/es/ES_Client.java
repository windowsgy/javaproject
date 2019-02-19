package es;

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

    public static void run(){

        Settings settings = Settings.builder()
                .put("cluster.name", "myClusterName").build();
        TransportClient client = new PreBuiltTransportClient(settings);
        // on startup
        try{
            client = new PreBuiltTransportClient(Settings.EMPTY)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("host1"), 9300))
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("host2"), 9300));
        }catch (Exception e){
            e.printStackTrace();
        }
        // on shutdown
        client.close();
    }
}