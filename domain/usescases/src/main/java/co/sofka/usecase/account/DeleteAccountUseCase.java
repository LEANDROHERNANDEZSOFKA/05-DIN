package co.sofka.usecase.account;

import co.sofka.Account;
import co.sofka.gateway.AccountRepository;

public class DeleteAccountUseCase {

    private final AccountRepository accountRepository;

    public DeleteAccountUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void apply(Account account) {
        accountRepository.deleteAccount(account);
    }
}
