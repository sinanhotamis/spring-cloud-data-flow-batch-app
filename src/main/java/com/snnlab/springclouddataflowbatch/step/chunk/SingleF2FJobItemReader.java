package com.snnlab.springclouddataflowbatch.step.chunk;

import com.snnlab.springclouddataflowbatch.model.SnnLabInfoDTO;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileUrlResource;

import java.net.MalformedURLException;

public class SingleF2FJobItemReader implements ItemReader<SnnLabInfoDTO> {

    private FlatFileItemReader<SnnLabInfoDTO> paymentInfoDTOFlatFileItemReader;

    private static final String ENCODING_UTF8 = "UTF-8";

    private static final String ITEM_READER_LINE_TOKENIZER = ";";

    private static final String ITEM_READER_INPUT_FILE = "snnLabF2FJobReaderInput.txt";

    public SingleF2FJobItemReader(String resourcePath) throws MalformedURLException {
        DefaultLineMapper<SnnLabInfoDTO> defaultLineMapper = generateDefaultLineMapper();
        paymentInfoDTOFlatFileItemReader = new FlatFileItemReader<>();
        paymentInfoDTOFlatFileItemReader.setResource(new FileUrlResource(resourcePath + ITEM_READER_INPUT_FILE));
        paymentInfoDTOFlatFileItemReader.setEncoding(ENCODING_UTF8);
        paymentInfoDTOFlatFileItemReader.setLineMapper(defaultLineMapper);
        paymentInfoDTOFlatFileItemReader.open(new ExecutionContext());
    }

    @Override
    public SnnLabInfoDTO read() throws Exception {
        return paymentInfoDTOFlatFileItemReader.read();
    }

    private DefaultLineMapper<SnnLabInfoDTO> generateDefaultLineMapper() {
        DefaultLineMapper<SnnLabInfoDTO> defaultLineMapper = new DefaultLineMapper<>();
        FieldSetMapper<SnnLabInfoDTO> fieldSetMapper = generateFieldSetMapper();
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer(ITEM_READER_LINE_TOKENIZER);

        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }

    private FieldSetMapper<SnnLabInfoDTO> generateFieldSetMapper() {
        FieldSetMapper<SnnLabInfoDTO> fieldSetMapper = fieldSet -> {
            SnnLabInfoDTO snnLabInfoDTO = new SnnLabInfoDTO();
            snnLabInfoDTO.setLabId(fieldSet.readString(0));
            snnLabInfoDTO.setLabAmount(fieldSet.readBigDecimal(1));
            snnLabInfoDTO.setLabCurrency(fieldSet.readString(2));

            return snnLabInfoDTO;
        };

        return fieldSetMapper;
    }
}
