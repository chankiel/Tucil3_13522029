import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<String> pathSol;
    private double duration;
    private Integer nodesVisited;
    private Integer totalSolNodes;

    public Solution(Node destNode,double duration,Integer nodes){
        pathSol = new ArrayList<>();
        Node curNode = destNode;
        totalSolNodes = 0;
        while(curNode!=null){
            totalSolNodes++;
            pathSol.add(curNode.getWord());
            curNode = curNode.getParent();
        }
        this.duration = duration;
        this.nodesVisited = nodes;
    }

    public void showSolution(){
        System.out.println("Waktu: "+duration);
        for(String element : pathSol){
            System.out.println(element);
        }
        System.out.println(nodesVisited);
        System.out.println(totalSolNodes);
    }
}