package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class User {

    private int id;
    private int age;
    private String name;
    private Gender gender;
    private int salary;
    private List<Loan> loans;

    public boolean isAdult() {
        return age >= 18;
    }

    public boolean hasArrears() {
        return loans.stream().anyMatch(loan -> !loan.isPaid());
    }

    public boolean hasLoan(Loan loan) {
        return loans.contains(loan);
    }

    public boolean isMale() {
        return gender.equals(Gender.MALE);
    }
}
