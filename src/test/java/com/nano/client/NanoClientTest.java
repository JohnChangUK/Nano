package com.nano.client;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NanoClientTest {

    private static final String ACCOUNT = "xrb_3t6k35gi95xu6tergt6p69ck76ogmitsa8mnijtpxm9fkcm736xtoncuohr3";
    private static final String REPRESENTATIVE = "xrb_16u1uufyoig8777y6r8iqjtrw8sg8maqrm36zzcm95jmbd9i9aj5i8abr8u5";
    private static final String PUBLIC_KEY = "E89208DD038FBB269987689621D52292AE9C35941A7484756ECCED92A65093BA";
    private static final String WALLET = "000D1BAEC8EC208142C99059B393051BAC8380F9B5A2E6B2489A277D81789F3F";
    private static final String BAD_ACCOUNT_NUMBER_ERROR = "Bad account number";
    private static final String WALLET_NOT_FOUND_ERROR = "Wallet not found";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private HttpClient httpClient;
    private NanoClient client;

    @Before
    public void setUp() throws Exception {
        httpClient = mock(HttpClient.class);
        client = new NanoClient(httpClient);
    }

    @Test
    public void testGetAccountBalance() throws Exception {
        expectJson("account_balance");

        Balance balance = client.getAccountBalance(ACCOUNT);
        assertEquals("325586539664609129644855132177", balance.getBalance());
        assertEquals(ACCOUNT, balance.getAccount());
        assertEquals("2309370940000000000000000000000000", balance.getPending());
    }

    @Test
    public void testGetAccountBalanceWhenAccountNotFound() throws Exception {
        expectJson("account_balance_not_found");

        expectedException.expect(NanoException.class);
        expectedException.expectMessage(BAD_ACCOUNT_NUMBER_ERROR);
        client.getAccountBalance(ACCOUNT);
    }

    @Test
    public void testGetAccountBlockCount() throws Exception {
        expectJson("account_block_count");

        assertEquals("42", client.getAccountBlockCount(ACCOUNT).getBlockCount());
    }

    @Test
    public void testGetAccountBlockCountWhenAddressNotFound() throws Exception {
        expectJson("account_block_count_not_found");

        expectedException.expect(NanoException.class);
        expectedException.expectMessage(BAD_ACCOUNT_NUMBER_ERROR);
        client.getAccountBlockCount(ACCOUNT);
    }

    @Test
    public void testGetAccountInformation() throws Exception {
        expectJson("account_info");

        AccountInformation info = client.getAccountInformation(ACCOUNT);
        assertEquals("ECCB8CB65CD3106EDA8CE9AA893FEAD497A91BCA903890CBD7A5C59F06AB9113", info.getFrontier());
        assertEquals("991CF190094C00F0B68E2E5F75F6BEE95A2E0BD93CEAA4A6734DB9F19B728948", info.getOpenBlock());
        assertEquals("991CF190094C00F0B68E2E5F75F6BEE95A2E0BD93CEAA4A6734DB9F19B728948", info.getRepresentativeBlock());
        assertEquals("325586539664609129644855132177", info.getBalance());
        assertEquals("42", info.getBlockCount());
    }

    @Test
    public void testGetAccountInformationWhenAddressNotFound() throws Exception {
        expectJson("account_info_not_found");

        expectedException.expect(NanoException.class);
        expectedException.expectMessage(BAD_ACCOUNT_NUMBER_ERROR);
        client.getAccountInformation(ACCOUNT);
    }

    @Test
    public void testCreateAccount() throws Exception {
        expectJson("account_create");

        AccountCreate account = client.createAccount(WALLET);
        assertEquals("xrb_1rophzebpsxhitm6cphr8qtph4gzjez899ibetqn1bftqydpp9yzau7fkhtm", account.getAccount());
    }

    @Test
    public void testCreateAccountWhenWalletNotFound() throws Exception {
        expectJson("account_create_wallet_not_found");

        expectedException.expect(NanoException.class);
        expectedException.expectMessage(WALLET_NOT_FOUND_ERROR);
        client.createAccount(WALLET);
    }

    @Test
    public void testGetAccountPublicKey() throws Exception {
        expectJson("account_key");

        AccountPublicKey pk = client.getAccountPublicKey(ACCOUNT);
        assertEquals("E89208DD038FBB269987689621D52292AE9C35941A7484756ECCED92A65093BA", pk.getKey());
    }

    @Test
    public void testGetAccount() throws Exception {
        expectJson("account_get");

        AccountGet account = client.getAccount(PUBLIC_KEY);
        assertEquals(ACCOUNT, account.getAccount());
    }

    @Test
    public void testGetAccountHistory() throws Exception {
        expectJson("account_history");

        AccountHistory history = client.getAccountHistory(ACCOUNT, 10);
        assertEquals(10, history.getHistory().size());

        History h = history.getHistory().get(0);
        assertEquals("xrb_1111111111111111111111111111111111111111111111111111hifc8npp", h.getAccount());
        assertEquals("205676479000000000000000000000000000000", h.getAmount());
        assertEquals("ECCB8CB65CD3106EDA8CE9AA893FEAD497A91BCA903890CBD7A5C59F06AB9113", h.getHash());
        assertEquals("send", h.getType());
    }

    @Test
    public void testGetAccountList() throws Exception {
        expectJson("account_list");

        AccountList list = client.getAccountList(WALLET);
        assertEquals(3, list.getAccounts().size());
        assertEquals(ACCOUNT, list.getAccounts().get(0));
    }

    @Test
    public void testMoveAccounts() throws Exception {
        expectJson("account_move");

        AccountMove move = client.moveAccounts(WALLET, WALLET, Arrays.asList("xrb_3t6k35gi95xu6tergt6p69ck76ogmitsa8mnijtpxm9fkcm736xtoncuohr3", "xrb_13ezf4od79h1tgj9aiu4djzcmmguendtjfuhwfukhuucboua8cpoihmh8byo"));
        assertEquals(2, (int) move.getMoved());
    }

    @Test
    public void testRemoveAccount() throws Exception {
        expectJson("account_remove");

        AccountRemove remove = client.removeAccount(WALLET, ACCOUNT);
        assertEquals(1, (int) remove.getRemoved());
    }

    @Test
    public void testGetAccountRepresentative() throws Exception {
        expectJson("account_representative");

        AccountRepresentative rep = client.getAccountRepresentative(ACCOUNT);
        assertEquals("xrb_16u1uufyoig8777y6r8iqjtrw8sg8maqrm36zzcm95jmbd9i9aj5i8abr8u5", rep.getRepresentative());
    }

    @Test
    public void testSetAccountRepresentative() throws Exception {
        expectJson("account_representative_set");

        AccountRepresentativeSet set = client.setAccountRepresentative(WALLET, ACCOUNT, REPRESENTATIVE);
        assertEquals("000D1BAEC8EC208142C99059B393051BAC8380F9B5A2E6B2489A277D81789F3F", set.getBlock());
    }

    @Test
    public void testGetAccountWeight() throws Exception {
        expectJson("account_weight");

        AccountWeight weight = client.getAccountWeight(ACCOUNT);
        assertEquals("10000", weight.getWeight());
    }

    @Test
    public void testGetAccountBalances() throws Exception {
        expectJson("account_balances");

        Balances balances = client.getAccountBalances(Arrays.asList("xrb_3e3j5tkog48pnny9dmfzj1r16pg8t1e76dz5tmac6iq689wyjfpi00000000", "xrb_3i1aq1cchnmbn9x5rsbap8b15akfh7wj7pwskuzi7ahz8oq6cobd99d4r3b7"));
        assertEquals(2, balances.getBalances().size());

        Balance one = balances.getBalances().get(0);
        assertEquals("xrb_3e3j5tkog48pnny9dmfzj1r16pg8t1e76dz5tmac6iq689wyjfpi00000000", one.getAccount());
        assertEquals("10000", one.getBalance());
        assertEquals("10001", one.getPending());
    }

    @Test
    public void testCreateAccounts() throws Exception {
        expectJson("accounts_create");

        AccountsCreate create = client.createAccounts(WALLET, 2);
        assertEquals(2, create.getAccounts().size());
        assertEquals("xrb_3e3j5tkog48pnny9dmfzj1r16pg8t1e76dz5tmac6iq689wyjfpi00000000", create.getAccounts().get(0));
    }

    @Test
    public void testGetAccountsFrontiers() throws Exception {
        expectJson("accounts_frontiers");

        AccountsFrontiers frontiers = client.getAccountsFrontiers(Arrays.asList("xrb_3t6k35gi95xu6tergt6p69ck76ogmitsa8mnijtpxm9fkcm736xtoncuohr3", "xrb_3i1aq1cchnmbn9x5rsbap8b15akfh7wj7pwskuzi7ahz8oq6cobd99d4r3b7"));
        assertEquals(2, frontiers.getFrontiers().size());
        assertEquals("xrb_3t6k35gi95xu6tergt6p69ck76ogmitsa8mnijtpxm9fkcm736xtoncuohr3", frontiers.getFrontiers().get(0).getAddress());
        assertEquals("791AF413173EEE674A6FCF633B5DFC0F3C33F397F0DA08E987D9E0741D40D81A", frontiers.getFrontiers().get(0).getBlock());
        assertEquals("xrb_3i1aq1cchnmbn9x5rsbap8b15akfh7wj7pwskuzi7ahz8oq6cobd99d4r3b7", frontiers.getFrontiers().get(1).getAddress());
        assertEquals("6A32397F4E95AF025DE29D9BF1ACE864D5404362258E06489FABDBA9DCCC046F", frontiers.getFrontiers().get(1).getBlock());
    }

    @Test
    public void testGetAccountsPending() throws Exception {
        expectJson("accounts_pending");

        AccountsPending pending = client.getAccountsPending(Arrays.asList("xrb_1111111111111111111111111111111111111111111111111117353trpda", "xrb_3t6k35gi95xu6tergt6p69ck76ogmitsa8mnijtpxm9fkcm736xtoncuohr3"), 1);
        assertEquals(2, pending.getAccounts().size());
        assertEquals("xrb_1111111111111111111111111111111111111111111111111117353trpda", pending.getAccounts().get(0).getAddress());
        assertEquals("142A538F36833D1CC78B94E11C766F75818F8B940771335C6C1B8AB880C5BB1D", pending.getAccounts().get(0).getBlocks().get(0));
    }

    @Test
    public void testGetBlockAccount() throws Exception {
        expectJson("block_account");

        BlockAccount account = client.getBlockAccount("000D1BAEC8EC208142C99059B393051BAC8380F9B5A2E6B2489A277D81789F3F");
        assertEquals("xrb_3e3j5tkog48pnny9dmfzj1r16pg8t1e76dz5tmac6iq689wyjfpi00000000", account.getAccount());
    }

    @Test
    public void testGetBlockCount() throws Exception {
        expectJson("block_count");

        BlockCount count = client.getBlockCount();
        assertEquals("1000", count.getCount());
        assertEquals("10", count.getUnchecked());
    }

    @Test
    public void testGetBlockCountType() throws Exception {
        expectJson("block_count_type");

        BlockCountType type = client.getBlockCountType();
        assertEquals("1000", type.getSend());
        assertEquals("900", type.getReceive());
        assertEquals("100", type.getOpen());
        assertEquals("50", type.getChange());
    }

    @Test
    public void testGetChain() throws Exception {
        expectJson("chain");

        Chain chain = client.getChain("000D1BAEC8EC208142C99059B393051BAC8380F9B5A2E6B2489A277D81789F3F", 1);
        assertEquals("000D1BAEC8EC208142C99059B393051BAC8380F9B5A2E6B2489A277D81789F3F", chain.getBlocks().get(0));
    }

    @Test
    public void testMraiFromRaw() throws Exception {
        expectJson("mrai_from_raw");

        String value = client.mraiFromRaw("1000000000000000000000000000000");
        assertEquals("1", value);
    }

    @Test
    public void testMraiToRaw() throws Exception {
        expectJson("mrai_to_raw");

        String value = client.mraiToRaw("1");
        assertEquals("1000000000000000000000000000000", value);
    }

    @Test
    public void testKraiFromRaw() throws Exception {
        expectJson("krai_from_raw");

        String value = client.kraiFromRaw("1000000000000000000000000000");
        assertEquals("1", value);
    }

    @Test
    public void testKraiToRaw() throws Exception {
        expectJson("krai_to_raw");

        String value = client.kraiToRaw("1");
        assertEquals("1000000000000000000000000000", value);
    }

    @Test
    public void testRaiFromRaw() throws Exception {
        expectJson("rai_from_raw");

        String value = client.raiFromRaw("1000000000000000000000000");
        assertEquals("1", value);
    }

    @Test
    public void testRaiToRaw() throws Exception {
        expectJson("rai_to_raw");

        String value = client.raiToRaw("1");
        assertEquals("1000000000000000000000000", value);
    }

    @Test
    public void testWhenCantConnectToNode() throws Exception {
        when(httpClient.post(anyString())).thenThrow(new IOException());

        expectedException.expect(NanoException.class);
        expectedException.expectMessage("Unable to communicate with node");
        client.createAccount(WALLET);
    }

    @Test
    public void testReceive() throws Exception {
        expectJson("receive");

        Receive receive = client.receive(
            "000D1BAEC8EC208142C99059B393051BAC8380F9B5A2E6B2489A277D81789F3F",
            "xrb_3e3j5tkog48pnny9dmfzj1r16pg8t1e76dz5tmac6iq689wyjfpi00000000",
            "53EAA25CE28FA0E6D55EA9704B32604A736966255948594D55CBB05267CECD48");

        assertEquals("EE5286AB32F580AB65FD84A69E107C69FBEB571DEC4D99297E19E3FA5529547B", receive.getBlock());
    }

    @Test
    public void testReceiveMinimum() throws Exception {
        expectJson("receive_minimum");

        ReceiveMinimum minimum = client.getReceiveMinimum();
        assertEquals("1000000000000000000000000", minimum.getAmount());
    }

    @Test
    public void testReceiveMinimumSet() throws Exception {
        expectJson("receive_minimum_set");
        client.setReceiveMinimum("10000000");
    }

    @Test
    public void testReceiveMinimumSetError() throws Exception {
        expectJson("receive_minimum_set_error");
        expectedException.expectMessage("Invalid JSON");
        client.setReceiveMinimum("10000000");
    }

    @Test
    public void testGetRepresentatives() throws Exception {
        expectJson("representatives");

        Representatives representatives = client.getRepresentatives();
        assertEquals(3, representatives.getRepresentatives().size());

        Representative rep = representatives.getRepresentatives().get(0);
        assertEquals("xrb_1111111111111111111111111111111111111111111111111117353trpda", rep.getAddress());
        assertEquals("3822372327060170000000000000000000000", rep.getAmount());
    }

    @Test
    public void testGetWalletRepresentative() throws Exception {
        expectJson("wallet_representative");

        WalletRepresentative rep = client.getWalletRepresentative("000D1BAEC8EC208142C99059B393051BAC8380F9B5A2E6B2489A277D81789F3F");
        assertEquals("xrb_3e3j5tkog48pnny9dmfzj1r16pg8t1e76dz5tmac6iq689wyjfpi00000000", rep.getRepresentative());
    }

    @Test
    public void testSetWalletRepresentative() throws Exception {
        expectJson("wallet_representative_set");
        client.setWalletRepresentative("000D1BAEC8EC208142C99059B393051BAC8380F9B5A2E6B2489A277D81789F3F", "xrb_3e3j5tkog48pnny9dmfzj1r16pg8t1e76dz5tmac6iq689wyjfpi00000000");
    }

    @Test
    public void testSend() throws Exception {
        expectJson("send");

        Send send = client.send(
            "000D1BAEC8EC208142C99059B393051BAC8380F9B5A2E6B2489A277D81789F3F",
            "xrb_3e3j5tkog48pnny9dmfzj1r16pg8t1e76dz5tmac6iq689wyjfpi00000000",
            "xrb_3e3j5tkog48pnny9dmfzj1r16pg8t1e76dz5tmac6iq689wyjfpi00000000",
            "1000000"
        );
        assertEquals("000D1BAEC8EC208142C99059B393051BAC8380F9B5A2E6B2489A277D81789F3F", send.getBlock());
    }

    @Test
    public void testGetNodeVersion() throws Exception {
        expectJson("version");

        NodeVersion version = client.getNodeVersion();
        assertEquals("RaiBlocks 7.5.0", version.getNodeVendor());
        assertEquals("1", version.getRpcVersion());
        assertEquals("2", version.getStoreVersion());
    }

    @Test
    public void testStopNode() throws Exception {
        expectJson("stop");
        client.stopNode();
    }

    @Test
    public void testChangeWalletSeed() throws Exception {
        expectJson("wallet_change_seed");
        client.changeWalletSeed(
            "000D1BAEC8EC208142C99059B393051BAC8380F9B5A2E6B2489A277D81789F3F",
            "74F2B37AAD20F4A260F0A5B3CB3D7FB51673212263E58A380BC10474BB039CEE"
        );
    }

    @Test
    public void testWalletContainsAccount() throws Exception {
        expectJson("existence_true");
        boolean contains = client.walletContainsAccount("000D1BAEC8EC208142C99059B393051BAC8380F9B5A2E6B2489A277D81789F3F", "xrb_3e3j5tkog48pnny9dmfzj1r16pg8t1e76dz5tmac6iq689wyjfpi00000000");
        assertTrue(contains);
    }

    @Test
    public void testWalletContainsAccountFalse() throws Exception {
        expectJson("existence_false");
        boolean contains = client.walletContainsAccount("000D1BAEC8EC208142C99059B393051BAC8380F9B5A2E6B2489A277D81789F3F", "xrb_3e3j5tkog48pnny9dmfzj1r16pg8t1e76dz5tmac6iq689wyjfpi00000000");
        assertFalse(contains);
    }

    @Test
    public void testGetWalletBalances() throws Exception {
        expectJson("wallet_balances");

        Balances balances = client.getWalletBalances("000D1BAEC8EC208142C99059B393051BAC8380F9B5A2E6B2489A277D81789F3F");
        assertEquals(1, balances.getBalances().size());
        assertEquals("xrb_3e3j5tkog48pnny9dmfzj1r16pg8t1e76dz5tmac6iq689wyjfpi00000000", balances.getBalances().get(0).getAccount());
        assertEquals("10000", balances.getBalances().get(0).getBalance());
        assertEquals("10001", balances.getBalances().get(0).getPending());
    }

    private void expectJson(String name) throws IOException {
        when(httpClient.post(anyString())).thenReturn(loadJson(name));
    }

    private String loadJson(String name) {
        String filename = "json/" + name + ".json";
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream(filename);
        return new Scanner(stream).useDelimiter("\\A").next();
    }
}
