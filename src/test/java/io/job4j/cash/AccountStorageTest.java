package io.job4j.cash;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountStorageTest {

    @Test
    public void whenAddNewAccountThenSuccess() {
        AccountStorage storage = new AccountStorage();
        Account account = new Account(1, 100);
        boolean result = storage.add(account);

        assertThat(result).isTrue();
        assertThat(storage.getById(1))
                .isPresent()
                .get()
                .extracting(Account::amount)
                .isEqualTo(100);
    }

    @Test
    public void whenAddExistingAccountThenFail() {
        AccountStorage storage = new AccountStorage();
        Account account = new Account(1, 100);
        storage.add(account);
        boolean result = storage.add(new Account(1, 200));

        assertThat(result).isFalse();
        assertThat(storage.getById(1))
                .isPresent()
                .get()
                .extracting(Account::amount)
                .isEqualTo(100);
    }

    @Test
    public void whenUpdateExistingAccountThenSuccess() {
        AccountStorage storage = new AccountStorage();
        storage.add(new Account(1, 100));
        boolean result = storage.update(new Account(1, 200));

        assertThat(result).isTrue();
        assertThat(storage.getById(1))
                .isPresent()
                .get()
                .extracting(Account::amount)
                .isEqualTo(200);
    }

    @Test
    public void whenUpdateNonExistingAccountThenFail() {
        AccountStorage storage = new AccountStorage();
        boolean result = storage.update(new Account(1, 100));

        assertThat(result).isFalse();
        assertThat(storage.getById(1)).isPresent();
    }

    @Test
    public void whenDeleteExistingAccountThenSuccess() {
        AccountStorage storage = new AccountStorage();
        storage.add(new Account(1, 100));
        storage.delete(1);

        assertThat(storage.getById(1)).isNotPresent();
    }

    @Test

    public void whenDeleteNonExistingAccountThenNoEffect() {
        AccountStorage storage = new AccountStorage();
        storage.delete(1);

        assertThat(storage.getById(1)).isNotPresent();
    }

    @Test
    public void whenTransferSuccess() {
        AccountStorage storage = new AccountStorage();
        storage.add(new Account(1, 500));
        storage.add(new Account(2, 300));

        boolean result = storage.transfer(1, 2, 200);

        assertThat(result).isTrue();
        assertThat(storage.getById(1))
                .isPresent()
                .get()
                .extracting(Account::amount)
                .isEqualTo(300);
        assertThat(storage.getById(2))
                .isPresent()
                .get()
                .extracting(Account::amount)
                .isEqualTo(500);
    }

    @Test
    public void whenTransferInsufficientFundsThenFail() {
        AccountStorage storage = new AccountStorage();
        storage.add(new Account(1, 100));
        storage.add(new Account(2, 300));

        boolean result = storage.transfer(1, 2, 200);

        assertThat(result).isFalse();
        assertThat(storage.getById(1))
                .isPresent()
                .get()
                .extracting(Account::amount)
                .isEqualTo(100);
        assertThat(storage.getById(2))
                .isPresent()
                .get()
                .extracting(Account::amount)
                .isEqualTo(300);
    }

    @Test
    public void whenTransferFromNonExistingAccountThenFail() {
        AccountStorage storage = new AccountStorage();
        storage.add(new Account(2, 300));

        boolean result = storage.transfer(1, 2, 100);

        assertThat(result).isFalse();
        assertThat(storage.getById(2))
                .isPresent()
                .get()
                .extracting(Account::amount)
                .isEqualTo(300);
    }

    @Test
    public void whenTransferToNonExistingAccountThenFail() {
        AccountStorage storage = new AccountStorage();
        storage.add(new Account(1, 500));

        boolean result = storage.transfer(1, 2, 200);

        assertThat(result).isFalse();
        assertThat(storage.getById(1))
                .isPresent()
                .get()
                .extracting(Account::amount)
                .isEqualTo(500);
        assertThat(storage.getById(2)).isNotPresent();
    }

    @Test
    public void whenTransferToSelfThenFail() {
        AccountStorage storage = new AccountStorage();
        storage.add(new Account(1, 500));

        boolean result = storage.transfer(1, 1, 100);

        assertThat(result).isFalse();
        assertThat(storage.getById(1))
                .isPresent()
                .get()
                .extracting(Account::amount)
                .isEqualTo(500);
    }
}