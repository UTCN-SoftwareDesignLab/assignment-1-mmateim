package repository.account;

import model.Account;
import model.builder.AccountBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.EntityNotFoundException;

import static org.junit.Assert.assertTrue;

/**
 * Created by Alex on 08/03/2017.
 */
public class AccountRepositoryMockTest {

    private static AccountRepository repository;

    @BeforeClass
    public static void setupClass() {
       /* repository = new AccountRepositoryCacheDecorator(
                new AccountRepositoryMock(),
                new Cache<>()
        );*/
    }

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void findAll() throws Exception {
        assertTrue(repository.findAll().size() == 0);
    }

    @Test(expected = EntityNotFoundException.class)
    public void findByIdEx() throws Exception {
        repository.findById(1L);
    }

    @Test
    public void save() throws Exception {
        Account account = new AccountBuilder()
                .setHolderID(123L)
                .setBalance(250.4f)
                .setIBAN("RO12345")
                .setType("savings")
                .build();

        assertTrue(repository.save(account));
    }

}