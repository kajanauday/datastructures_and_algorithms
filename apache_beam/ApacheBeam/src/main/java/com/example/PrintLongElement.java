package com.example;
import org.apache.beam.sdk.transforms.DoFn;
public class PrintLongElement extends DoFn<Long, Void> {
    @DoFn.ProcessElement
    public void processElement(@DoFn.Element Long element) {
        System.out.println(element);
    }
}