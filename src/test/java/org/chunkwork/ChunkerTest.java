package org.chunkwork;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

/**
 * Unit tests for {@link Chunker}.
 *
 * @author Jonathan Cone
 */
public class ChunkerTest {
    private static final int TEST_CHUNK_SIZE = 3;

    @Test(expected = IllegalArgumentException.class)
    public void testChunkingNoSize() {
        runChunking(Arrays.asList(1, 2, 3, 4, 5, 6, 7), 0);
    }

    @Test
    public void testChunking0() {
        runChunking(null);
    }


    @Test
    public void testChunking1() {
        runChunking(new ArrayList<>());
    }

    @Test
    public void testChunking2() {
        runChunking(Collections.singletonList(1));
    }

    @Test
    public void testChunking3() {
        runChunking(Arrays.asList(1, 2));
    }

    @Test
    public void testChunking4() {
        runChunking(Arrays.asList(1, 2, 3, 4, 5, 6));
    }

    @Test
    public void testChunking5() {
        runChunking(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
    }

    @Test
    public void testChunking6() {
        runChunking(Arrays.asList(1, 2, 3, 4, 5, 6, 7), 1);
    }

    @Test
    public void testChunking7() {
        runChunking(Arrays.asList(1, 2, 3, 4, 5, 6, 7), 2);
    }

    @Test
    public void testChunking8() {
        runChunking(Arrays.asList(1, 2, 3, 4, 5, 6, 7), 3);
    }

    @Test
    public void testChunking9() {
        runChunking(Arrays.asList(1, 2, 3, 4, 5, 6, 7), 4);
    }

    @Test
    public void testChunking10() {
        runChunking(Arrays.asList(1, 2, 3, 4, 5, 6, 7), 5);
    }

    @Test
    public void testChunking11() {
        runChunking(Arrays.asList(1, 2, 3, 4, 5, 6, 7), 6);
    }

    @Test
    public void testChunking12() {
        runChunking(Arrays.asList(1, 2, 3, 4, 5, 6, 7), 7);
    }

    @Test
    public void testChunking13() {
        runChunking(Arrays.asList(1, 2, 3, 4, 5, 6, 7), 8);
    }


    public void runChunking(List<Integer> block) {
        runChunking(block, TEST_CHUNK_SIZE);
    }

    public void runChunking(List<Integer> block, int chunkSize) {

        final List<Integer> result = new ArrayList<>();

        Chunker.execute(block, chunkSize, result::addAll);


        if (block == null) {
            assertEquals("The result should have been an empty list.",
                    new ArrayList<Integer>(), result);
        } else {
            assertEquals("The result should have matched the input block.",
                    block, result);
        }
    }

    @Test
    public void docTest() {
        Chunker.execute(IntStream.range(0, 64)
                        .boxed()
                        .map(i -> String.format("%03x", i))
                        .toList(),
                8,
                System.out::println);
    }

}
