
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
        long startTime = System.currentTimeMillis();

        Node node = new Node(getStartWord(), null, cost(getStartWord()),null);
        markVisited(getStartWord());

        Boolean stop = false,found=false;
        int nodesVisited = 0;
        while(!stop){
            nodesVisited++;
            ArrayList<String> children = node.expandChild();
            Integer min = 9999;
            String word="";
            for(String child:children){
                if(!checkVisited(child)){
                    if(cost(child)<min){
                        min = cost(child);
                        word = child;
                    }
                }
            }
            if(min==9999){
                stop = true;
            }else{
                if(word.equals(getDestWord())){
                    stop = true;
                    found = true;
                }
                markVisited(word);
                node = new Node(word, node, min,null);
            }
        }
        long endTime = System.currentTimeMillis();
        double duration = endTime-startTime;

        if(found){
            return new Solution(node, duration, nodesVisited);
        }
        return new Solution(null, duration, nodesVisited);
    }
  
}
