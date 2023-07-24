package org.example;
import java.io.IOException;
import java.util.LinkedList;
import java.util.TreeSet;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class UrlScraper {
    private Document doc;
    private Elements elements;

    public UrlScraper(String url) {
        try {
            doc = Jsoup.connect(url).get();
            elements = doc.select("a");
        } catch (IOException e) {
            System.out.println("That's cringe");
            return;
        }
    }

    public String[] getLinks() {
        TreeSet<String> links = new TreeSet<>();
        for (Element element : elements) {
            if (element.absUrl("href").contains("replay.pokemonshowdown.com")) {
                links.add(element.absUrl("href"));
            }
        }
        String[] linkArray = new String[links.size()];
        return links.toArray(linkArray);
    }
}
