package com.example;
import org.apache.beam.sdk.Pipeline;
public class PipelineHandler{
    Pipeline pipeline = null;
    public Pipeline getPipeline(){
        return this.pipeline;
    } 
    public void setPipeline(){
        pipeline = Pipeline.create();
    }
}