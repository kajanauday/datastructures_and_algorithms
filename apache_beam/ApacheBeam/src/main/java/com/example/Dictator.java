package com.example;
import org.apache.beam.sdk.Pipeline;

public class Dictator {
    public static void main(String[] args) throws Exception {
        Pipeline pipeline = Pipeline.create();
        MapExample mapExample = new MapExample();
        mapExample.execute(pipeline,"/workspace/practice/apache_beam/ApacheBeam/src/resources/input_map.txt");
        pipeline.run().waitUntilFinish();
    }



}