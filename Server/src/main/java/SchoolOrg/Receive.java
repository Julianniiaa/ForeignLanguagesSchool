package SchoolOrg;

import java.io.Serializable;

public class Receive implements Serializable {
    int balance;
    String language;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Receive{" +
                "balance=" + balance +
                ", language='" + language + '\'' +
                '}';
    }
}
