package trees;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    Map<Character, Map<Character, TrieNode>> children;
    boolean isEnd;

    public TrieNode() {
        children = new HashMap<>();
        isEnd = false;
    }
}
