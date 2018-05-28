package org.nano;

import com.nano.client.AccountHistory;
import com.nano.client.AccountInformation;
import com.nano.client.AccountPublicKey;
import com.nano.client.AccountRepresentative;
import com.nano.client.AccountWeight;
import com.nano.client.AvailableSupply;
import com.nano.client.Balance;
import com.nano.client.BaseResponse;
import com.nano.client.BlockCount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/")
public class NanoRestClient {

    private static final Logger log = LoggerFactory.getLogger(NanoRestClient.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${API_KEY}")
    private String API_KEY;
    private String url = "https://api.nanode.co";

    @RequestMapping(method = RequestMethod.GET, value = "/accountHistory")
    public String getAccountHistory() {
        NanoHttpClient nanoHttpClient = new NanoHttpClient(url, API_KEY);

        BaseResponse baseResponse = nanoHttpClient.rpcRequest("{\"action\": \"account_history\", " +
                "\"account\": \"xrb_1ipx847tk8o46pwxt5qjdbncjqcbwcc1rrmqnkztrfjy5k7z4imsrata9est\", + " +
                "\"count\", \"1\"}", AccountHistory.class);

        AccountHistory accountHistory = (AccountHistory) baseResponse;

        return "Account History: " + accountHistory.getHistory();
    }

    @RequestMapping("/accountRep")
    public String getAccountList() {
        NanoHttpClient nanoHttpClient = new NanoHttpClient(url, API_KEY);

        BaseResponse baseResponse = nanoHttpClient.rpcRequest("{\"action\": \"account_representative\", " +
                "\"account\": \"xrb_1ipx847tk8o46pwxt5qjdbncjqcbwcc1rrmqnkztrfjy5k7z4imsrata9est\", + ", AccountRepresentative.class);

        AccountRepresentative accountRepresentative = (AccountRepresentative) baseResponse;

        return "Account Rep: " + accountRepresentative.getRepresentative();
    }

    @RequestMapping("/balance")
    public String balance() {
        NanoHttpClient nanoHttpClient = new NanoHttpClient(url, API_KEY);

        BaseResponse balanceResponse = nanoHttpClient.rpcRequest("{\"action\": \"account_balance\", " +
                "\"account\":\"xrb_1ipx847tk8o46pwxt5qjdbncjqcbwcc1rrmqnkztrfjy5k7z4imsrata9est\"}", Balance.class);

        Balance balance = (Balance) balanceResponse;

        return "Balance: " + balance.getBalance() + "<br>" +
                "Pending Balance: " + balance.getPending();
    }

    @RequestMapping("/accountWeight")
    public String accountWeight() {
        NanoHttpClient nanoHttpClient = new NanoHttpClient(url, API_KEY);

        BaseResponse baseResponse = nanoHttpClient.rpcRequest("{\"action\": \"account_weight\", " +
                "\"account\":\"xrb_1ipx847tk8o46pwxt5qjdbncjqcbwcc1rrmqnkztrfjy5k7z4imsrata9est\"}", AccountWeight.class);

        AccountWeight accountWeight = (AccountWeight) baseResponse;

        return "Account Weight: " + accountWeight.getWeight() + "<br>";
    }

    @RequestMapping("/availableSupply")
    public String availableSupply() {
        NanoHttpClient nanoHttpClient = new NanoHttpClient(url, API_KEY);

        BaseResponse baseResponse = nanoHttpClient.rpcRequest("{\"action\": \"available_supply\"}", AvailableSupply.class);

        AvailableSupply availableSupply = (AvailableSupply) baseResponse;

        return "Available Supply: " + availableSupply.getAvailable() + "<br>";
    }

    @RequestMapping("/blockCount")
    public String blockCount() {
        NanoHttpClient nanoHttpClient = new NanoHttpClient(url, API_KEY);

        BaseResponse baseResponse = nanoHttpClient.rpcRequest("{\"action\": \"block_count\"}", BlockCount.class);

        BlockCount blockCount = (BlockCount) baseResponse;

        return "Block Count: " + blockCount.getCount() + "<br>";
    }

    @RequestMapping("/accountInfo")
    public String accountInformation() {
        NanoHttpClient nanoHttpClient = new NanoHttpClient(url, API_KEY);

        BaseResponse accountInformationResponse = nanoHttpClient.rpcRequest("{\"action\": \"account_info\", " +
                "\"account\": \"xrb_1niabkx3gbxit5j5yyqcpas71dkffggbr6zpd3heui8rpoocm5xqbdwq44oh\"}", AccountInformation.class);

        AccountInformation accountInformation = (AccountInformation) accountInformationResponse;

        return "Balance: " + accountInformation.getBalance() + "<br>" +
                "Frontier: " + accountInformation.getFrontier() + "<br>" +
                "OpenBlock: " + accountInformation.getOpenBlock() + "<br>" +
                "Representative Block: " + accountInformation.getRepresentativeBlock() + "<br>" +
                "Modified Timestamp: " + accountInformation.getModifiedTimestamp();
    }

    @RequestMapping("/accountPublicKey")
    public String accountPublicKey() {
        NanoHttpClient nanoHttpClient = new NanoHttpClient(url, API_KEY);

        BaseResponse accountPublicKeyResponse = nanoHttpClient.rpcRequest("{\"action\": \"account_key\", " +
                "\"account\":\"xrb_1ipx847tk8o46pwxt5qjdbncjqcbwcc1rrmqnkztrfjy5k7z4imsrata9est\"}", AccountPublicKey.class);

        AccountPublicKey accountPublicKey = (AccountPublicKey) accountPublicKeyResponse;

        return "Account Public Key: " + accountPublicKey.getKey();
    }

    @RequestMapping("/logApiKey")
    public void run(String... args) throws Exception {

        log.info("Nanode API Key: {}", API_KEY);
    }
}
