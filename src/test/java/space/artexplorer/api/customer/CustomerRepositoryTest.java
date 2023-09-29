package space.artexplorer.api.customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository underTest;

    @Test
    void itShouldSelectCustomerByPhoneNumber() {
        // Given
        // When
        // Then
    }

    @Test
    void itShouldSaveCustomer() {

        // Given
        Customer customer = new Customer(
                "NameTest",
                "LastNameTest",
                "+3987654321",
                "emailtest@test.com",
                "test.com",
                true);

        // When
        this.underTest.save(customer);

        // Then
        Optional<Customer> optionalCustomer = this.underTest.findById(customer.getId());
        assertThat(optionalCustomer)
                .isPresent()
                .hasValueSatisfying(c -> {
                    assertThat(c.getFirstName()).isEqualTo("NameTest");
                    assertThat(c.getLastName()).isEqualTo("LastNameTest");
                    assertThat(c.getPhoneNumber()).isEqualTo("+3987654321");
                    assertThat(c.getWebsite()).isEqualTo("test.com");
                    assertThat(c.getIsAdmin()).isEqualTo(true);
                });
    }
}