package scheduler;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import scheduler.job.FcmJob;
import scheduler.job.FcmJobListener;

import javax.annotation.PostConstruct;
import java.util.function.Function;

/**
 * 스케줄러 구성을 지정합니다.
 *
 * @author : lee
 * @fileName : SchedulerConfiguration
 * @since : 2/27/24
 */
@Configuration
public class SchedulerConfiguration implements WebMvcConfigurer {
    private Scheduler scheduler;
    private final ApplicationContext applicationContext;

    // 애플리케이션 영역을 가져오기 위한 이름
    final private static String APPLICATION_NAME = "appContext";

    // 생성자를 통해 두개의 객체 구성
    public SchedulerConfiguration(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * FCM 전송을 위한 스케줄러 구성
     */
    @PostConstruct
    private void configScheduler() throws SchedulerException {

        JobDataMap ctx = new JobDataMap();                  // 스케줄러에게 애플리케이션 영역을 추가합니다.
        ctx.put(APPLICATION_NAME, applicationContext);      // 애플리케이션 영역을 "appContext"으로 지정합니다.

        // [STEP1] Job 생성
        JobDetail job = JobBuilder
                .newJob(FcmJob.class)                                   // Job 구현 클래스
                .withIdentity("fcmSendJob", "fcmGroup")     // Job 이름, 그룹 지정
                .withDescription("FCM 처리를 위한 조회 Job")   // Job 설명
                .setJobData(ctx)
                .build();

        // [STEP2] Trigger 생성
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("fcmSendTrigger", "fcmGroup")         // Trigger 이름, 그룹 지정
                .withDescription("FCM 처리를 위한 조회 Trigger")     // Trigger 설명
                .startNow()
                .withSchedule(
                        SimpleScheduleBuilder
                                .simpleSchedule()
                                .withIntervalInSeconds(5)
                                .repeatForever())
                .build();

        // [STEP3] 스케줄러 생성 및 Job, Trigger 등록
        scheduler = new StdSchedulerFactory().getScheduler();
        FcmJobListener fcmJobListener = new FcmJobListener();
        scheduler.getListenerManager().addJobListener(fcmJobListener);
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }


    @PostConstruct
    public void schInit() throws SchedulerException {

        System.out.println("[+] 일단 구성이 완료되었는가?");
        //크론스케줄을 쓰겠다는 함수
        final Function<String, Trigger> trigger = (exp) -> TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule(exp)).build();

        JobDataMap ctx = new JobDataMap();  //스케줄러에게 어플리케이션(Application)영역을 넣어 줍니다.
        ctx.put(APPLICATION_NAME, applicationContext);  //넣어줄 때 이름은 "appContext" 입니다.
        JobDetail jobDetail = JobBuilder.newJob(FcmJob.class).setJobData(ctx).build();  //스케줄을 생성해서
        scheduler.scheduleJob(jobDetail, trigger.apply("0/59 * * * * ?"));  //크론형식을 더해 시작합니다
    }

}
