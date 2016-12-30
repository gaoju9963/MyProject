package pachong;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 * Created by pengshu on 2016/11/10.
 */
@Service
public class Main {


    static ForeignExchange foreignExchange = new ForeignExchange();


    static SimpleDateFormat  formatDate = new SimpleDateFormat("yyyy-MM-dd");
    static SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");

    public static void main(String[] args) throws IOException, ParseException {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("application.xml");
        MongoTemplate mongoTemplate = (MongoTemplate) ctx.getBean("mongoTemplate");

        URL url = new URL("http://www.boc.cn/sourcedb/whpj/index.html");
        URLConnection conn = url.openConnection();
        conn.setReadTimeout(10000);
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        String line = "";
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
//        Pattern pattern = Pattern.compile("(?<=\\<td\\>)[^<,^>](?=\\</td\\>)");
//        Matcher matcher = pattern.matcher(sb.toString());
//        while (matcher.find()){
//            System.out.println(matcher.group(0));
//        }
        Document doc = Jsoup.parse(sb.toString());
        Elements elements = doc.getElementsByTag("tbody");

        for (Element element : elements) {
            if (element == elements.get(1)) {
//                System.out.println(124);
//                System.out.println(element.getElementsByTag("th"));

                Elements tr = element.getElementsByTag("tr");
                for (Element th : tr) {

                    Elements eachTd = th.getElementsByTag("td");
                    if (eachTd.size() != 0) {
                        foreignExchange.setCurrency(eachTd.get(0).text());
                        foreignExchange.setSpotPurchase(!eachTd.get(1).text().equals("") ? Double.parseDouble(eachTd.get(1).text()) : null);
                        foreignExchange.setCasePurchase(!eachTd.get(2).text().equals("") ? Double.parseDouble(eachTd.get(2).text()) : null);
                        foreignExchange.setSpotSell(!eachTd.get(3).text().equals("") ? Double.parseDouble(eachTd.get(3).text()) : null);
                        foreignExchange.setCaseSell(!eachTd.get(4).text().equals("") ? Double.parseDouble(eachTd.get(4).text()) : null);
                        foreignExchange.setDiscountPrice(!eachTd.get(5).text().equals("") ? Double.parseDouble(eachTd.get(5).text()) : null);
                        foreignExchange.setReleaseDate(formatDate.parse(eachTd.get(6).text()));
                        foreignExchange.setReleaseTime(formatTime.parse(eachTd.get(7).text()));
                        mongoTemplate.insert(foreignExchange);
                    }

                }

            }
        }

    }
}
