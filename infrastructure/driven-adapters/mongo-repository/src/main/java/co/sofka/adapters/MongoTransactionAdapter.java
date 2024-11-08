package co.sofka.adapters;

import co.sofka.Transaction;
import co.sofka.data.AccountDocument;
import co.sofka.data.CustomerDocument;
import co.sofka.data.TransactionDocument;
import co.sofka.exception.GetNotFoundException;
import co.sofka.gateway.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Repository
public class MongoTransactionAdapter implements TransactionRepository {

    private final MongoTemplate mongoTemplate;

    public MongoTransactionAdapter(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void createTransaction(Transaction transaction) {

        Optional<AccountDocument>accountDocument= Optional.ofNullable(mongoTemplate.findById(transaction.getAccountId(), AccountDocument.class));

        if(accountDocument.isEmpty()) {
            throw new GetNotFoundException("Account does not exist");
        }

        Optional<CustomerDocument>customerDocument=Optional.ofNullable(mongoTemplate.findById(accountDocument.get().getCustomerId(), CustomerDocument.class));

        TransactionDocument transactionDocument = new TransactionDocument();
        transactionDocument.setAmount(transaction.getAmount());
        transactionDocument.setAmountCost(transaction.getAmountCost());
        transactionDocument.setTypeOfTransaction(transaction.getType());

        accountDocument.get().getTransactions().add(transactionDocument);
        customerDocument.get().setAccount(accountDocument.get());


        mongoTemplate.save(customerDocument.get());
    }

    @Override
    public Transaction getTransaction(Transaction transaction) {

        Optional<TransactionDocument> transactionDocument = Optional.ofNullable(mongoTemplate.findById(transaction.getId(), TransactionDocument.class));

        if (transactionDocument.isEmpty()) {
            throw new GetNotFoundException("Transaction does not exist");
        }

        ZoneOffset zoneOffset = ZoneOffset.UTC;
        OffsetDateTime offsetDateTime = transactionDocument.get().getTimeStamp().atOffset(zoneOffset);
        return new Transaction(
                transactionDocument.get().getId(),
                transactionDocument.get().getAmount(),
                transactionDocument.get().getAmountCost(),
                transactionDocument.get().getTypeOfTransaction(),
                offsetDateTime
        );

    }
}
