import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BFSWebCrawler {
    private Queue<String> queue;
    private List<String> discoveredWebsites;

    public BFSWebCrawler() {
        queue = new LinkedList<>();
        discoveredWebsites = new ArrayList<>();
    }

    //root is the starting URL(www.bbc.com) for the algorithm
    public void discover(String root){
        this.queue.add(root);
        this.discoveredWebsites.add(root);

        while (!queue.isEmpty()){
            String v = queue.remove();
            String rawHtml = readUrl(v);

            String regex = "https://(\\w+\\.)*(\\w+)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(rawHtml);

            while(matcher.find()){
                String w = matcher.group();
                if(!discoveredWebsites.contains(w)){
                    discoveredWebsites.add(w);
                    System.out.println(w);
                    queue.add(w);
                }
            }
        }
    }

    //this is how we read a
    private String readUrl(String url){
        StringBuilder sb = new StringBuilder("");

        try {
            URL htmlUrl = new URL(url);
            BufferedReader reader = new BufferedReader(new InputStreamReader(htmlUrl.openStream()));

            while (reader.readLine() != null){
                sb.append(reader.readLine());
            }
            reader.close();d
        }

        return sb.toString();
    }
}

class BFS{
    public static void main(String[] args){
        BFSWebCrawler bfs = new BFSWebCrawler();

        bfs.discover("https://www.bbc.com/");
    }
}
