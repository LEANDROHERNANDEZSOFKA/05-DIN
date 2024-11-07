package co.sofka.handler;

import co.sofka.Transaction;
import co.sofka.data.transaction.TransactionDto;
import co.sofka.usecase.transaction.CreateTransactionUseCase;
import co.sofka.usecase.transaction.GetTransactionByIdUseCase;
import org.springframework.stereotype.Component;

@Component
public class TransactionHandler {

    private final CreateTransactionUseCase transactionUseCase;
    private final GetTransactionByIdUseCase transactionByIdUseCase;

    public TransactionHandler(CreateTransactionUseCase transactionUseCase, GetTransactionByIdUseCase transactionByIdUseCase) {
        this.transactionUseCase = transactionUseCase;
        this.transactionByIdUseCase = transactionByIdUseCase;
    }

    public void createTransaction(TransactionDto transactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setType(transactionDTO.getType());
        transaction.setAccountId(transactionDTO.getAccountId());
        transactionUseCase.apply(transaction);
    }


    public TransactionDto getTransactionById(TransactionDto getTransactionDTO){
        Transaction transaction= transactionByIdUseCase.apply(new Transaction(getTransactionDTO.getId()));
        return new TransactionDto(
                transaction.getId(),
                transaction.getAccountId(),
                transaction.getAmount(),
                transaction.getAmountCost(),
                transaction.getType(),
                transaction.getTimeStamp()
        );
    }
}


