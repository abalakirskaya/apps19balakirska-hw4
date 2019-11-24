package ua.edu.ucu.tries;
import ua.edu.ucu.tries.immutable.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class RWayTrie implements Trie {
    private static int R = 256;
    private Node root;
    private static class Node
    {
        private Object val;
        private Node[] next = new Node[R];
    }
    @Override
    public void add(Tuple t)
    {  root = add(root, t.term, t.weight, 0);  }
    public Tuple add(String key)
    {
        Node x = get(root, key, 0);
        if (x == null) return null;
        return (Tuple) x.val;
    }
    private Node add(Node x, String key, int val, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {  x.val = val; return x; }
        char c = key.charAt(d);
        x.next[c] = add(x.next[c], key, val, d+1);
        return x;
    }
    public Tuple get(String word)
    {
        Node x = get(root, word, 0);
        if (x == null) return null;
        return (Tuple) x.val;
    }
    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[c], key, d+1);
    }
    @Override
    public boolean contains(String word) {
        if (get(word) == null){
            return false;
        }
        return true;
    }
    @Override
    public boolean delete(String key) {
        root = delete(root, key, 0);
        return root == null;
    }
    private Node delete(Node x, String key, int d)
    {
        if (x == null) {
            return null;
        }
        if (d == key.length())
            x.val = null;
        else
        {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d+1);
        }
        if (x.val != null) return x;
        for (char c = 0; c < R; c++) {
            if (x.next[c] != null) {
                return x;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size(root);
    }
    private int size(Node x)
    {
        if (x == null) return 0;
        int cnt = 0;
        if (x.val != null) cnt++;
        for (char c = 0; c < R; c++)
            cnt += size(x.next[c]);
        return cnt;
    }

    @Override
    public Iterable<String> words()
    {  return wordsWithPrefix("");  }

    @Override
    public Iterable<String> wordsWithPrefix(String pre)
    {   List<String> result = new ArrayList<>();
        Queue q = new Queue();
        collect(get(root, pre, 0), pre, q);
        for(int i = 0; i < size(); i++){
            result.add((String) q.dequeue());
        }
        return result;
    }
    private void collect(Node x, String pre,
                         Queue q)
    {
        if (x == null) return;
        if (x.val != null) q.enqueue(pre);
        for (char c = 0; c < R; c++)
            collect(x.next[c], pre + c, q);
    }

}
