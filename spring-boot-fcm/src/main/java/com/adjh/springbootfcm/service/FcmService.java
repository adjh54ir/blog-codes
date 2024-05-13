package com.adjh.springbootfcm.service;

import com.adjh.springbootfcm.dto.FcmSendDto;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * FCM SERVICE
 *
 * @author : lee
 * @fileName : PushMessageService
 * @since : 2/21/24
 */
@Service
public interface FcmService {

    int sendMessageTo(FcmSendDto fcmSendDto) throws IOException;

}