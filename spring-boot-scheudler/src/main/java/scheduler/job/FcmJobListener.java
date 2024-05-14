package scheduler.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : FcmJobListener
 * @since : 2/23/24
 */
public class FcmJobListener implements JobListener {
    @Override
    public String getName() {
        return "hello";
    }

    /**
     * Job 실행 이전 수행
     *
     * @param jobExecutionContext
     */
    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        System.out.println("[-] Job이 실행되기전 수행됩니다");
    }

    /**
     * Job 실행 취소 시점 수행
     *
     * @param jobExecutionContext
     */
    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        System.out.println("[-] Job이 실행 취소된 시점 수행됩니다.");

    }

    /**
     * Job 실행 완료 시점 수행
     *
     * @param jobExecutionContext
     * @param e
     */
    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        System.out.println("[+] Job이 실행 완료된 시점 수행됩니다.");
    }
}
