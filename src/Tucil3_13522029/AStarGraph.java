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
        long startTime = System.currentTimeMillis();
        
        Node startNode = new Node(getStartWord(), null, cost(getStartWord()),0);
        nodeQueue.add(startNode);
        Node curNode = null;
        int nodesVisited = 0;
        
        while (!nodeQueue.isEmpty()) {
            curNode = nodeQueue.poll();
            nodesVisited++;
            if(curNode.getWord().equals(getDestWord())){
                break;
            }

            String word = curNode.getWord();
            for(int i=0;i<word.length();i++){

                ArrayList<String> childs = curNode.expandChild();
                for(String child:childs){
                    if(!checkVisited(child)){
                        Integer childCost = curNode.getDistFromStart() + 1 + cost(child);
                        nodeQueue.add(new Node(child, curNode, childCost,curNode.getDistFromStart()+1));
                        markVisited(child);
                    }
                }
            }
        }
        
        long endTime = System.currentTimeMillis();
        double duration = endTime-startTime;
        if(curNode.getWord().equals(getDestWord())){
            return new Solution(curNode, duration, nodesVisited);
        }
        return new Solution(null, duration, nodesVisited);
    }
}
