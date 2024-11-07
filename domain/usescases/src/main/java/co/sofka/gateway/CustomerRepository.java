package co.sofka.gateway;

import co.sofka.Customer;

public interface CustomerRepository {
    void createCustomer(Customer customer);
    void deleteCustomer(Customer customer);
    Customer getCustomer(Customer customer);
}
