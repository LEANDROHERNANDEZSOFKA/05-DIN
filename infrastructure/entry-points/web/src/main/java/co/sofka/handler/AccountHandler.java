package co.sofka.handler;

import co.sofka.Account;
import co.sofka.data.account.AccountDto;
import co.sofka.exceptions.InvalidCreationException;
import co.sofka.exceptions.InvalidFundsException;
import co.sofka.usecase.account.CreateAccountUseCaseImpl;
import co.sofka.usecase.account.DeleteAccountUseCaseImpl;
import co.sofka.usecase.account.GetAccountByIdUseCaseImpl;
import co.sofka.usecase.account.UpdateAccountUseCaseImpl;
import cryptography.AESUtilAdapter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AccountHandler {

    private final CreateAccountUseCaseImpl createAccountUseCaseImpl;
    private final GetAccountByIdUseCaseImpl getAccountByIdUseCaseImpl;
    private final UpdateAccountUseCaseImpl updateAccountUseCaseImpl;
    private final DeleteAccountUseCaseImpl deleteAccountUseCaseImpl;


    public AccountHandler(CreateAccountUseCaseImpl createAccountUseCaseImpl, GetAccountByIdUseCaseImpl getAccountByIdUseCaseImpl, UpdateAccountUseCaseImpl updateAccountUseCaseImpl, DeleteAccountUseCaseImpl deleteAccountUseCaseImpl) {
        this.createAccountUseCaseImpl = createAccountUseCaseImpl;
        this.getAccountByIdUseCaseImpl = getAccountByIdUseCaseImpl;
        this.updateAccountUseCaseImpl = updateAccountUseCaseImpl;
        this.deleteAccountUseCaseImpl = deleteAccountUseCaseImpl;
    }

    public void createAccount(AccountDto accountDTO) {
        try {
            Account account = new Account();
            account.setNumber(Integer.parseInt(accountDTO.getNumber()));
            account.setAmount(accountDTO.getAmount());
            account.setCustomerId(accountDTO.getCustomerId());
            account.setCreatedAt(LocalDate.now());
            createAccountUseCaseImpl.apply(account);
        } catch (InvalidCreationException e) {
            throw new InvalidCreationException(e.getMessage());
        } catch (NumberFormatException e) {
            throw new NumberFormatException(e.getMessage());
        } catch (InvalidFundsException e) {
            throw new InvalidFundsException(e.getMessage());
        }
    }

    public void deleteAccount(AccountDto accountDTO) {
        Account account = new Account();
        account.setId(accountDTO.getId());
        deleteAccountUseCaseImpl.apply(account);
    }

    public AccountDto getAccountById(AccountDto accountDTO) {
        AESUtilAdapter adapter = new AESUtilAdapter();
        System.out.println("Account number en getAccountById: " + accountDTO.getNumber());
        try {
            Account account = getAccountByIdUseCaseImpl.apply(new Account(Integer.parseInt(accountDTO.getNumber())));

            return new AccountDto(
                    String.valueOf(account.getNumber()),
                    account.getAmount(),
                    account.getCustomerId(),
                    account.getCreatedAt());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateAccount(AccountDto accountDTO) {
        Account account = new Account();
        account.setNumber(Integer.parseInt(accountDTO.getNumber()));
        account.setAmount(accountDTO.getAmount());
        account.setCustomerId(accountDTO.getCustomerId());
        account.setCreatedAt(accountDTO.getCreatedAt());
        updateAccountUseCaseImpl.apply(account);
    }
}
