package co.sofka.usecase.account;

import co.sofka.Account;
import co.sofka.gateway.AccountRepository;

public class UpdateAccountUseCase {

    private final AccountRepository accountRepository;

    public UpdateAccountUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public void apply(Account account) {
        accountRepository.updateAccount(account);
    }
}
