package com.snnlab.springclouddataflowbatch.step.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class SingleF2FJobTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        //TODO any single task
        System.out.println("Example tasklet is executed..");
        return RepeatStatus.FINISHED;
    }
}
