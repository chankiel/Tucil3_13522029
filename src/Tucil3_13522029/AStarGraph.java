import java.util.ArrayList;
import java.util.PriorityQueue;

public class AStarGraph extends Graph{
    private PriorityQueue<Node> nodeQueue;

    public AStarGraph(String startingWord,String destWord){
        super(startingWord,destWord);  
        nodeQueue = new PriorityQueue<>(new NodeComparator());  
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

        nodeQueue.add(new Node(getStartWord(), null, 0,0));
        Node curNode = null;
        int nodesVisited = 0;
        boolean found = false;
        
        while (!nodeQueue.isEmpty()) {
            curNode = nodeQueue.poll();
            nodesVisited++;
            if(checkVisited(curNode.getWord())){
                continue;
            }

            markVisited(curNode.getWord());
            if(curNode.getWord().equals(getDestWord())){
                found = true;
                break;
            }

            ArrayList<String> childs = curNode.expandChild();
            for(String child:childs){
                if(!checkVisited(child)){
                    Integer childCost = curNode.getDistFromStart() + 1 + cost(child);
                    nodeQueue.add(new Node(child, curNode, childCost,curNode.getDistFromStart()+1));
                }
            }
        }
        
        long endTime = System.nanoTime();
        double duration = (endTime-startTime)/1_000_000.0;
        if(found){
            return new Solution(curNode, duration, nodesVisited);
        }
        return new Solution(null, duration, nodesVisited);
    }
}
