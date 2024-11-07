package co.sofka.usecase.transaction;

import co.sofka.Transaction;
import co.sofka.exceptions.InvalidCreationException;
import co.sofka.gateway.TransactionRepository;
import co.sofka.usecase.strategy.AccountMovementContext;

public class CreateTransactionUseCase {

    private final TransactionRepository transactionRepository;


    public CreateTransactionUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void apply(Transaction transaction) {

        if(transaction == null){
            throw new InvalidCreationException("Transaction cannot be null");
        }

        Transaction transaction1=AccountMovementContext.accountMovement(transaction).movement(transaction);
        transactionRepository.createTransaction(transaction1);
    }

}
