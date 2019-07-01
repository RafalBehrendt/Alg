package RadixTrie;

import java.util.HashMap;

public class Node {
    HashMap<String, Node> Children = new HashMap<String, Node>();
    boolean isWord;

    Node(boolean isWord){
        this.isWord = isWord;
    }



}
