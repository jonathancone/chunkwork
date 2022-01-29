package org.chunkwork;

import java.util.List;

public interface Chunk<T> {
    void work(List<T> chunk);
}
