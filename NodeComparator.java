import java.util.Comparator;

class NodeComparator implements Comparator<Node>{
    @Override
    public int compare(Node node1, Node node2){
        return Integer.compare(node1.getCost(), node2.getCost());
    }
}
