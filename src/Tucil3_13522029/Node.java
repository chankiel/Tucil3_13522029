
import java.util.ArrayList;

public class Node {
    private String word;
    private Node parent;
    private Integer cost;
    // distFromStart only for A Star
    private Integer distFromStart;

    public Node(String word,Node parent,Integer cost,Integer distFromStart){
        this.word = word;
        this.parent = parent;
        this.cost = cost;
        this.distFromStart = distFromStart;
    }

    public Integer getCost(){
        return cost;
    }

    public Node getParent(){
        return parent;
    }

    public String getWord(){
        return word;
    }

    public Integer getDistFromStart(){
        return distFromStart;
    }

    public ArrayList<String> expandChild(){
        ArrayList<String> childs = new ArrayList<>();
        int lengthWord = word.length();
        for(int i=0;i<lengthWord;i++){
            StringBuilder str = new StringBuilder(word);
            char charTarget = str.charAt(i);
            for(char c='a';c<='z';c++){
                if(charTarget==c){
                    continue;
                }
                str.setCharAt(i, c);
                if(Dict.checkWord(str.toString())){
                    childs.add(str.toString());
                }
            }
        }
        return childs;
    }
}