package com.snnlab.springclouddataflowbatch.job;


import com.snnlab.springclouddataflowbatch.listener.BaseJobListener;
import com.snnlab.springclouddataflowbatch.model.SnnLabInfoDTO;
import com.snnlab.springclouddataflowbatch.step.chunk.SingleF2FJobItemReader;
import com.snnlab.springclouddataflowbatch.step.chunk.SingleF2FJobItemWriter;
import com.snnlab.springclouddataflowbatch.step.tasklet.SingleF2FJobTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;

@Configuration
public class SingleF2FJobConfigurer extends BaseJobConfigurer {

    @Bean
    public Job singleF2FJob(@Autowired SingleF2FJobConfigInfo singleF2FJobConfigInfo) throws MalformedURLException{
        return jobBuilderFactory.get(singleF2FJobConfigInfo.getName())
                .start(firstChunkOrientedStep(singleF2FJobConfigInfo))
                .next(taskletStep())
                .listener(new BaseJobListener())
                .build();
    }


    private Step firstChunkOrientedStep(SingleF2FJobConfigInfo singleF2FJobConfigInfo) throws MalformedURLException {
         return  stepBuilderFactory.get("firstChunkOrientedStep")
                .<SnnLabInfoDTO, SnnLabInfoDTO>chunk(singleF2FJobConfigInfo.getChunkSize())
                .reader(new SingleF2FJobItemReader(singleF2FJobConfigInfo.getResourcePath()))
                .writer(new SingleF2FJobItemWriter(singleF2FJobConfigInfo.getResourcePath()))
                .build();
    }

    private Step taskletStep() {
        return stepBuilderFactory.get("taskletStep")
                .tasklet(new SingleF2FJobTasklet())
                .build();
    }
}


