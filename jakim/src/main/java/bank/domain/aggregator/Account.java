package bank.domain.aggregator;

public class Account {
    private float balance;

    public float add(float amount) {
        return balance += amount;
    }

    public float balance() {
        return balance;
    }

    public float minus(float amount) {
        return balance -= amount;
    }
}
