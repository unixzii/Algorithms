package me.cyandev.test;

import me.cyandev.LCS;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * TDD for {@link me.cyandev.LCS}.
 */
@RunWith(JUnit4.class)
public class LCSTest {

    @Test
    public void test() {
        assertEquals("abca", LCS.lcs("abcicba", "abdkscab"));
        assertEquals("blog", LCS.lcs("cnblogs", "belong"));
        assertEquals("ae", LCS.lcs("cyandev", "apache"));
    }

}
