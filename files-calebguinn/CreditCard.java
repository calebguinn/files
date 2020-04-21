import java.util.Random;

public class CreditCard {
    private String number;

    public CreditCard() {
        this.number = createCreditCardNumber();
    }

    public String getNumber() {
        return this.number;
    }

    public static String createCreditCardNumber() {
        Random random = new Random();
        int p1 = random.nextInt(10000);
        int p2 = random.nextInt(10000);
        int p3 = random.nextInt(10000);
        int p4 = random.nextInt(10000);
        String number = Integer.toString(p1) 
                      + " "
                      + Integer.toString(p2)
                      + " "
                      + Integer.toString(p3)
                      + " "
                      + Integer.toString(p4);
        return number;
    }

    @Override
    public String toString() {
        return "{" +
            " number='" + getNumber() + "'" +
            "}";
    }

}