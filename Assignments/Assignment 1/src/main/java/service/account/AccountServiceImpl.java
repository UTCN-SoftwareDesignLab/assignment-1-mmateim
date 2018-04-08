package service.account;

import model.Account;
import repository.EntityNotFoundException;
import repository.account.AccountRepository;
import repository.user.UserRepository;

import java.util.List;

/**
 * Created by Alex on 07/03/2017.
 */
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountServiceImpl(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Boolean delete(Long id) {
        return accountRepository.delete(id);
    }

    @Override
    public Boolean update(Long id, Account account) {
        return accountRepository.update(id, account);
    }

    @Override
    public Account findById(Long id) throws EntityNotFoundException {
        return accountRepository.findById(id);
    }

    @Override
    public boolean save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> findByUser(String cnp) {
        Long userId = userRepository.findIdByCnp(cnp);
        return accountRepository.findByUser(userId);
    }

    @Override
    public Account findByIban(String iban) {
        return accountRepository.findByIban(iban);
    }
}
