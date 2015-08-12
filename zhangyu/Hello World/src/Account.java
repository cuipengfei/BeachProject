/**
 * Created by yuzhang on 8/12/15.
 */
public class Account {
    private int balance;
    private Customer customer;

    public Account(Customer customer) {
        this.customer = customer;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void deposit(Account account,int num){
        account.balance += num;
        System.out.println("[successful deposit]Your balance:" + account.getBalance());
    }

    public int withdraw(Account account,int num) throws Exception {
        if (num > account.balance) {
            System.out.println("[Failed withdraw]Your balance:"+account.balance);
            throw new Exception("overdraw");
        }
        account.balance -= num;
        System.out.println("[successful withdraw]Your balance:" + account.balance);
        return account.balance;
    }

/*    public void createAccount(Customer customer1){
        if(customer1.needAccount){
            Account account = new Account(customer1);
            customer1.needAccount = false;
            //  Account account1 = new Account(customer1);
            account.deposit(account,300);

            try {
                account.withdraw(account,301);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/
}
