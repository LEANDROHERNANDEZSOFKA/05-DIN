package co.sofka.adapters;

import co.sofka.Account;
import co.sofka.data.AccountDocument;
import co.sofka.data.CustomerDocument;
import co.sofka.exception.GetNotFoundException;
import co.sofka.gateway.AccountRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public class MongoAccountAdapter implements AccountRepository {

    private final MongoTemplate mongoTemplate;

    public MongoAccountAdapter(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void createAccount(Account account) {
        Optional<CustomerDocument>customerDocument = Optional.ofNullable(mongoTemplate.findById(account.getCustomerId(),CustomerDocument.class));

        if(customerDocument.isEmpty()) {
            throw new GetNotFoundException("Customer not found");
        }

        AccountDocument accountDocument = new AccountDocument();
        accountDocument.setNumber(account.getNumber());
        accountDocument.setAmount(account.getAmount());
        accountDocument.setCreatedAt(LocalDate.now());
        accountDocument.setDeleted(false);
        accountDocument.setCustomerId(customerDocument.get().getId());


        customerDocument.get().setAccount(accountDocument);

        mongoTemplate.save(accountDocument);
        mongoTemplate.save(customerDocument.get());
    }


    @Override
    public void deleteAccount(Account account) {
        AccountDocument accountDocument = mongoTemplate.findById(account.getId(), AccountDocument.class);
        assert accountDocument != null;
        accountDocument.setDeleted(true);
        mongoTemplate.save(accountDocument);
    }


    @Override
    public Account getAccount(Account account) {

        Optional<AccountDocument>accountDocument=Optional.ofNullable(mongoTemplate.findById(account.getId(),AccountDocument.class));

        if(accountDocument.isEmpty()) {
            throw new GetNotFoundException("Account does not exist");
        }

        return new Account(accountDocument.get().getId(),
                accountDocument.get().getNumber(),
                accountDocument.get().getAmount(),
                account.getId(),
                accountDocument.get().getCreatedAt());
    }

    @Override
    public void updateAccount(Account account) {
        Optional<AccountDocument>accountDocument=Optional.ofNullable(mongoTemplate.findById(account.getId(),AccountDocument.class));

        if(accountDocument.isEmpty()) {
            throw new GetNotFoundException("Account does not exist");
        }

        accountDocument.get().setAmount(account.getAmount());

        mongoTemplate.save(accountDocument.get());
    }

}
