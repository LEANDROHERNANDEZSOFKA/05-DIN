package co.sofka.usecase.strategy;

import co.sofka.Transaction;

public interface TypeTransaction {
    Transaction movement(Transaction transaction);
}
