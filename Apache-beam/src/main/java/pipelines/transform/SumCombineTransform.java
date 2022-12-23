package pipelines.transform;

import org.apache.beam.sdk.transforms.SerializableFunction;

public class SumCombineTransform implements SerializableFunction<Iterable<Long>, Long> {
	@Override
	public Long apply(Iterable<Long> input) {
		Long sum = 0l;

		for (Long elem : input) {
			sum += elem;
		}

		return sum;
	}
}
