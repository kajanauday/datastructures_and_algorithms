package com.example;
import org.apache.beam.sdk.transforms.DoFn;

public class PrintIntegerElement extends DoFn<Integer, Void> {
    @DoFn.ProcessElement
    public void processElement(@DoFn.Element Integer element) {
        System.out.println(element);
    }
}
