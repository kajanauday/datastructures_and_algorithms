package com.example;
import org.apache.beam.sdk.transforms.DoFn;

public class PrintStringElement extends DoFn<String, Void> {
    @DoFn.ProcessElement
    public void processElement(@DoFn.Element String element) {
        System.out.println(element);
    }
}


