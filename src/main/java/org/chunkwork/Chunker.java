package org.chunkwork;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

/**
 * Take a large List input that you need to operate upon and break it up into smaller
 * chunks, each to be processed independently.
 * <p>
 * The workload is null-safe so you can pass in a null collection without risk of
 * {@link NullPointerException}s.
 *
 * @author Jonathan Cone
 */
public class Chunker {

    private static final int DEFAULT_WORK_CHUNK_SIZE = 500;

    public static <T> void execute(List<T> block, Chunk<T> chunk) {
        execute(block, DEFAULT_WORK_CHUNK_SIZE, chunk);
    }

    public static <T> void execute(Collection<T> block, int chunkSize, Chunk<T> chunk) {

        if (chunkSize < 1) {
            throw new IllegalArgumentException("chunkSize must be greater than zero");
        }

        if (CollectionUtils.isNotEmpty(block)) {
            List<T> fixedWorkload = new ArrayList<>(block);

            int chunkCount = (int) Math.ceil(fixedWorkload.size()
                    / (double) chunkSize);

            for (int i = 0; i < chunkCount; i++) {
                int chunkStart = i * chunkSize;

                chunk.work(fixedWorkload.subList(chunkStart,
                                Math.min(chunkStart + chunkSize,
                                        fixedWorkload.size()))
                        // Make immutable
                        .stream()
                        .toList());

            }
        }

    }
}
