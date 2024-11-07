package co.sofka.gateway;

import co.sofka.Transaction;

public interface TransactionRepository {
    void createTransaction(Transaction transaction);
    Transaction getTransaction(Transaction transaction);
}
