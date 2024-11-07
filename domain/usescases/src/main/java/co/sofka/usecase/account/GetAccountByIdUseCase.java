package co.sofka.usecase.account;

import co.sofka.Account;
import co.sofka.gateway.AccountRepository;

public class GetAccountByIdUseCase {

    private final AccountRepository accountRepository;

    public GetAccountByIdUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account apply(Account account) {
        return accountRepository.getAccount(account);
    }
}
