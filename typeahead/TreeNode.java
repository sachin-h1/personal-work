package interview.typeahead;

import java.util.HashMap;
import java.util.Map;


public class TreeNode {
    Map<Character, TreeNode> children;
    char c;
    boolean isWord;

    public TreeNode(char c) {
        this.c = c;
        children = new HashMap<>();
    }

    public TreeNode() {
        children = new HashMap<>();
    }

    public void insert(String word) {
        if (word != null && !word.isEmpty()) {
            char firstChar = word.charAt(0);
            TreeNode child = children.get(firstChar);
            if (child == null) {
                child = new TreeNode(firstChar);
                children.put(firstChar, child);
            }
            if (word.length() > 1)
                child.insert(word.substring(1));
            else
                child.isWord = true;
        }
    }

}