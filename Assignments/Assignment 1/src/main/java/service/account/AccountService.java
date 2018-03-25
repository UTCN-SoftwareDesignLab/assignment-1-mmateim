package service.account;

import model.Account;
import repository.EntityNotFoundException;

import java.util.List;

/**
 * Created by Alex on 07/03/2017.
 */
public interface AccountService {

    List<Account> findAll();

    Account findById(Long id) throws EntityNotFoundException;

    boolean save(Account book);

}
