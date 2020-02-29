package logic;

import model.Bank;
import model.Gender;
import model.Loan;
import model.User;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static logic.FillList.generateUsers;

public class Functionality {


    public static void sortByAge() {
        Map<Integer, List<User>> byAge = generateUsers()
                .stream()
                .collect(Collectors.groupingBy(User::getAge));
        byAge.entrySet().forEach(e -> System.out.println(e));
    }

    public static void notPaidCredit() {
        List<User> notPaidCr = generateUsers().stream()
                .filter(User::isAdult)
                .filter(User::hasArrears)
                .collect(Collectors.toList());
        notPaidCr.forEach(System.out::println);
    }

    public static void paidCredit() {
        List<User> list = generateUsers();
        List<User> paidCr = list.stream()
                .filter(age -> !age.isAdult())
                .filter(paidCre -> !paidCre.hasArrears())
                .filter(User::isMale)
                .collect(Collectors.toList());
        paidCr.stream().forEach(System.out::println);
    }

    public static void notPaidMap() {
//       TODO
        List<User> list = generateUsers();
        Map<Bank, List<User>> notPaidCreditMap = list
                .stream()
                .map(User::getLoans)
                .flatMap(List::stream)
                .filter(e -> !e.isPaid())
                .collect(groupingBy(Loan::getBank, mapping(l -> list.stream()
                                .filter(o -> o.getLoans().contains(l)).findFirst().orElse(null),
                        toList())));

        notPaidCreditMap.entrySet().stream().forEach(System.out::println);
    }

    public static void distinctWoman() {
        List<User> list = generateUsers();
        Set<String> distinctWomen = list.stream()
                .filter(user -> user.getGender().equals(Gender.FEMALE))
                .map(user -> Pair.of(user.getName(), user.getLoans()))
                .filter(pair -> pair.getRight().stream()
                        .filter(Loan::isPaid)
                        .anyMatch(loan -> loan.getBank().getName().equals("OTP_BANK_NAME")))
                .map(Pair::getLeft)
                .collect(Collectors.toSet());
        distinctWomen.forEach(System.out::println);
    }

    public static void avgLoanMap() {
        List<User> list = generateUsers();
        Map<String, Double> avgLoan = list.stream()
                .collect(toMap(User::getName, user -> user.getLoans().stream()
                        .mapToDouble(Loan::getArrears)
                        .average().orElse(Double.NaN))
                );
        avgLoan.entrySet().forEach(System.out::println);
    }

    public static void topHighestCredit() {
        List<User> list = generateUsers();

        Map<String, List<Integer>> top = list.stream()
                .collect(toMap(User::getName, user -> user.getLoans().stream()
                                .sorted((a, b) -> b.getAmount() - a.getAmount())
                                .map(Loan::getAmount)
                                .limit(3)
                                .collect(Collectors.toList())
                        )
                );
        top.entrySet().forEach(System.out::println);
    }

    public static void topHighCrHighArr() {
        List<User> list = generateUsers();

        Map<String, List<Integer>> top2 = list.stream()
                .collect(toMap(User::getName, user -> user.getLoans().stream()
                                .filter(e -> !e.isPaid())
                                .sorted((a, b) -> b.getArrears() - a.getArrears())
                                .map(Loan::getAmount)
                                .limit(3)
                                .collect(Collectors.toList())
                        )
                );
        list.forEach(System.out::println);
        top2.entrySet().forEach(System.out::println);
    }

    public static void div() {
        List<User> list = generateUsers();

        Map<Integer, Map<Gender, List<Double>>> divMap = list.stream()
                .collect(groupingBy(User::getAge, groupingBy(User::getGender, mapping((User user) -> user.getLoans().stream()
                        .mapToDouble(Loan::getArrears)
                        .average().orElse(Double.NaN), Collectors.toList()))
                ));

        list.forEach(System.out::println);
        divMap.entrySet().forEach(System.out::println);

    }

}
