package org.sid.bankaccountservice.repositories;

import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource // Expose the repository as a REST service
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

   // url : http://localhost:8081/api/bankAccounts/search/findByType?type=SAVINGS_ACCOUNT
   @RestResource(path = "/byType")
   List<BankAccount> findByType(@Param("t") AccountType type);
   // url : http://localhost:8081/api/bankAccounts/search/byType?t=SAVINGS_ACCOUNT

}
