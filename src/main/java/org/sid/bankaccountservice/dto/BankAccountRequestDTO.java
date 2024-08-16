package org.sid.bankaccountservice.dto;

import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.bankaccountservice.enums.AccountType;

import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
public class BankAccountRequestDTO {

    private Double balance;
    private String currency;
    private AccountType type;
}
