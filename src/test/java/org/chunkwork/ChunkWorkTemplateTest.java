package org.chunkwork;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * Unit tests for {@link ChunkWorkTemplate}.
 * 
 * @author Jonathan Cone
 */
public class ChunkWorkTemplateTest {
	private static final int TEST_CHUNK_SIZE = 3;

	@Test
	public void testChunking0() {
		runChunking(null);
	}

	@Test
	public void testChunking1() {
		runChunking(new ArrayList<Integer>());
	}

	@Test
	public void testChunking2() {
		runChunking(Arrays.asList(1));
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

	public void runChunking(List<Integer> workload) {

		final List<Integer> result = new ArrayList<Integer>();

		new ChunkWorkTemplate<Integer>(TEST_CHUNK_SIZE, workload) {
			protected void executeOnChunk(List<Integer> chunk) {
				result.addAll(chunk);
			};
		}.execute();

		if (workload == null) {
			assertEquals("The result should have been an empty list.",
					new ArrayList<Integer>(), result);
		} else {
			assertEquals("The result should have matched the input workload.",
					workload, result);
		}
	}

}
