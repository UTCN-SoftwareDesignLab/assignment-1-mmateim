package repository.account;

/**
 * Created by Alex on 07/03/2017.
 */
public abstract class AccountRepositoryDecorator implements AccountRepository {

    protected AccountRepository decoratedRepository;

    public AccountRepositoryDecorator(AccountRepository bookRepository) {
        this.decoratedRepository = bookRepository;
    }

}
