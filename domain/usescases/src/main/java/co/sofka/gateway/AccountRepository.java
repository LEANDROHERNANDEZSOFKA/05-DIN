package co.sofka.gateway;

import co.sofka.Account;

public interface AccountRepository {
    void createAccount(Account account);
    void deleteAccount(Account account);
    Account getAccount(Account account);
    void updateAccount(Account account);
}
