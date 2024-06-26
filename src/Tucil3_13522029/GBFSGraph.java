
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

        nodeQueue.add(new Node(getStartWord(), null, 0,null));
        Node curNode = null;
        int nodesVisited = 0;

        while (!nodeQueue.isEmpty()) {
            curNode = nodeQueue.poll();
            nodesVisited++;
            if(checkVisited(curNode.getWord())){
                continue;
            }

            markVisited(curNode.getWord());
            if(curNode.getWord().equals(getDestWord())){
                break;
            }

            ArrayList<String> childs = curNode.expandChild();
            for(String child:childs){
                if(!checkVisited(child)){
                    nodeQueue.add(new Node(child, curNode, cost(child),null));
                }
            }
        }

        long endTime = System.nanoTime();
        double duration = (endTime-startTime)/1_000_000.0;
        if(curNode.getWord().equals(getDestWord())){
            return new Solution(curNode, duration, nodesVisited);
        }
        return new Solution(null, duration, nodesVisited);
    }
  
}
