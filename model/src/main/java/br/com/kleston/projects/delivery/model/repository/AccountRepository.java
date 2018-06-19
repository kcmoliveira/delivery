package br.com.kleston.projects.delivery.model.repository;

import br.com.kleston.projects.delivery.model.dtos.AccountDTO;

public interface AccountRepository extends BaseRespository<AccountDTO> {
    AccountDTO findByUsername(String username);
}