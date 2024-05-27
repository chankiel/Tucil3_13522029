
import java.util.ArrayList;
import java.util.PriorityQueue;

public class UCSGraph extends Graph{
    private PriorityQueue<Node> nodeQueue;

    public UCSGraph(String startingWord,String destWord){
        super(startingWord,destWord);
        nodeQueue = new PriorityQueue<>(new NodeComparator());        
    }

    @Override
    public Solution traverseSolution(){
        long startTime = System.currentTimeMillis();
        
        int nodesVisited = 0;
        Node startNode = new Node(getStartWord(), null, 0,null);
        Node curNode = null;
        
        nodeQueue.add(startNode);
        while (!nodeQueue.isEmpty()) {
            curNode = nodeQueue.poll();
            nodesVisited++;
            
            if(curNode.getWord().equals(getDestWord())){
                break;
            }
            
            ArrayList<String> childs = curNode.expandChild();
            for(String child:childs){
                if(!checkVisited(child)){
                    nodeQueue.add(new Node(child, curNode, curNode.getCost()+1,null));
                    markVisited(child);
                }
            }
        }
        long endTime = System.currentTimeMillis();
        double duration = (endTime-startTime);

        if(curNode.getWord().equals(getDestWord())){
            return new Solution(curNode, duration, nodesVisited);
        }
        return new Solution(null, duration, nodesVisited);
    }
}
