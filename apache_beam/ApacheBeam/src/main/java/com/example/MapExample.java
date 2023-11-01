package com.example;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.SimpleFunction;
import org.apache.beam.sdk.io.TextIO;

public class MapExample {
    public void execute(Pipeline pipeline, String filePath) {
        pipeline.apply("Reading File : ", TextIO.read().from(filePath))
                .apply("Convering to String :", MapElements.via(new ConvertToInteger()))
                .apply("Sqaring :", MapElements.via(new SqaureElement()))
                .apply("printing :", ParDo.of(new PrintIntegerElement()));
    }
}

class SqaureElement extends SimpleFunction<Integer, Integer> {
    @Override
    public Integer apply(Integer element) {
        return element * element;
    }
}

class ConvertToInteger extends SimpleFunction<String, Integer> {
    @Override
    public Integer apply(String element) {
        return Integer.valueOf(element);
    }
}
