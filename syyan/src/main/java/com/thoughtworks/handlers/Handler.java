package main.java.com.thoughtworks.handlers;


import main.java.com.thoughtworks.Bank;
import main.java.com.thoughtworks.exception.OverdrawException;

public interface Handler {
    double handle(Bank.CustomerRequest customerRequest) throws OverdrawException;
}
