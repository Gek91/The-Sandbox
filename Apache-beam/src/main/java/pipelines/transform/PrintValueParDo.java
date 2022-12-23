package pipelines.transform;

import org.apache.beam.sdk.transforms.DoFn;

public class PrintValueParDo extends DoFn<Long, Long>  {

	@ProcessElement
	public void processElement(@Element Long value, OutputReceiver<Long> out) {

		System.out.println(value);

		out.output(value);
	}
}
