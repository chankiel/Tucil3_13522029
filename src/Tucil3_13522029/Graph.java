
import java.util.HashMap;
import java.util.PriorityQueue;

abstract public class Graph {
    private String startingWord;
    private String destWord;
    private HashMap<String,Boolean> visited;
    protected PriorityQueue<Node> nodeQueue;

    public Graph(String startingWord,String destWord){
        this.startingWord = startingWord;
        this.destWord = destWord;
        visited = new HashMap<>();
        nodeQueue = new PriorityQueue<>(new NodeComparator());
    }

    public String getStartWord(){
        return startingWord;
    }

    public String getDestWord(){
        return destWord;
    }

    public Boolean checkVisited(String word){
        return visited.containsKey(word);
    }

    public void markVisited(String word){
        visited.put(word, true);
    }
    
    abstract public Solution traverseSolution();
}
