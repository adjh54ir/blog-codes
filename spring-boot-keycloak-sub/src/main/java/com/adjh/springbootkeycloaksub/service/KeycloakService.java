package com.adjh.springbootkeycloaksub.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : KeycloakService
 * @since : 2025. 1. 31.
 */
@Service
public interface KeycloakService {
    boolean isValidToken(String token);
}
