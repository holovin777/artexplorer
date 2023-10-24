package space.artexplorer.api.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class CustomerRegistrationServiceTest {

    private CustomerRepository customerRepository = mock(CustomerRepository.class);

    private CustomerRegistrationService underTest;

    @BeforeEach
    void setUp() {
        underTest = new CustomerRegistrationService(customerRepository);
    }

    @Test
    void itShouldRegisterNewCustomer() {
        // Given
        Customer customer = new Customer(
                "NameTest",
                "LastNameTest",
                "00099",
                "email@test.com",
                "test.com",
                true);
        // request
        CustomerRegistrationRequest request = new CustomerRegistrationRequest(customer);
        given(customerRepository.selectCustomerByPhoneNumber("00099")).willReturn(Optional.empty());

        // When
        underTest.registerNewCustomer(request);

        // Then
    }
}