package co.sofka.usecase.account;

import co.sofka.Account;
import co.sofka.in.account.UpdateAccountUseCase;
import co.sofka.out.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateAccountUseCaseImpl implements UpdateAccountUseCase {

    private final AccountRepository accountRepository;

    public UpdateAccountUseCaseImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public void apply(Account account) {
        accountRepository.updateAccount(account);
    }
}
