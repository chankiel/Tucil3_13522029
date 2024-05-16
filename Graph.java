import java.util.HashMap;

abstract public class Graph {
    private String startingWord;
    private String destWord;
    private HashMap<String,Boolean> visited;

    public Graph(String startingWord,String destWord){
        this.startingWord = startingWord;
        this.destWord = destWord;
        visited = new HashMap<>();
    }

    public String getStartWord(){
        return startingWord;
    }

    public String getDestWord(){
        return destWord;
    }

    public Boolean checkVisited(String word){
        return visited.get(word)!=null;
    }

    public void markVisited(String word){
        visited.put(word, true);
    }

    public Integer getNodesVisited(){
        return visited.size();
    }

    public void clearVisited(){
        visited.clear();
    }

    abstract public Solution traverseSolution();
}
