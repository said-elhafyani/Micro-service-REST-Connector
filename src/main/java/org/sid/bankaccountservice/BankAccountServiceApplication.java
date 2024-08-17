package org.sid.bankaccountservice;

import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.entities.Customer;
import org.sid.bankaccountservice.enums.AccountType;
import org.sid.bankaccountservice.repositories.BankAccountRepository;
import org.sid.bankaccountservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.stream.Stream;

import static graphql.introspection.IntrospectionQueryBuilder.build;
import static org.springframework.data.util.TypeUtils.type;

@SpringBootApplication
public class BankAccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BankAccountRepository bankAccountRepository, CustomerRepository customerRepository){
        return args -> {

            Stream.of("mohamd","ali","salah","omar").forEach(name->{
                customerRepository.save(new Customer(null,name,null));
            });
            customerRepository.findAll().forEach(customer -> {
                for(int i=0; i<10; i++){
                    BankAccount bankAccount = BankAccount.builder().id(UUID.randomUUID().toString())
                            .type(Math.random() > 0.5 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVINGS_ACCOUNT)
                            .balance(Math.random()*10000)
                            .currency("USD")
                            .createdAt(new java.util.Date())
                            .customer(customer).build();
                    bankAccountRepository.save(bankAccount);

                }
            });

        };
    }
}
