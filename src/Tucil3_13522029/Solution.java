
import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<String> pathSol;
    private double duration;
    private Integer nodesVisited;
    
    public Solution(Node destNode,double duration,Integer nodes){
        pathSol = new ArrayList<>();
        Node curNode = destNode;
        while(curNode!=null){
            pathSol.add(curNode.getWord());
            curNode = curNode.getParent();
        }
        this.duration = duration;
        this.nodesVisited = nodes;
    }
    
    public List<String> getPathSol(){
        return pathSol;
    }
    
    public double getDuration(){
        return duration;
    }
    
    public Integer getNodesVisited(){
        return nodesVisited;
    }
}

