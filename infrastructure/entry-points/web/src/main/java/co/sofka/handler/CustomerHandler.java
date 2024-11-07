package co.sofka.handler;

import co.sofka.Customer;
import co.sofka.data.customer.CustomerDto;
import co.sofka.exception.GetNotFoundException;
import co.sofka.exceptions.InvalidNameCustomerException;
import co.sofka.usecase.customer.CreateCustomerUseCase;
import co.sofka.usecase.customer.DeleteCustomerUseCase;
import co.sofka.usecase.customer.GetCustomerByIdUseCase;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerHandler {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;
    private final GetCustomerByIdUseCase getCustomerByIdUseCase;

    public CustomerHandler(CreateCustomerUseCase createCustomerUseCase, DeleteCustomerUseCase deleteCustomerUseCase, GetCustomerByIdUseCase getCustomerByIdUseCase) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.deleteCustomerUseCase = deleteCustomerUseCase;
        this.getCustomerByIdUseCase = getCustomerByIdUseCase;
    }

    public void createCustomer(CustomerDto customerDto) {
        try{
            Customer customer = new Customer();
            customer.setName(customerDto.getName());
            createCustomerUseCase.apply(customer);
        }catch (InvalidNameCustomerException e){
            throw new InvalidNameCustomerException(e.getMessage());
        }
    }

    public void deleteCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        deleteCustomerUseCase.apply(customer);
    }

    public CustomerDto getCustomerById(CustomerDto getCustomerByIdDTO) {
        try{
            Optional<Customer>customer=Optional.ofNullable(getCustomerByIdUseCase.apply(new Customer(getCustomerByIdDTO.getId())));

            return new CustomerDto(
                    customer.get().getId(),
                    customer.get().getName(),
                    customer.get().getCreatedAt()
            );
        }catch (GetNotFoundException e){
            throw new GetNotFoundException("Customer does not exist");
        }
    }

}
