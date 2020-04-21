import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PaidEpisode extends Episode {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private transient CreditCard card;


    public PaidEpisode( String title, Date data, 
        int number, String season, List<Double> markers, CreditCard card) {
        super(title, data, number, season, markers);
        this.card = card;
    }

    public CreditCard getCard() {
        return this.card;
    }

    @Override
    public String toString() {
        String superStr = super.toString();
        String result =  superStr.replace("}", ",");

        return result +
            ", credit Card='" + getCard() + "'" +
            "}";
    }
}