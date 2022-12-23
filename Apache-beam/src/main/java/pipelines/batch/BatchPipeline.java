package pipelines.batch;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.options.*;
import org.apache.beam.sdk.transforms.*;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PCollectionList;
import pipelines.transform.PowParDo;
import pipelines.transform.PrintValueParDo;
import pipelines.transform.SumCombineTransform;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BatchPipeline {

	//define pipeline options
	public interface BatchPipelineOptions extends PipelineOptions {

		//to popolate values as option using command line args use "--values=val1,val2,val3" as main argument
		@Description("values")
		@Validation.Required
		ValueProvider<String> getValues();
		void setValues(ValueProvider<String> values);
	}

	public static void main(String[] args) {

		PipelineOptionsFactory.register(BatchPipelineOptions.class);
		BatchPipelineOptions options = PipelineOptionsFactory
				.fromArgs(args) //for local testing, pass values as main args
                .withValidation() //for local testing
				.as(BatchPipelineOptions.class);


		Pipeline pipeline = Pipeline.create(options);

		ValueProvider<String> valuesProvider = options.getValues();

		PCollection<Long> pcollectionsValues = pipeline.apply(Create.of(1l)) //needed for creare the first step of pipeline
			// retrieve values from input
			.apply(ParDo.of(
					new DoFn<Long, Long>() {
					@DoFn.ProcessElement
					public void processElement(DoFn.OutputReceiver<Long> out) {

						List<Long> values = Arrays.asList(valuesProvider.get().split(",")).stream().map(x -> Long.valueOf(x)).collect(Collectors.toList());

						values.forEach(elem -> out.output(elem));
					}
				}
			))
		//print intput values
		.apply(ParDo.of(new PrintValueParDo()))
		.apply(ParDo.of(new PowParDo()))
		.apply(ParDo.of(new PrintValueParDo()));

		//get even from pcollection and sum
		PCollection<Long> evenSum = pcollectionsValues.apply(ParDo.of(new DoFn<Long, Long>() {
			@ProcessElement
			public void processElement(@Element Long value, OutputReceiver<Long> out) {
				if(value % 2 == 0) {
					out.output(value);
				}
			}
		}))
		.apply(Combine.globally(new SumCombineTransform()))
		.apply(ParDo.of(new PrintValueParDo()));

		//get odd from pcollection and sum
		PCollection<Long> oddSum = pcollectionsValues.apply(ParDo.of(new DoFn<Long, Long>() {
			@ProcessElement
			public void processElement(@Element Long value, OutputReceiver<Long> out) {
				if(value % 2 != 0) {
					out.output(value);
				}
			}
		}))
		.apply(Combine.globally(new SumCombineTransform()))
		.apply(ParDo.of(new PrintValueParDo()));;

		//merge pcollections
		PCollectionList.of(evenSum).and(oddSum).apply(Flatten.<Long>pCollections())
		.apply(ParDo.of(new PrintValueParDo()));

		pipeline.run();
	}
}
