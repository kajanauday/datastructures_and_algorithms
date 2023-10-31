package com.example;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.transforms.*;
import org.apache.beam.sdk.values.PCollection;
class Pipe{

}
public class Sample {
    public static void main(String[] args) throws Exception{
        PipelineHandler pipelineHandler = new PipelineHandler();
        pipelineHandler.setPipeline();
        System.out.println("in progress...................");
        PCollection<String> staticCollection = pipelineHandler.pipeline.apply(Create.of(
                "Element 1",
                "Element 2",
                "Element 3",
                "Element 4"));
        staticCollection.apply("printing", ParDo.of(new PrintElement()));
        pipelineHandler.pipeline.run().waitUntilFinish();
    }
    static class PrintElement extends DoFn<String,Void>{
        @DoFn.ProcessElement
        public void processElement(@DoFn.Element String element){
            System.out.println(element);
        }
    }
}