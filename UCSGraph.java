import java.util.ArrayList;
import java.util.PriorityQueue;

public class UCSGraph extends Graph{
    private PriorityQueue<Node> nodeQueue;

    public UCSGraph(String startingWord,String destWord){
        super(startingWord,destWord);
        nodeQueue = new PriorityQueue<>(new NodeComparator());        
    }

    public Solution traverseSolution(){
        long startTime = System.currentTimeMillis();

        Node startNode = new Node(getStartWord(), null, 0,null);
        nodeQueue.add(startNode);
        Node curNode = null;
        while (!nodeQueue.isEmpty()) {
            curNode = nodeQueue.poll();

            if(curNode.getWord().equals(getDestWord())){
                break;
            }

            String word = curNode.getWord();
            for(int i=0;i<word.length();i++){

                ArrayList<String> childs = curNode.expandChild();
                for(String child:childs){
                    if(!checkVisited(child)){
                        nodeQueue.add(new Node(child, curNode, curNode.getCost()+1,null));
                        markVisited(child);
                    }
                }
            }
        }
        long endTime = System.currentTimeMillis();
        double duration = (endTime-startTime);

        if(curNode.getWord().equals(getDestWord())){
            return new Solution(curNode, duration, getNodesVisited());
        }
        return new Solution(null, duration, getNodesVisited());
    }
}
