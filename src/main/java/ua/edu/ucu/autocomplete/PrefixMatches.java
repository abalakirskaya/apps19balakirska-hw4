package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author andrii
 */
public class PrefixMatches {

    private Trie trie;

    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    public int load(String... strings) {
        int counter = 0;
        for(String s: strings){
            if (s.length() > 2) {
                this.trie.add(new Tuple(s, s.length()));
                counter++;
            }
        }
        return counter;
    }

    public boolean contains(String word) {
        return this.trie.contains(word);
    }

    public boolean delete(String word) {
        return this.trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        if(pref.length() >= 2) {
            return this.trie.wordsWithPrefix(pref);
        }
        return null;
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        Iterable<String> old_list = this.trie.wordsWithPrefix(pref);
        List<String> list1 = new ArrayList();
        for (String str: old_list){
            list1.add(str);
        }
        Collections.sort(list1, Comparator.comparing(String::length));
        List<String> list = new ArrayList();
        int counter = 0;
        int curr_length = 0;
        for (String str : list1){
            if (curr_length != str.length()) {
                curr_length = str.length();
                counter++;
                if (counter == k+1) {
                    break;
                }

            }
            if (str.length() >= 2) {
                list.add(str);
            }

        }
        Collections.sort(list, Comparator.comparing(String::length));
        return list;
    }

    public int size() {
        return this.trie.size();
    }
}
