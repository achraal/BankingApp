package com.example.bankingapp.Repositories;

import com.example.bankingapp.Entities.BankAccount;
import com.example.bankingapp.Entities.Customer;
import com.example.bankingapp.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
