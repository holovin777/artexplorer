package space.artexplorer.api.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerRegistrationService {
    private final CustomerRepository customerRepository;
    @Autowired
    public CustomerRegistrationService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public void registerNewCustomer(CustomerRegistrationRequest request) {
        String phoneNumber = request.getCustomer().getPhoneNumber();
        Optional<Customer> customerOptional = customerRepository.selectCustomerByPhoneNumber(phoneNumber);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            if (customer.getFirstName().equals(request.getCustomer().getFirstName())) {
                return;
            }
            throw new IllegalStateException(String.format("Phone number [%s] is taken", phoneNumber));
        }
        customerRepository.save(request.getCustomer());
    }
}