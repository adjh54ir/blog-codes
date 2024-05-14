package mapper;

import model.FcmSendDeviceDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : FcmMapper
 * @since : 2/26/24
 */
@Service
public interface FcmMapper {

    List<FcmSendDeviceDto> selectFcmSendList();

}
