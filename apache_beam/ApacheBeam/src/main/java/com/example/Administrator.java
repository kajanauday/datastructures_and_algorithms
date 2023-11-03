package com.example;

import org.apache.beam.sdk.Pipeline;

public class Administrator {
    public static void main(String[] args) throws Exception {
        Pipeline pipeline = Pipeline.create();
        //new MapExample().execute(pipeline, "/workspace/practice/apache_beam/ApacheBeam/src/resources/input_map.txt");
        new CombinersExample().execute(pipeline,  "/workspace/practice/apache_beam/ApacheBeam/src/resources/input_map.txt");
        pipeline.run().waitUntilFinish();
    }
}