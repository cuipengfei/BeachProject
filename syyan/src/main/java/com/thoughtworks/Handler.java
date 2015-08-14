package main.java.com.thoughtworks;


import main.java.com.thoughtworks.exception.OverdrawException;

public interface Handler {
    double handle(CustomerRequest customerRequest) throws OverdrawException;
}
