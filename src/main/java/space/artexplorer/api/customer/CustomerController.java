package space.artexplorer.api.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path = "all")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping
    public void setCustomer(@RequestBody Customer customer) {
        this.customerService.setCustomer(customer);
    }

    @DeleteMapping(path="{customerId}")
    public void deleteCustomer(@PathVariable Long customerId) {
        this.customerService.deleteCustomer(customerId);
    }

    @GetMapping(path = "{customerId}")
    public Customer getCustomer(@PathVariable Long customerId) {
        return this.customerService.getCustomer(customerId);
    }

    @PutMapping(path = "{customerId}/update")
    public void updateCustomer(
            @PathVariable Long customerId,
            @RequestParam(required = false) String customerFirstName,
            @RequestParam(required = false) String customerLastName,
            @RequestParam(required = false) String customerPhoneNumber,
            @RequestParam(required = false) String customerEmail,
            @RequestParam(required = false) String customerWebsite
    ) {
        this.customerService.updateCustomer(
                customerId,
                customerFirstName,
                customerLastName,
                customerPhoneNumber,
                customerEmail,
                customerWebsite
        );
    }

    @PutMapping(path = "{customerId}/change-customer-role")
    public void changeCustomerRole(@PathVariable Long customerId,
                                   @RequestParam boolean customerIsAdmin) {
        this.customerService.changeCustomerRole(customerId, customerIsAdmin);
    }

}
