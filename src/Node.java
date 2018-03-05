import java.util.*;

public class Node {
    ArrayList<Edge> edges;
    double cost = Double.MAX_VALUE;
    String URL;
    boolean end = false;
    public Node(String URL){
        edges = new ArrayList<>();
        this.URL = URL;
    }
    @Override
    public int hashCode(){
        return URL.hashCode();
    }
}
