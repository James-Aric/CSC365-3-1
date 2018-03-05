import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author James
 */
public class WebScraper {
    //Jsoup document array
    Document[] docs;

    //method to find the num of lines in a file in order to creat docs array
    void fileLineNum(){
        try{
            //BufferedReader reader = new BufferedReader(new FileReader("sites.txt"));
            BufferedReader reader = new BufferedReader(new FileReader("sites.txt"));
            int lines = 0;
            while (reader.readLine() != null) lines++;
            reader.close();
            docs = new Document[lines];
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }
    //load documents from sites.txt file located in project directory.

    //read text from Documents, remove special characters in order to split properly for hashing
    String parseDoc(Document d){
        //create an array of size docs.length to hold the new cleaned up string
        String text = "";
        //add string to array, and clean up special chars
        String temp = d.text();
        //System.out.println(temp);
        temp = temp.replaceAll("[^A-Za-z0-9]", " ");
        temp = temp.replaceAll("  ", " ");
        text = temp;

        //return array
        return text;
    }

    String parseCompare(String url) throws IOException{
        String temp;
        Document tempDoc = Jsoup.connect(url).get();
        temp = tempDoc.text();
        temp = temp.replaceAll("[^A-Za-z0-9]", " ");
        temp = temp.replaceAll("  ", " ");
        return temp;
    }

    String getModifyDate(Document d){
        Elements element = d.select("li");
        Elements children = new Elements();

        Element test = d.select("li#footer-info-lastmod").first();

        if(test != null) {
            return test.text().substring(test.text().indexOf("on")+3);
        }
        return "failed";
    }

    public ArrayList<String> getLinks(String start) throws IOException{
        ArrayList<String> links = new ArrayList<>();
        Document startDoc = Jsoup.connect(start).get();
        Elements linkList = startDoc.select("a[href]");
        for(Element e: linkList){
            if(!links.contains(e.attr("abs:href"))) {
                if (e.attr("abs:href").contains("#") || e.attr("abs:href").substring(e.attr("abs:href").length()-5).contains(".") || e.attr("abs:href").contains("index.php")){
                }
                else if(e.attr("abs:href").substring(0, 10).equals("https://en")){
                    //System.out.println(e.attr("abs:href").toString());
                    links.add(e.attr("abs:href"));
                }
            }
        }
        return links;
    }

}
