
package ua.edu.ucu.autocomplete;

import static org.hamcrest.Matchers.containsInAnyOrder;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import ua.edu.ucu.tries.RWayTrie;

/**
 *
 * @author Andrii_Rodionov
 */
public class PrefixMatchesITTest {

    private PrefixMatches pm;
    private PrefixMatches list;

    @Before
    public void init() {
        pm = new PrefixMatches(new RWayTrie());
        pm.load("abc", "abce", "abcd", "abcde", "abcdef");
    }

    @Test
    public void testWordsWithPrefix_String() {
        String pref = "ab";

        Iterable<String> result = pm.wordsWithPrefix(pref);


        String[] expResult = {"abc", "abce", "abcd", "abcde", "abcdef"};

        assertThat(result, containsInAnyOrder(expResult));
    }

    @Test
    public void testWordsWithPrefix_String_and_K() {
        String pref = "abc";
        int k = 3;

        Iterable<String> result = pm.wordsWithPrefix(pref, k);

        String[] expResult = {"abc", "abce", "abcd", "abcde"};

        assertThat(result, containsInAnyOrder(expResult));
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void test_wordsWithPrefix2() {
        String pref = "abce";
        int k = 1;

        Iterable<String> result = pm.wordsWithPrefix(pref, k);


    }
    @Test
    public void testWordsWithPrefix_2() {
        String pref = "abc";
        int k = 2;

        Iterable<String> result = pm.wordsWithPrefix(pref, k);

        String[] expResult = {"abc", "abce", "abcd"};

        assertThat(result, containsInAnyOrder(expResult));
    }

    @Test
    public void testWordsWithPrefix_less_than_2() {
        String pref = "a";
        int k = 2;
        list = new PrefixMatches(new RWayTrie());
        list.load("a", "b", "c", "d");

        Iterable<String> result = list.wordsWithPrefix(pref, k);

        String[] expResult = {};

        assertThat(result, containsInAnyOrder(expResult));
    }





}
