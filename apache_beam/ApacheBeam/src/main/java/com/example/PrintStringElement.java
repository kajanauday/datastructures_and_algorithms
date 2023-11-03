package com.example;
import org.apache.beam.sdk.transforms.DoFn;

public class PrintStringElement extends DoFn<String, Void> {
    @ProcessElement
    public void processElement(@Element String element) {
        System.out.println(element);
    }
}


