package br.com.kleston.projects.delivery.model.repository.impl;

import br.com.kleston.projects.delivery.model.dtos.AccountDTO;
import br.com.kleston.projects.delivery.model.jooq.tables.Account;
import br.com.kleston.projects.delivery.model.repository.AccountRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositoryImpl extends BaseRepositoryImpl<Account, AccountDTO> implements AccountRepository {
    public static final Account table = Account.ACCOUNT;
    public static final Class<AccountDTO> dtoClass = AccountDTO.class;

    public AccountRepositoryImpl() {
        super( table, dtoClass );
    }

    @Override
    public AccountDTO findByUsername(String username) {
        return this.getDsl().select()
                .from( this.table )
                .where( Account.ACCOUNT.USERNAME.eq( username ) ).fetchOneInto( this.dtoClass );
    }
}