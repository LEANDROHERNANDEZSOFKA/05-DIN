package co.sofka.usecase.customer;

import co.sofka.Customer;
import co.sofka.gateway.CustomerRepository;

public class GetCustomerByIdUseCase {

    private final CustomerRepository customerRepository;


    public GetCustomerByIdUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer apply(Customer customer) {
        return customerRepository.getCustomer(customer);
    }
}
