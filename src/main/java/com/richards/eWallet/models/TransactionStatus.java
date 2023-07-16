package com.richards.eWallet.models;


public enum TransactionStatus {

    SUCCESSFUL("SUCCESSFUL"),
    PENDING("PENDING"),
    FAILED("FAILED");

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;

    TransactionStatus(String status) {
        this.status = status;
    }

    public TransactionStatus getStatus() {
        return transactions.getStatus();
    }


    private Transactions transactions;
}
