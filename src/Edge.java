public class Edge {
    double weight;
    Node src, dest;

    public Edge(double weight, Node src, Node dest){
        this.weight = weight;
        this.src = src;
        this.dest = dest;
    }

    @Override
    public int hashCode(){
        return src.hashCode() + dest.hashCode();
    }
}
