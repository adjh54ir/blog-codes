package com.blog.springbootwebflux.service.impl;

import com.blog.springbootwebflux.model.entity.CodeEntity;
import com.blog.springbootwebflux.model.entity.UserEntity;
import com.blog.springbootwebflux.repository.CodeRepository;
import com.blog.springbootwebflux.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : CodeServiceImpl
 * @since : 24. 12. 24.
 */
@Service
public class CodeServiceImpl implements CodeService {

    private CodeRepository codeRepository;

    public CodeServiceImpl(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }


    @Override
    public Mono<CodeEntity> findAllByCd(String cd) {

        Mono<CodeEntity> codeInfo = codeRepository.findAllByCd(cd);

        codeInfo.subscribe(
                data -> System.out.println("Code` data: " + data),
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Completed")
        );

        return codeInfo;
    }
}
