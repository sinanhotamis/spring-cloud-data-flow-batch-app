package com.snnlab.springclouddataflowbatch.step.chunk;

import com.snnlab.springclouddataflowbatch.model.SnnLabInfoDTO;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.core.io.FileUrlResource;

import java.net.MalformedURLException;

public class SingleF2FJobItemWriter extends FlatFileItemWriter<SnnLabInfoDTO> {

    private static final String ENCODING_UTF8 = "UTF-8";
    private static final String ITEM_WRITER_LINE_DELIMITER= "|";
    private static final String ITEM_WRITER_OUTPUT_FILE = "snnLabF2FJobReaderOutput.txt";

    public SingleF2FJobItemWriter(String resourcePath) throws MalformedURLException {
        this.setResource(new FileUrlResource(resourcePath + ITEM_WRITER_OUTPUT_FILE));
        this.setEncoding(ENCODING_UTF8);
        this.setLineAggregator(generateLineAgregator());
        this.setAppendAllowed(true);
        this.open(new ExecutionContext());
    }

    private LineAggregator<SnnLabInfoDTO> generateLineAgregator() {
        return new DelimitedLineAggregator<>() {
            {
                setDelimiter(ITEM_WRITER_LINE_DELIMITER);
                setFieldExtractor(new BeanWrapperFieldExtractor<>() {
                    { setNames(new String[]{"labId", "labAmount", "labCurrency"});}
                });
            }
        };
    }
}
