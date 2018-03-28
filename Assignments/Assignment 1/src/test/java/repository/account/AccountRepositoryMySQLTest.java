package repository.account;

import database.DBConnectionFactory;
import model.Account;
import model.builder.AccountBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.Cache;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Alex on 07/03/2017.
 */
public class AccountRepositoryMySQLTest {

    private static AccountRepository accountRepository;

    @BeforeClass
    public static void setupClass() {
        accountRepository = new AccountRepositoryCacheDecorator(
                new AccountRepositoryMySQL(
                        new DBConnectionFactory().getConnectionWrapper(true).getConnection()
                ),
                new Cache<>()
        );
    }

    @Before
    public void cleanUp() {
        accountRepository.removeAll();
    }

    @Test
    public void findAll() throws Exception {
        List<Account> accounts = accountRepository.findAll();
        assertEquals(accounts.size(), 0);
    }

    @Test
    public void findAllWhenDbNotEmpty() throws Exception {
        Account account = new AccountBuilder()
                .setHolderID(123L)
                .setBalance(250.4f)
                .setIBAN("RO12345")
                .setType("savings")
                .build();
        accountRepository.save(account);
        accountRepository.save(account);
        accountRepository.save(account);

        List<Account> accounts = accountRepository.findAll();
        assertEquals(accounts.size(), 3);
    }

    @Test
    public void findById() throws Exception {

    }

    @Test
    public void save() throws Exception {
        assertTrue(accountRepository.save(
                new AccountBuilder()
                        .setHolderID(123L)
                        .setBalance(250.4f)
                        .setIBAN("RO12345")
                        .setType("savings")
                        .build()
        ));
    }

    @Test
    public void removeAll() throws Exception {

    }

}