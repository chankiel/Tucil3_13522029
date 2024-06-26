
import java.util.ArrayList;
import java.util.PriorityQueue;

public class UCSGraph extends Graph{

    public UCSGraph(String startingWord,String destWord){
        super(startingWord,destWord);
    }

    @Override
    public Solution traverseSolution(){
        long startTime = System.nanoTime();

        nodeQueue.add(new Node(getStartWord(), null, 0,null));

        int nodesVisited = 0;
        Node curNode = null;

        while (!nodeQueue.isEmpty()) {
            curNode = nodeQueue.poll();
            nodesVisited++;

            if(checkVisited(curNode.getWord())) {
                continue;
            }

            markVisited(curNode.getWord());
            if(curNode.getWord().equals(getDestWord())){
                break;
            }

            ArrayList<String> childs = curNode.expandChild();
            for(String child:childs){
                if(!checkVisited(child)){
                    nodeQueue.add(new Node(child, curNode, curNode.getCost()+1,null));
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
