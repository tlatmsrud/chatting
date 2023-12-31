package org.ssk.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.springframework.stereotype.Component;

/**
 * title        :
 * author       : sim
 * date         : 2023-07-24
 * description  :
 */

@Slf4j
@Component
public class CustomJobListener implements JobListener {


    @Override
    public String getName() {
        return CustomJobListener.class.getSimpleName();
    }

    /**
     * Job 실행 직전 메서드
     */
    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        String jobKey = context.getJobDetail().getKey().getName();
        log.info("["+jobKey+"] 에 대한 Job을 실행합니다.");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {

    }

    /**
     * Job 실행 완료 직후 메서드
     */
    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException e) {
        String jobKey = context.getJobDetail().getKey().getName();

        if(e != null) {
            e.printStackTrace();
            log.error("["+jobKey+"] 에 대한 Job 실행 도중 에러가 발생하였습니다.!!",e);
        }else {
            log.info("["+jobKey+"] 에 대한 Job이 정상적으로 종료되었습니다.");
        }
    }
}
