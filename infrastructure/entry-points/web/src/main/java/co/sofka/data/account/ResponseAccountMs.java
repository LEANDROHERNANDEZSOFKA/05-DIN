package co.sofka.data.account;

import co.sofka.Account;
import co.sofka.DinError;
import co.sofka.DinHeader;
import co.sofka.ResponseMs;
import co.sofka.data.customer.CustomerDto;

public class ResponseAccountMs extends ResponseMs<AccountDto> {

    public ResponseAccountMs(AccountDto accountDto) {
        super(new DinHeader(), accountDto, defaultDinError());
    }


    public ResponseAccountMs(DinHeader dinHeader, AccountDto accountDto) {
        super(dinHeader != null ? dinHeader : new DinHeader(), accountDto, defaultDinError());
    }

    public ResponseAccountMs(DinError dinError) {
        super(new DinHeader(), null, dinError != null ? dinError : defaultDinError());
    }

    public ResponseAccountMs(AccountDto accountDto, DinError dinError) {
        super(new DinHeader(), accountDto, dinError != null ? dinError : defaultDinError());
    }

    public ResponseAccountMs(DinHeader dinHeader, AccountDto accountDto, DinError dinError) {
        super(dinHeader != null ? dinHeader : new DinHeader(), accountDto, dinError != null ? dinError : defaultDinError());
    }

    private static DinError defaultDinError() {
        return new DinError();
    }
}
