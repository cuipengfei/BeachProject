package main.java.com.thoughtworks;


import java.text.SimpleDateFormat;

public class Main {

    public static void main(String[] args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Customer customer = new Customer("yan123", sdf.parse("2015-08-09"));
        Bank bank = new Bank();
        Customer customer2 = new Customer("YAN", sdf.parse("2015-08-10"));
        Customer existCustomer = new Customer("yan123", sdf.parse("2015-08-10"));

        System.out.println(customer.getNickName() + "   " + bank.addCustomer(customer));
        System.out.println(customer2.getNickName() + "   " + bank.addCustomer(customer2));
        System.out.println(existCustomer.getNickName() + "   " + bank.addCustomer(existCustomer));


    }
}
