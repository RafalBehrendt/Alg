package RadixTrie;

import java.util.Random;

public class RadixTrie {

    Node root = new Node(false);
    int iterations = -1;
    Random rand = new Random();

    public void insert(Node node, String value){

        if (node.Children == null){
            node.Children.put(value, new Node(true));
            return;
        }

        for(String currentKey : node.Children.keySet()){
            if(currentKey.charAt(0) == value.charAt(0)){
                split(node, currentKey, value);
                return;
            }
        }

        node.Children.put(value, new Node(true));

    }

    private void split(Node node, String toSplit, String newString){

        String newVal = "";
        boolean hasBeenPut = false;

            for (int i = 0; i < toSplit.length() && i < newString.length(); i++) {
                if (toSplit.charAt(i) == newString.charAt(i)) newVal += toSplit.charAt(i);
                else {
                    newString = newString.substring(i);
                    String oldString = toSplit.substring(i);
                    i = toSplit.length();
                    node.Children.put(newVal, new Node(false));
                    node.Children.get(newVal).Children.put(oldString, node.Children.get(toSplit));
                    node.Children.remove(toSplit);
                    node.Children.get(newVal).Children.put(newString, new Node(true));
                    hasBeenPut = true;
                }
            }

            if(!hasBeenPut){
                if(toSplit.length() > newString.length()){
                    String oldString = toSplit.substring(newString.length());
                    node.Children.put(newString, new Node(true));
                    node.Children.get(newString).Children.put(oldString, node.Children.get(toSplit));
                    node.Children.remove(toSplit);
                }
                else if(toSplit.length() < newString.length()){
                    String toAdd = newString.substring(toSplit.length());
                    insert(node.Children.get(toSplit), toAdd);
                }
                else{
                    node.Children.get(toSplit).isWord = true;
                }
            }
    }

    public boolean contains(Node node, String value){

        if (node.Children == null){
            return false;
        }

        for(String currentKey : node.Children.keySet()){
            iterations++;
            if(currentKey.equals(value)) return node.Children.get(currentKey).isWord;
            if(currentKey.charAt(0) == value.charAt(0)){
                for(int i = 1; i < currentKey.length(); i++){
                    if(i >= value.length() || currentKey.charAt(i) != value.charAt(i)) return false;
                }
                return contains(node.Children.get(currentKey), value.substring(currentKey.length()));
            }
        }
        return false;
    }

    private void deletion(Node node, String value){

            for(String currentKey : node.Children.keySet()){
                if(currentKey.equals(value)) {
                    if (node.Children.get(currentKey).Children.size() > 0) node.Children.get(currentKey).isWord = false;
                    else node.Children.remove(currentKey);
                    return;
                }
                if(currentKey.charAt(0) == value.charAt(0))
                    deletion(node.Children.get(currentKey), value.substring(currentKey.length()));
            }
    }

    public void delete(String value){
        if(contains(root, value)) {
            deletion(root, value);
        }
    }

    private void generate(int quantity){

        for(int i = 0; i < quantity; i++){
            String newWord = "";
            for (int j = 0; j < rand.nextInt(10) + 6; j++) {
                char letter = (char)(rand.nextInt(26) + 97);
                newWord += letter;
            }
            insert(root, newWord);
        }

    }

    public void test(){

        insert(root, "algorytmy");

        for(int i = 1000; i <= 100000; i +=1000) {
            generate(i);
            contains(root, "algorytmy");
            System.out.println(i + " " + iterations);
            iterations = -1;
        }

    }


    public static void main(String[] args){

        RadixTrie trie = new RadixTrie();

        trie.insert(trie.root, "Roman");
        trie.insert(trie.root, "Romek");
        trie.insert(trie.root, "Rom");
        trie.delete("Rom");
        System.out.println(trie.contains(trie.root, "Rom") + "\n");
        System.out.println(trie.contains(trie.root, "Romek") + "\n");

//       RadixTrie TestingTrie = new RadixTrie();
//
//       TestingTrie.test();



    }
}
