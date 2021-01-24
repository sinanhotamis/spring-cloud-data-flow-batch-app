package com.snnlab.springclouddataflowbatch.step.chunk;

import com.snnlab.springclouddataflowbatch.model.SnnLabInfoDTO;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.core.io.FileUrlResource;

import java.net.MalformedURLException;
import java.util.List;

public class SingleF2FJobItemWriter implements ItemWriter<SnnLabInfoDTO> {

    private FlatFileItemWriter<SnnLabInfoDTO> paymentInfoDTOFlatFileItemWriter;

    private static final String ENCODING_UTF8 = "UTF-8";

    private static final String ITEM_WRITER_LINE_DELIMITER= "|";

    private static final String ITEM_WRITER_OUTPUT_FILE = "snnLabF2FJobReaderOutput.txt";

    public SingleF2FJobItemWriter(String resourcePath) throws MalformedURLException {
        paymentInfoDTOFlatFileItemWriter = new FlatFileItemWriter<>();
        paymentInfoDTOFlatFileItemWriter.setResource(new FileUrlResource(resourcePath + ITEM_WRITER_OUTPUT_FILE));
        paymentInfoDTOFlatFileItemWriter.setEncoding(ENCODING_UTF8);
        paymentInfoDTOFlatFileItemWriter.setLineAggregator(generateLineAgregator());
        paymentInfoDTOFlatFileItemWriter.setAppendAllowed(true);
        paymentInfoDTOFlatFileItemWriter.open(new ExecutionContext());
    }

    @Override
    public void write(List<? extends SnnLabInfoDTO> list) throws Exception {
        paymentInfoDTOFlatFileItemWriter.write(list);
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
