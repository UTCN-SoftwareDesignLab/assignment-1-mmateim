package repository.account;

import model.Account;
import repository.EntityNotFoundException;

import java.util.List;

/**
 * Created by Alex on 07/03/2017.
 */
public interface AccountRepository {

    List<Account> findAll();

    Account findById(Long id) throws EntityNotFoundException;

    boolean save(Account book);

    void removeAll();

    List<Account> findByUser(Long userId);

    Account findByIban(String iban);

    Boolean delete(Long id);

    Boolean update(Long id, Account account);

}
