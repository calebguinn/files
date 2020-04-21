import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Episode implements Serializable{
    private String title;
    private Date date;
    private int number;
    private String season;
    private int numberInSeason;
    private List<Double> markers;


    /*
    public Episode(){
        this("N/A", new Date(), new ArrayList<Double>());
    }
    */

    public Episode(String title, Date data, int number, String season, List<Double> markers) {
        this.title = title;
        this.date = data;
        this.markers = markers;
        this.number = number;
        this.season = season;
        Map<String, Integer>  map = getSeason(season);
        this.numberInSeason = map.get("number");
    }

    public Map<String, Integer> getSeason(String str){
        Map<String, Integer> vk = new HashMap<>();
        String[] values = str.split("-");
        // System.preferences
        Integer s = Integer.parseInt(values[0]);
        vk.put("season", s);
        vk.put("number", Integer.parseInt(values[1]));
        return vk;
    }

    public String getTitle() {
        return this.title;
    }

    public Date getDate() {
        return this.date;
    }

    public int getNumber() {
        return this.number;
    }

    public int getNumberInSeason() {
        return this.numberInSeason;
    }

    public List<Double> getMarkers() {
        return this.markers;
    }

    @Override
    public String toString() {
        return "{" +
            " title='" + title + "'" +
            ", date='" + date + "'" +
            ", number='" + number + "'" +
            ", season='" + season + "'" +
            ", numberInSeason='" + numberInSeason + "'" +
            ", markers='" + markers + "'" +
            "}";
    }
}
