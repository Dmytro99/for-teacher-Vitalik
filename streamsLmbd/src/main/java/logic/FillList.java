package logic;

import lombok.Data;
import model.Bank;
import model.Gender;
import model.Loan;
import model.User;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextInt;

@Data
public class FillList {
    private static final Integer NUMBER_OF_USERS = 45;
    private static final Integer NUMBER_OF_BANKS = 10;
    private static final Integer MIN_LOANS = 1;
    private static final Integer MAX_LOANS = 100;

    private static List<User> users;

    public static List<User> generateUsers() {
        List<Bank> banks = Stream.generate(FillList::getRandomBank)
                .limit(NUMBER_OF_BANKS - (long) 1)
                .collect(Collectors.toList());
        banks.add(new Bank(1, "OTP_BANK_NAME"));

        users = Stream.generate(() -> getRandomUser(getRandomLoans(nextInt(MIN_LOANS, MAX_LOANS), banks)))
                .limit(NUMBER_OF_USERS)
                .collect(Collectors.toList());
        return users;
    }

    private static Bank getRandomBank() {
        return new Bank(nextInt(2, 1000), randomAlphabetic(10));
    }

    private static User getRandomUser(List<Loan> loans) {

        return User.builder()
                .id(nextInt(1, 100000))
                .age(nextInt(14, 100))
                .name(randomAlphabetic(10))
                .gender(getOneOfTwo(Gender.MALE, Gender.FEMALE))
                .salary(nextInt(5000, 100000))
                .loans(loans)
                .build();
    }

    private static List<Loan> getRandomLoans(int limit, List<Bank> banks) {
        return Stream.generate(() -> createRandomLoan(banks))
                .limit(limit)
                .collect(Collectors.toList());
    }

    private static Loan createRandomLoan(List<Bank> banks) {
        int amount = nextInt(1000, 100000);
        int balance = getOneOfTwo(nextInt(0, amount), amount);
        LocalDate startDate = LocalDate.now().minus(Period.ofDays(nextInt(0, 365 * 10)));
        LocalDate endDate = LocalDate.ofEpochDay(nextInt((int) startDate.toEpochDay(),
                (int) LocalDate.now().plusYears(nextInt(0, 10)).toEpochDay()));
        Bank bank = banks.get(nextInt(0, NUMBER_OF_BANKS));

        return Loan.builder()
                .id(nextInt(1, 10000))
                .amount(amount)
                .balance(balance)
                .start(startDate)
                .due(endDate)
                .bank(bank)
                .build();
    }

    private static <T> T getOneOfTwo(T first, T second) {
        return (Math.random() <= 0.5) ? first : second;
    }
}
