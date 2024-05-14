package service;

import model.FcmSendDeviceDto;
import model.FcmSendDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * FCM SERVICE
 *
 * @author : lee
 * @fileName : FcmService
 * @since : 2/21/24
 */
@Service
public interface FcmService {
    void sendMessageTo(FcmSendDto fcmSendDto) throws IOException;

    List<FcmSendDeviceDto> selectFcmSendList();

}
