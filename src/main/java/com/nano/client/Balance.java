package com.nano.client;

public class Balance extends BaseResponse {

    private String account;
    private String balance;
    private String pending;

    public String getAccount() {
        return account;
    }

    void setAccount(String account) {
        this.account = account;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public void setPending(String pending) {
        this.pending = pending;
    }

    public String getBalance() {
        return balance;
    }

    public String getPending() {
        return pending;
    }
}
