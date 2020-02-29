package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class Loan {

    private int id;
    private int amount;
    private int balance;
    private LocalDate start;
    private LocalDate due;
    private Bank bank;

    public boolean isPaid() {
        return balance == amount;
    }

    public int getArrears() {
        return amount - balance;
    }

    public boolean isExpired() {
        return due.isBefore(LocalDate.now());
    }

}