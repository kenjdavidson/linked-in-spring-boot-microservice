package kjd.linkedin.explorecali.initializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import kjd.linkedin.explorecali.customer.Customer;
import kjd.linkedin.explorecali.customer.CustomerRepository;

@Component
public class CustomerAppRunner implements ApplicationRunner {

    Logger logger = LoggerFactory.getLogger(CustomerAppRunner.class);

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Loading Customer data");
        loadData();
        
        logger.info("Loaded {} customers", customerRepository.count());
    }
    
    private void loadData() {
        customerRepository.save(buildCustomer("Ken", "Davidson", "ken.j.davidson@live.ca"));
    }

    private Customer buildCustomer(String firstName, String lastName, String email) {
        return new Customer(firstName, lastName, email);
    }
}
