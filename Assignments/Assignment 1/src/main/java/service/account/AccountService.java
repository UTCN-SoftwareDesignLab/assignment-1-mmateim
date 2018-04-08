package service.account;

import model.Account;
import repository.EntityNotFoundException;

import java.util.List;

/**
 * Created by Alex on 07/03/2017.
 */
public interface AccountService {

    List<Account> findAll();

    Boolean update(Long id, Account account);

    Account findById(Long id) throws EntityNotFoundException;

    boolean save(Account account);

    void transferMoney(Float amount, String ibanReceiver, Account sender);

    List<Account> findByUser(String cnp);

    Account findByIban(String iban);

    Boolean delete(Long id);
}
