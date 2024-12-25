package com.blog.springbootwebflux.service;

import com.blog.springbootwebflux.model.entity.CodeEntity;
import com.blog.springbootwebflux.model.entity.UserEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : CodeService
 * @since : 24. 12. 24.
 */
@Service
public interface CodeService {
    Mono<CodeEntity> findAllByCd(String cd);
}
