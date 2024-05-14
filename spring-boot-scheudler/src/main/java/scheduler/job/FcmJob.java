package scheduler.job;

import lombok.extern.slf4j.Slf4j;
import model.FcmSendDeviceDto;
import model.FcmSendDto;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.context.ApplicationContext;
import service.FcmService;

import java.io.IOException;
import java.util.List;

/**
 * FCM 메시지 전송을 위한 Job
 *
 * @author : lee
 * @fileName : FcmJob
 * @since : 2/23/24
 */

@Slf4j
public class FcmJob implements Job {

    private FcmService fcmService;

    /**
     * Job 실행시 수행이 됩니다.
     *
     * @param jobExecutionContext JobExecutionContext
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {


        if (fcmService == null) {
            // [STEP1] Service 인터페이스를 호출하기 위해 ApplicationContext에 appContext 이름으로 bean을 등록합니다.
            ApplicationContext appCtx = (ApplicationContext) jobExecutionContext.getJobDetail().getJobDataMap().get("appContext");
            fcmService = appCtx.getBean(FcmService.class);
        }

        // [STEP2] FCM 전송 리스트 구성합니다. : FCM 토큰을 구성합니다.
        List<FcmSendDeviceDto> selectFcmSendList = fcmService.selectFcmSendList();

        // [STEP3] 리스트를 순회하며 값들을 추출합니다. : 토큰 값을 반환하여 FCM에 전송할 데이터를 구성합니다.
        for (FcmSendDeviceDto fcmSendItem : selectFcmSendList) {

            // [STEP4] FCM 전송 데이터를 구성합니다.
            FcmSendDto fcmSendDto = FcmSendDto.builder()
                    .token(fcmSendItem.getDeviceToken())
                    .title("푸시 메시지입니다!")
                    .body("계획된 시간이 되었어요!")
                    .build();
            try {
                // [STEP5] FCM 전송을 합니다.
                fcmService.sendMessageTo(fcmSendDto);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
