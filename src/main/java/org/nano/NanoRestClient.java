package org.nano;

import com.nano.client.Balance;
import com.nano.client.BlockCount;
import com.nano.client.NanoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import java.io.IOException;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;

@RestController
@RequestMapping("/")
public class NanoRestClient implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(NanoRestClient.class);

    @Value("${API_KEY}")
    private String API_KEY;

    @Autowired
    private RestTemplate restTemplate;

    private String url = "https://api.nanode.co";
    String apiKey = "20e7f841-5d45-11e8-bd27-d37637e19002";

    @RequestMapping(method = RequestMethod.GET, value = "/init")
    public void initialise() throws IOException {

        BlockCount blockCount = ClientBuilder.newClient()
                .target(url).request()
                .accept(APPLICATION_JSON_TYPE)
                .header("Authorization", apiKey)
                .post(Entity.entity("{\"action\":\"block_count\"}", APPLICATION_JSON_TYPE))
                .readEntity(BlockCount.class);

       /* String payload = "{\"action\":\"block_count\"}";
        StringEntity entity = new StringEntity(payload);

        org.apache.http.client.HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        post.setEntity(entity);
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Accept", "application/json");
        post.setHeader("Authorization", apiKey);

        HttpResponse response = httpClient.execute(post);
        System.out.println("Response Code: " + response.getStatusLine().getStatusCode());
        ObjectMapper mapper = new ObjectMapper();
        BlockCount blockCount = mapper.readValue(response.getEntity().getContent(), BlockCount.class);*/
        System.out.println("RES: " + blockCount.getCount() + " " + blockCount.getUnchecked());
    }

    @RequestMapping("/balance")
    public void nanoRpcRequest() {

        NanoClient nanoClient = new NanoClient();
        Balance balance = nanoClient.getAccountBalance("xrb_3pczxuorp48td8645bs3m6c3xotxd3idskrenmi65rbrga5zmkemzhwkaznh");

        log.info("Balance: {}", balance);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/test")
    public String test() {
        return "Test";
    }

    @RequestMapping("/logApiKey")
    public void run(String... args) throws Exception {

        log.info("Nanode API Key: {}", API_KEY);
    }
}
