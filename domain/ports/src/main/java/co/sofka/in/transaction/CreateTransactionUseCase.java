package co.sofka.in.transaction;

import co.sofka.Transaction;

public interface CreateTransactionUseCase {
    void apply(Transaction transaction);
}
