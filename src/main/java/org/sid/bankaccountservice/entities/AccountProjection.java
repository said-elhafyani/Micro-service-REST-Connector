package org.sid.bankaccountservice.entities;

import org.springframework.data.rest.core.config.Projection;

// url : http://localhost:8081/api/bankAccounts?projection=p1
@Projection(types = BankAccount.class, name = "p1")
public interface AccountProjection {
    public String getId();
    public String getType();
}


