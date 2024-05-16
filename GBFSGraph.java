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

    public Solution traverseSolution(){
        long startTime = System.currentTimeMillis();

        Node node = new Node(getStartWord(), null, cost(getStartWord()),null);
        markVisited(getStartWord());

        Boolean stop = false,found=false;
        while(!stop){
            ArrayList<String> children = node.expandChild();
            Integer min = 9999;
            String word="";
            for(String child:children){
                if(!checkVisited(child)){
                    markVisited(child);

                    if(cost(child)<=min){
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
                node = new Node(word, node, min,null);
            }
        }
        long endTime = System.currentTimeMillis();
        double duration = endTime-startTime;

        if(found){
            return new Solution(node, duration, getNodesVisited());
        }
        return new Solution(null, duration, getNodesVisited());
    }

}
