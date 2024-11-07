package co.sofka.usecase.customer;

import co.sofka.Customer;
import co.sofka.gateway.CustomerRepository;

public class DeleteCustomerUseCase {

    private final CustomerRepository customerRepository;

    public DeleteCustomerUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void apply(Customer customer) {
        customerRepository.deleteCustomer(customer);
    }
}
