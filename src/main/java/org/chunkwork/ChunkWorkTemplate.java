package org.chunkwork;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;

/**
 * This template allows you to take a large List input that you need to operate
 * upon and break it up into smaller chunks, each to be processed independently.
 * You can also store the results of the operation in a single cumulative result
 * object which you specify in the constructor.
 * 
 * The workload is null-safe so you can pass in a null collection and not have
 * to worry about {@link NullPointerException}s.
 * 
 * 
 * @param <T>
 *            The type parameter for the input list workload.
 * @param <S>
 *            The type of object to store the cumulative results in.
 * 
 * @author Jonathan Cone
 */
public abstract class ChunkWorkTemplate<T> {

	private static final int DEFAULT_WORK_CHUNK_SIZE = 500;

	private int chunkSize;

	private List<T> workload;

	public ChunkWorkTemplate(List<T> workload) {
		this(DEFAULT_WORK_CHUNK_SIZE, workload);
	}

	public ChunkWorkTemplate(int chunkSize, List<T> workload) {
		this.chunkSize = chunkSize;
		this.workload = workload;
	}

	@SuppressWarnings("unchecked")
	public void execute() {

		if (CollectionUtils.isNotEmpty(workload)) {

			List<T> fixedWorkload = (List<T>) ListUtils
					.unmodifiableList(workload);

			int chunkCount = (int) Math.ceil(fixedWorkload.size()
					/ (double) chunkSize);

			for (int i = 0; i < chunkCount; i++) {
				int chunkStart = i * chunkSize;

				List<T> chunk = fixedWorkload.subList(chunkStart,
						Math.min(chunkStart + chunkSize, fixedWorkload.size()));

				executeOnChunk(chunk);

			}
		}

	}

	protected abstract void executeOnChunk(List<T> chunk);
}
