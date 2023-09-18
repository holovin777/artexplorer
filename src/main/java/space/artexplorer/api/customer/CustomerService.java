package space.artexplorer.api.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CustomerService {

    private final CustomerRepository customerRepository;
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers() {
        return this.customerRepository.findAll();
    }

    @Transactional
    public void setCustomer(Customer customer) {
        this.customerRepository.save(customer);
    }

    public Customer getCustomer(Long customerId) {
        Optional<Customer> customerOptional = this.customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            return customerOptional.get();
        }
        throw new IllegalStateException("Customer with ID " + customerId + " doesn't exists");
    }

    @Transactional
    public void deleteCustomer(Long customerId) {
        Optional<Customer> customerOptional = this.customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            this.customerRepository.delete(customer);
        } else {
            throw new IllegalStateException("Customer with ID " + customerId + " doesn't exists");
        }
    }

    @Transactional
    public void updateCustomer(Long customerId,
                               String customerFirstName,
                               String customerLastName,
                               String customerPhoneNumber,
                               String customerEmail,
                               String customerWebsite) {
        Optional<Customer> customerOptional = this.customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            if (customerFirstName != null) {
                customer.setFirstName(customerFirstName);
                customerRepository.save(customer);
            }
            if (customerLastName != null) {
                customer.setLastName(customerLastName);
                customerRepository.save(customer);
            }
            if (customerPhoneNumber != null) {
                customer.setPhoneNumber(customerPhoneNumber);
                customerRepository.save(customer);
            }
            if (customerEmail != null) {
                customer.setEmail(customerEmail);
                customerRepository.save(customer);
            }
            if (customerWebsite != null) {
                customer.setWebsite(customerWebsite);
                customerRepository.save(customer);
            }
        } else {
            throw new IllegalStateException("Customer with ID " + customerId + " doesn't exists");
        }
    }

    @Transactional
    public void changeCustomerRole(Long customerId, boolean customerIsAdmin) {
        Optional<Customer> customerOptional = this.customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.setAdmin(customerIsAdmin);
        } else {
            throw new IllegalStateException("Customer with ID " + customerId + " doesn't exists");
        }

    }

}
