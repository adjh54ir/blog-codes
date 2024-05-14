package controller;

import lombok.extern.slf4j.Slf4j;
import model.FcmSendDeviceDto;
import model.FcmSendDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.FcmService;

import java.io.IOException;
import java.util.List;

/**
 * FCM 관리하는 Controller
 *
 * @author : lee
 * @fileName : FcmController
 * @since : 2/21/24
 */

@Slf4j
@RestController
@RequestMapping("/api/v1/fcm")
public class FcmController {

    private final FcmService fcmService;

    public FcmController(FcmService fcmService) {
        this.fcmService = fcmService;
    }

    @PostMapping("/test")
    public ResponseEntity<Object> push2Message(@RequestBody @Validated FcmSendDto fcmSendDto) throws IOException {

        List<FcmSendDeviceDto> fcmSendDeviceDtoList = fcmService.selectFcmSendList();

        return new ResponseEntity<>(fcmSendDeviceDtoList, HttpStatus.OK);
    }

    @PostMapping("/send")
    public ResponseEntity<Object> pushMessage(@RequestBody @Validated FcmSendDto fcmSendDto) throws IOException {
        log.debug("[+] 푸시 메시지를 전송합니다. " + fcmSendDto.toString());
        fcmService.sendMessageTo(fcmSendDto);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }
}
