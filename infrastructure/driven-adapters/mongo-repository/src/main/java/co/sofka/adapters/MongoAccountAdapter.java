package co.sofka.adapters;

import co.sofka.Account;
import co.sofka.data.AccountDocument;
import co.sofka.data.CustomerDocument;
import co.sofka.data.UserDocument;
import co.sofka.exception.GetNotFoundException;
import co.sofka.out.AccountRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
        Optional<UserDocument> userDocument = Optional.ofNullable(mongoTemplate.findById(account.getCustomerId(), UserDocument.class));

        if (userDocument.isEmpty()) {
            throw new GetNotFoundException("Customer not found");
        }


        AccountDocument accountDocument = new AccountDocument();
        accountDocument.setNumber(account.getNumber());
        accountDocument.setAmount(account.getAmount());
        accountDocument.setCreatedAt(LocalDate.now());
        accountDocument.setDeleted(false);
        accountDocument.setCustomerId(userDocument.get().getCustomer().getId());


        userDocument.get().getCustomer().setAccount(accountDocument);

        mongoTemplate.save(userDocument.get());
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
        System.out.println("Numero: "+account.getNumber());

        Query query = new Query(Criteria.where("customer.account_customer.number").is(321345));
        UserDocument user = mongoTemplate.findOne(query, UserDocument.class);

        System.out.println("Documento encontrado por número: " + user);

        if (user != null) {
            return new Account(user.getCustomer().getAccount().getId()
                    , user.getCustomer().getAccount().getNumber(),
                    user.getCustomer().getAccount().getAmount(),
                    user.getCustomer().getAccount().getCustomerId(),
                    user.getCustomer().getAccount().getCreatedAt());
        }
        return null;
    }

    @Override
    public void updateAccount(Account account) {
        Optional<AccountDocument> accountDocument = Optional.ofNullable(mongoTemplate.findById(account.getId(), AccountDocument.class));

        if (accountDocument.isEmpty()) {
            throw new GetNotFoundException("Account does not exist");
        }

        accountDocument.get().setAmount(account.getAmount());

        mongoTemplate.save(accountDocument.get());
    }

}
