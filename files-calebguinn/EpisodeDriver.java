import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import java.lang.instrument.Instrumentation;

public class EpisodeDriver {
    private final static Random random = new Random();

    public static List<Double> generateStarValues() {
        final List<Double> list = new ArrayList<>();
        list.add(random.nextDouble());
        list.add(random.nextDouble());
        return list;
    }

    // quite universal, and could be modified for different format strings
    public static Date createDate(final String day, final String month, final String year) {
        final SimpleDateFormat df = new SimpleDateFormat("dd MMM yy");
        final String dateStr = day + " " + month + " " + year;

        Date date = null;
        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    // this relates to the file that I am parsing
    public static Date createDate(String[] columns) {
        String day = columns[2].trim();
        String month = columns[3].trim();
        String year = columns[4].trim();
        return createDate(day, month, year);
    }

    public static String createTitle(String[] columns) {
        // the rest is title
        final int START_OF_TILE_WORDS = 5;
        final StringBuffer buffer = new StringBuffer("");
        for (int index = START_OF_TILE_WORDS; index < columns.length; ++index) {
            buffer.append(columns[index]).append(" ");
        }
        return buffer.toString().trim();
    }

    public static List<Episode> getEpisode() {
        // you have to fix your path in relation to this file
        final String fileName = "./LastShip.txt";
        final File file = new File(fileName);
        final List<Episode> episodes = new ArrayList<>();
        try (Scanner input = new Scanner(file);) {
            while (input.hasNextLine()) {
                final String line = input.nextLine().trim();
                final String[] columns = line.split("\s+");

                final double number = (Double.parseDouble(columns[0].trim()));
                final String season = columns[1].trim();

                String title = createTitle(columns);
                final Date date = createDate(columns);
                final List<Double> list = generateStarValues();

                CreditCard card = new CreditCard();
                final PaidEpisode episode = new PaidEpisode(title, date, (int)number, season, list, card);
                episodes.add(episode);
                System.out.println("From creation: " + episode);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return episodes;
    }

    // THIS WOULD LEAD TO A BAD DESIGN, AND WE WOULD NEED A CONSTRUCTOR THAT DOES NOT WORK:
    // new T(...)
    //
    // public static<T extends Episode> List<T> getPaidEpisode() {
    //     final String fileName = "/Users/rafal/Projects/Java/Apple/LastShip.txt";
    //     final File file = new File(fileName);
    //     final List<T> episodes = new ArrayList<>();
    //     try (Scanner input = new Scanner(file);) {
    //         if (input.hasNextLine()) {
    //             final String[] headers = input.nextLine().split(",");
    //         }
    //         while (input.hasNextLine()) {
    //             // parse on white space
    //             final String line = input.nextLine().trim();
    //             final String[] columns = line.split("\s+");
    //             final String number = columns[0].trim();
    //             final String season = columns[1].trim();

    //             final String day = columns[2].trim();
    //             final String month = columns[3].trim();
    //             final String year = columns[4].trim();

    //             final SimpleDateFormat df = new SimpleDateFormat("dd MMM yy");
    //             final String dateStr = day + " " + month + " " + year;
    //             final Date date = df.parse(dateStr);
    //             // System.out.println(date);

    //             // the rest is title
    //             String title = "";
    //             for (int index = 5; index < columns.length; ++index) {
    //                 title += columns[index] + " ";
    //             }
    //             title = title.trim();
    //             final List<Double> list = new ArrayList<>();
    //             list.add(2.3);
    //             list.add(3.3);
    //             final T = new T(title, date, list, new CreditCard());
    //             episodes.add(episode);
    //         }

    //     } catch (final Exception e) {
    //         e.printStackTrace();
    //     }
    //     return episodes;
    // }

    public static void main(final String[] args) {
        final List<Episode> episodes = getEpisode();
        episodes.forEach(System.out::println);

        Episode[] a = new Episode[episodes.size()];
        Episode[] es = episodes.toArray(a);
        System.out.println(es[0]);

        final String file = "episodes.dat";
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));) {
            for (final Episode episode : episodes) {
                os.writeObject(episode);
            }
            os.writeObject(es);
        } catch (final IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));) {
            while (true) {
                final Episode o = (Episode) (input.readObject());
                System.out.println(o);
            }
        } catch (final IOException e) {
            System.out.println("All episodes were read!");
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}