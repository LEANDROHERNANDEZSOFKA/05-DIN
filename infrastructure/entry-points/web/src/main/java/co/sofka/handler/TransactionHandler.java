package co.sofka.handler;

import co.sofka.Transaction;
import co.sofka.data.transaction.TransactionDto;
import co.sofka.usecase.transaction.CreateTransactionUseCaseImpl;
import co.sofka.usecase.transaction.GetTransactionByIdUseCaseImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionHandler {

    private final CreateTransactionUseCaseImpl transactionUseCase;
    private final GetTransactionByIdUseCaseImpl transactionByIdUseCase;

    public TransactionHandler(CreateTransactionUseCaseImpl transactionUseCase, GetTransactionByIdUseCaseImpl transactionByIdUseCase) {
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


    public List<TransactionDto> getTransactionsByUserId(TransactionDto getTransactionDTO) {

        List<Transaction> transactions = transactionByIdUseCase.apply(new Transaction(getTransactionDTO.getId()));

        return transactions.stream()
                .map(transaction -> new TransactionDto(
                        transaction.getId(),
                        transaction.getAccountId(),
                        transaction.getAmount(),
                        transaction.getAmountCost(),
                        transaction.getType(),
                        transaction.getTimeStamp()
                ))
                .collect(Collectors.toList());
    }

}


