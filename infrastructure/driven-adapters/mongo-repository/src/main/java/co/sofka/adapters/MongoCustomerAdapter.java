package co.sofka.adapters;

import co.sofka.Customer;
import co.sofka.data.CustomerDocument;
import co.sofka.exception.GetNotFoundException;
import co.sofka.gateway.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public class MongoCustomerAdapter implements CustomerRepository {

    private final MongoTemplate mongoTemplate;

    public MongoCustomerAdapter(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void createCustomer(Customer customer) {
        CustomerDocument customerDocument = new CustomerDocument();
        customerDocument.setName(customer.getName());
        customerDocument.setDeleted(false);
        customerDocument.setCreatedAt(LocalDate.now());
        mongoTemplate.save(customerDocument);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        CustomerDocument customerDocument = mongoTemplate.findById(customer.getId(), CustomerDocument.class);
        if(customerDocument != null) {
            customerDocument.setDeleted(true);
            mongoTemplate.save(customerDocument);
        }
    }

    @Override
    public Customer getCustomer(Customer customer) {
        Optional<CustomerDocument>customerDocument= Optional.ofNullable(mongoTemplate.findById(customer.getId(), CustomerDocument.class));

        if(customerDocument.isEmpty()) {
            throw new GetNotFoundException("Customer does not exist");
        }

        return new Customer(
                customerDocument.get().getId(),
                customerDocument.get().getName(),
                customerDocument.get().getCreatedAt()
        );
    }

}
