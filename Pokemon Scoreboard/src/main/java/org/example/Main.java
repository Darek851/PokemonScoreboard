package org.example;

public class Main {

    private static final String URL = "https://www.smogon.com/forums/threads/the-world-cup-of-pokémon-2023-replays.3720595/";
    private static final String FILENAME = "files/data.csv";
    public static void main(String[] args) {
        /*DataWriter writer = new DataWriter("files/test.csv");
        ReplayReader reader = new ReplayReader("https://replay.pokemonshowdown.com/smogtours-gen9ou-700702");
        writer.save(reader); */

        UrlScraper scraper = new UrlScraper(URL);
        DataWriter writer = new DataWriter("files/data.csv");

        String[] replays = scraper.getLinks();
        for (int i = 0; i < replays.length; i++) {
            ReplayReader reader = new ReplayReader(replays[i]);
            if (reader.getTime() > 1686355200) { // From June 11 2023 when Urshifu and Volcarona were banned
                writer.save(reader);
                System.out.println("Saved replay " + (i + 1) + " out of " + replays.length);
            } else {
                System.out.println("Replay " + (i + 1) + " out of " + replays.length + " not in time frame");
            }
        }
    }
}