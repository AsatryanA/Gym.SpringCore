package com.epam.gym.aspect;

public class TransactionIdHolder {

    private TransactionIdHolder() {
    }

    private static final ThreadLocal<String> transactionId = new ThreadLocal<>();

    public static String getTransactionId() {
        return transactionId.get();
    }

    public static void setTransactionId(String id) {
        transactionId.set(id);
    }

    public static void clearTransactionId() {
        transactionId.remove();
    }
}
