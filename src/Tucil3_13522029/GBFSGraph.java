
import java.util.ArrayList;

public class GBFSGraph extends Graph{

    public GBFSGraph(String startingWord,String destWord){
        super(startingWord,destWord);      
    }

    public Integer cost(String word){
        Integer sum = 0;
        for(int i=0;i<word.length();i++){
            if(word.charAt(i)!=getDestWord().charAt(i)){
                sum++;
            }
        }
        return sum;
    }
    
    @Override
    public Solution traverseSolution(){
        long startTime = System.nanoTime();

        Node node = new Node(getStartWord(), null, cost(getStartWord()),null);
        markVisited(getStartWord());

        int nodesVisited = 0;
        while(true){
            nodesVisited++;
            markVisited(node.getWord());
            if(node.getWord().equals(getDestWord())){
                break;
            }

            ArrayList<String> children = node.expandChild();
            Integer min = 9999;
            String word="";
            for(String child:children){
                if(!checkVisited(child) && cost(child)<min){
                    min = cost(child);
                    word = child;
                }
            }
            if(min==9999) {
                break;
            }
            node = new Node(word, node, min,null);
        }
        long endTime = System.nanoTime();
        double duration = (endTime-startTime)/1_000_000.0;

        if(node.getWord().equals(getDestWord())){
            return new Solution(node, duration, nodesVisited);
        }
        return new Solution(null, duration, nodesVisited);
    }
  
}
