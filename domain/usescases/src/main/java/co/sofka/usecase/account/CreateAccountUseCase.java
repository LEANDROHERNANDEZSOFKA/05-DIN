package co.sofka.usecase.account;

import co.sofka.Account;
import co.sofka.exceptions.InvalidCreationException;
import co.sofka.exceptions.InvalidFundsException;
import co.sofka.exceptions.InvalidNumberException;
import co.sofka.gateway.AccountRepository;

import java.math.BigDecimal;

public class CreateAccountUseCase {

    private final AccountRepository accountRepository;

    public CreateAccountUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void apply(Account account) {

        if (account == null) {
            throw new InvalidCreationException("The account cannot be null");
        }

        if (account.getAmount() == null || account.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidFundsException("The amount cannot be negative or null: " + account.getAmount());
        }

        if (account.getNumber() <= 0) {
            throw new InvalidNumberException("The account number must be a positive integer: " + account.getNumber());
        }

        accountRepository.createAccount(account);
    }

}
