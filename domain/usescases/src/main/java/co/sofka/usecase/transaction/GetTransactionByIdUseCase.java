package co.sofka.usecase.transaction;

import co.sofka.Transaction;
import co.sofka.gateway.TransactionRepository;

public class GetTransactionByIdUseCase {

    private final TransactionRepository transactionRepository;

    public GetTransactionByIdUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction apply(Transaction transaction) {
        return transactionRepository.getTransaction(transaction);
    }
}
