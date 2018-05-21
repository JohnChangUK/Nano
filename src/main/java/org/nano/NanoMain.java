package org.nano;

import com.nano.client.Balance;
import com.nano.client.NanoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NanoMain {

    private static final Logger log = LoggerFactory.getLogger(NanoMain.class);

    public static void main(String[] args) {

        NanoClient nanoClient = new NanoClient();

        Balance balance = nanoClient.getAccountBalance("xrb_3pczxuorp48td8645bs3m6c3xotxd3idskrenmi65rbrga5zmkemzhwkaznh");

        log.info("Balance: {}", balance);
    }
}
