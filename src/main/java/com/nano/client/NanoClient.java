package com.nano.client;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

public class NanoClient {

    private static final String DEFAULT_HOST = "http://localhost:7076";

    private final HttpClient client;
    private final Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

    public NanoClient() {
        this(DEFAULT_HOST);
    }

    public NanoClient(String host) {
        this(new HttpClient(host));
    }

    NanoClient(HttpClient client) {
        this.client = client;
    }

    // region Account Methods

    /**
     * Returns how many RAW is owned and how many have not yet been received for an account.
     *
     * @param account the address of the account
     */
    public Balance getAccountBalance(String account) {
        Request request = Request.action("account_balance")
            .param("account", account)
            .build();

        Balance balance = request(request, Balance.class);
        balance.setAccount(account);
        return balance;
    }

    /**
     * Get number of blocks for a specific account.
     *
     * @param account the address of the account
     */
    public AccountBlockCount getAccountBlockCount(String account) {
        Request request = Request.action("account_block_count")
            .param("account", account)
            .build();

        return request(request, AccountBlockCount.class);
    }

    /**
     * Returns frontier, open block, change representative block, balance, last
     * modified timestamp from local database & block count for account.
     *
     * @param account the address of the account
     * @see NanoClient#getAccountInformation(String, boolean, boolean, boolean)
     */
    public AccountInformation getAccountInformation(String account) {
        return getAccountInformation(account, false, false, false);
    }

    /**
     * Returns frontier, open block, change representative block, balance, last
     * modified timestamp from local database & block count for account.
     * <p>
     * This overloaded version can additionally returns representative, voting
     * weight, and pending balance for account.
     *
     * @param account        the address of the account
     * @param representative whether or not to return the representative for the account
     * @param weight         whether or not to return the voting weight of the account
     * @param pending        whether or not to return the pending balance of the account
     * @see NanoClient#getAccountInformation(String)
     */
    public AccountInformation getAccountInformation(String account,
                                                    boolean representative,
                                                    boolean weight,
                                                    boolean pending) {
        Request request = Request.action("account_info")
            .param("account", account)
            .param("representative", representative)
            .param("weight", weight)
            .param("pending", pending)
            .build();
        return request(request, AccountInformation.class);
    }

    /**
     * Creates a new account and inserts the next deterministic key in wallet.
     * <p>
     * Requires enable_account.
     *
     * @param wallet the specified wallet.
     * @return the address of the new account.
     */
    public AccountCreate createAccount(String wallet) {
        return createAccount(wallet, true);
    }

    /**
     * Creates a new account and inserts the next deterministic key in wallet.
     * <p>
     * This overloaded version takes can disable the generating the work after
     * the account is created.
     * <p>
     * Requires enable_account.
     *
     * @param wallet the specified wallet.
     * @param work   whether or not generate work.
     * @return the address of the new account.
     */
    public AccountCreate createAccount(String wallet, boolean work) {
        Request request = Request.action("account_create")
            .param("wallet", wallet)
            .param("work", work)
            .build();

        return request(request, AccountCreate.class);
    }

    /**
     * Get account number for the public key.
     *
     * @param publicKey the public key for an account.
     * @return the address of the associated account.
     */
    public AccountGet getAccount(String publicKey) {
        Request request = Request.action("account_get")
            .param("key", publicKey)
            .build();

        return request(request, AccountGet.class);
    }

    /**
     * Reports send/receive information for an account.
     *
     * @param account the account address.
     * @param count   the number of transactions to return.
     * @return the history for the account.
     */
    public AccountHistory getAccountHistory(String account, Integer count) {
        Request request = Request.action("account_history")
            .param("account", account)
            .param("count", count)
            .build();

        return request(request, AccountHistory.class);
    }

    /**
     * Lists all the accounts inside the wallet.
     *
     * @param wallet the wallet identifier.
     * @return the list of accounts.
     */
    public AccountList getAccountList(String wallet) {
        Request request = Request.action("account_list")
            .param("wallet", wallet)
            .build();

        return request(request, AccountList.class);
    }

    /**
     * Moves accounts from source to wallet.
     * <p>
     * Requires enable_control.
     *
     * @param from     the wallet to move from.
     * @param to       the wallet to move to.
     * @param accounts the accounts to move.
     * @return the number of accounts moved.
     */
    public AccountMove moveAccounts(String from, String to, List<String> accounts) {
        Request request = Request.action("account_move")
            .param("source", from)
            .param("wallet", to)
            .param("accounts", accounts)
            .build();

        return request(request, AccountMove.class);
    }

    /**
     * Get the public key for an account.
     *
     * @param account the account to retrieve the public key for.
     * @return the account's public key.
     */
    public AccountPublicKey getAccountPublicKey(String account) {
        Request request = Request.action("account_key")
            .param("account", account)
            .build();

        return request(request, AccountPublicKey.class);
    }

    /**
     * Remove account from wallet.
     * <p>
     * Requires enable_control.
     *
     * @param wallet  the wallet to remove the account from.
     * @param account the account to remove.
     * @return the number of accounts removed (1 for successful, 0 for unsuccessful).
     */
    public AccountRemove removeAccount(String wallet, String account) {
        Request request = Request.action("account_remove")
            .param("account", account)
            .param("wallet", wallet)
            .build();

        return request(request, AccountRemove.class);
    }

    /**
     * Returns the representative for an account.
     *
     * @param account the account to get the representative for.
     * @return the account's representative.
     */
    public AccountRepresentative getAccountRepresentative(String account) {
        Request request = Request.action("account_representative")
            .param("account", account)
            .build();

        return request(request, AccountRepresentative.class);
    }

    /**
     * Sets the representative for an account within a wallet.
     * <p>
     * Requires enable_control.
     *
     * @param wallet         the wallet associated with the account.
     * @param account        the account to set the representative in.
     * @param representative the representative to set.
     * @return the block associated with setting the account representative.
     * TODO: Add the optional work parameter
     */
    public AccountRepresentativeSet setAccountRepresentative(String wallet, String account, String representative) {
        Request request = Request.action("account_representative_set")
            .param("wallet", wallet)
            .param("account", account)
            .param("representative", representative)
            .build();

        return request(request, AccountRepresentativeSet.class);
    }

    /**
     * Returns the voting weight for an account.
     *
     * @param account the account to query voting weight for.
     * @return the account's voting weight.
     */
    public AccountWeight getAccountWeight(String account) {
        Request request = Request.action("account_weight")
            .param("account", account)
            .build();

        return request(request, AccountWeight.class);
    }

    /**
     * Returns how many RAW is owned and how many have not yet been received by a list of accounts.
     *
     * @param accounts the accounts to query.
     * @return the balances for each account.
     */
    public Balances getAccountBalances(List<String> accounts) {
        Request request = Request.action("accounts_balances")
            .param("accounts", accounts)
            .build();

        return request(request, Balances.class);
    }

    /**
     * Creates new accounts, insert next deterministic keys in wallet up to count.
     * <p>
     * Requires enable_control.
     *
     * @param wallet the wallet to create accounts in.
     * @param count  the number of accounts to create.
     * @return the created accounts.
     */
    public AccountsCreate createAccounts(String wallet, Integer count) {
        return createAccounts(wallet, count, true);
    }

    /**
     * Creates new accounts, insert next deterministic keys in wallet up to count.
     * <p>
     * This overloaded version allows disabling work generation after creating accounts.
     * <p>
     * Requires enable_control.
     *
     * @param wallet the wallet to create accounts in.
     * @param count  the number of accounts to create.
     * @param work   whether or not the generate work after creating accounts.
     * @return the created accounts.
     */
    public AccountsCreate createAccounts(String wallet, Integer count, boolean work) {
        Request request = Request.action("accounts_create")
            .param("wallet", wallet)
            .param("count", count)
            .param("work", work)
            .build();

        return request(request, AccountsCreate.class);
    }

    /**
     * Returns a list of pairs of account and block hash representing the
     * head block for an accounts list.
     *
     * @param accounts the accounts to retrieve.
     * @return the starting block for each account.
     */
    public AccountsFrontiers getAccountsFrontiers(List<String> accounts) {
        Request request = Request.action("accounts_frontiers")
            .param("accounts", accounts)
            .build();

        return request(request, AccountsFrontiers.class);
    }

    /**
     * Returns a list of block hashes which have not yet been received by a list of accounts.
     *
     * @param accounts the accounts to query.
     * @param count    the number of blocks to query.
     * @return the list of accounts and associated pending blocks.
     * TODO: Add the optional parameters to this method (threshold and source)
     */
    public AccountsPending getAccountsPending(List<String> accounts, Integer count) {
        Request request = Request.action("accounts_pending")
            .param("accounts", accounts)
            .param("count", count)
            .build();

        return request(request, AccountsPending.class);
    }

    // endregion

    // region Block Methods

    /**
     * Returns the account for an associated block.
     *
     * @param hash the hash of the block.
     * @return the account number.
     */
    public BlockAccount getBlockAccount(String hash) {
        Request request = Request.action("block_account")
            .param("hash", hash)
            .build();

        return request(request, BlockAccount.class);
    }

    /**
     * Reports the number of blocks in the ledger and unchecked synchronizing blocks.
     */
    public BlockCount getBlockCount() {
        Request request = Request.action("block_count").build();
        return request(request, BlockCount.class);
    }

    /**
     * Reports the number of blocks in the ledger by type (send, receive, open, change).
     */
    public BlockCountType getBlockCountType() {
        Request request = Request.action("block_count_type").build();
        return request(request, BlockCountType.class);
    }

    /**
     * Returns a list of block hashes in the account chain starting at block up to count.
     *
     * @param block the block to start at.
     * @param count the number of blocks to return.
     */
    public Chain getChain(String block, Integer count) {
        Request request = Request.action("chain")
            .param("block", block)
            .param("count", count)
            .build();

        return request(request, Chain.class);
    }

    // endregion

    // region Conversion Methods

    /**
     * Divide a raw amount down by the Mrai ratio.
     *
     * @param raw the amount in raw.
     * @return the amount in Mrai.
     */
    public String mraiFromRaw(String raw) {
        Request request = Request.action("mrai_from_raw")
            .param("amount", raw)
            .build();

        return request(request, ConversionResponse.class).getAmount();
    }

    /**
     * Multiply an Mrai amount by the Mrai ratio.
     *
     * @param mrai the amount in Mrai.
     * @return the amount in raw.
     */
    public String mraiToRaw(String mrai) {
        Request request = Request.action("mrai_to_raw")
            .param("amount", mrai)
            .build();

        return request(request, ConversionResponse.class).getAmount();
    }

    /**
     * Divide a raw amount down by the krai ratio.
     *
     * @param raw the amount in raw.
     * @return the amount in krai.
     */
    public String kraiFromRaw(String raw) {
        Request request = Request.action("krai_from_raw")
            .param("amount", raw)
            .build();

        return request(request, ConversionResponse.class).getAmount();
    }

    /**
     * Multiply an krai amount by the krai ratio.
     *
     * @param krai the amount in krai.
     * @return the amount in raw.
     */
    public String kraiToRaw(String krai) {
        Request request = Request.action("krai_to_raw")
            .param("amount", krai)
            .build();

        return request(request, ConversionResponse.class).getAmount();
    }

    /**
     * Divide a raw amount down by the rai ratio.
     *
     * @param raw the amount in raw.
     * @return the amount in rai.
     */
    public String raiFromRaw(String raw) {
        Request request = Request.action("rai_from_raw")
            .param("amount", raw)
            .build();

        return request(request, ConversionResponse.class).getAmount();
    }

    /**
     * Multiply an rai amount by the rai ratio.
     *
     * @param rai the amount in rai.
     * @return the amount in raw.
     */
    public String raiToRaw(String rai) {
        Request request = Request.action("rai_to_raw")
            .param("amount", rai)
            .build();

        return request(request, ConversionResponse.class).getAmount();
    }

    // endregion

    // region Node Methods

    /**
     * Returns version information for RPC, Store & Node (Major & Minor version).
     *
     * @return version information about the node.
     */
    public NodeVersion getNodeVersion() {
        Request request = Request.action("version").build();
        return request(request, NodeVersion.class);
    }

    /**
     * Method to safely shutdown node.
     * <p>
     * Requires enable_control.
     */
    public void stopNode() {
        Request request = Request.action("stop").build();
        request(request, VoidResponse.class);
    }

    // endregion

    // region Receive Methods

    /**
     * Receive a pending block for an account.
     * <p>
     * Requires enable_control.
     *
     * @param wallet  the wallet associated with the account.
     * @param account the account to receive into.
     * @param block   the pending block.
     * @return the newly created block.
     * TODO: Add optional work parameter
     */
    public Receive receive(String wallet, String account, String block) {
        Request request = Request.action("receive")
            .param("wallet", wallet)
            .param("account", account)
            .param("block", block)
            .build();

        return request(request, Receive.class);
    }

    /**
     * Get the minimum receive amount for a node.
     * <p>
     * Requires enable_control.
     *
     * @return the minimum receive amount.
     */
    public ReceiveMinimum getReceiveMinimum() {
        Request request = Request.action("receive_minimum").build();
        return request(request, ReceiveMinimum.class);
    }

    /**
     * Set the new receive minimum for a node until restart.
     * <p>
     * Requires enable_control.
     *
     * @param amount the new amount to set.
     */
    public void setReceiveMinimum(String amount) {
        Request request = Request.action("receive_minimum_set")
            .param("amount", amount)
            .build();

        request(request, VoidResponse.class);
    }

    // endregion

    // region Representative Methods

    /**
     * Returns a list of pairs of representatives and their respective voting weight.
     * TODO: Add optional count and sorting parameters
     */
    public Representatives getRepresentatives() {
        Request request = Request.action("representatives").build();
        return request(request, Representatives.class);
    }

    /**
     * Returns the default representative for the wallet.
     *
     * @param wallet the associated wallet.
     * @return the wallet's representative.
     */
    public WalletRepresentative getWalletRepresentative(String wallet) {
        Request request = Request.action("wallet_representative")
            .param("wallet", wallet)
            .build();

        return request(request, WalletRepresentative.class);
    }

    /**
     * Sets the default representative for a wallet.
     * <p>
     * Requires enable_control.
     *
     * @param wallet         the associated wallet.
     * @param representative the representative to set.
     */
    public void setWalletRepresentative(String wallet, String representative) {
        Request request = Request.action("wallet_representative_set")
            .param("wallet", wallet)
            .build();

        request(request, WalletRepresentativeSet.class);
        return;
    }

    // endregion

    // region Send Methods

    /**
     * Send funds from one account to another.
     * <p>
     * Requires enable_control.
     *
     * @param wallet      the wallet which contains the source account.
     * @param source      the account to send from.
     * @param destination the receiving account.
     * @param amount      the amount to send. // TODO: document the unit here
     * @return the block associated with the send transaction.
     */
    public Send send(String wallet, String source, String destination, String amount) {
        Request request = Request.action("send")
            .param("wallet", wallet)
            .param("source", source)
            .param("destination", destination)
            .param("amount", amount)
            .build();

        return request(request, Send.class);
    }

    // endregion

    // region Wallet Methods

    /**
     * Returns how many nano is owned and how many have not yet been
     * received by all accounts in wallet.
     *
     * @param wallet the wallet to check.
     * @return the balance and pending balance for each account in the wallet.
     * TODO: Add optional threshold
     */
    public Balances getWalletBalances(String wallet) {
        Request request = Request.action("wallet_balances")
            .param("wallet", wallet)
            .build();

        return request(request, Balances.class);
    }

    /**
     * Changes the seed for a wallet.
     * <p>
     * Requires enable_control.
     *
     * @param wallet the wallet to change the seed for.
     * @param seed   the new seed.
     */
    public void changeWalletSeed(String wallet, String seed) {
        Request request = Request.action("wallet_change_seed")
            .param("wallet", wallet)
            .param("seed", seed)
            .build();

        request(request, VoidResponse.class);
    }

    /**
     * Check whether the wallet contains account.
     *
     * @param wallet  the wallet to check.
     * @param account the account to check for.
     * @return true or false.
     */
    public boolean walletContainsAccount(String wallet, String account) {
        Request request = Request.action("wallet_contains")
            .param("wallet", wallet)
            .param("account", account)
            .build();

        return request(request, ExistenceResponse.class).exists();
    }

    // endregion

    private <T extends BaseResponse> T request(Request r, Class<T> clazz) {
        try {
            String json = gson.toJson(r.getMap());
            String body = client.post(json);
            System.out.println("Response: " + body);
            T t = gson.fromJson(body, clazz);
            if (!t.isSuccess()) {
                throw new NanoException(t.getError());
            }
            return t;
        } catch (IOException e) {
            throw new NanoException("Unable to communicate with node", e);
        }
    }
}
