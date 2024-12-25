package com.blog.springbootwebflux.repository;

import com.blog.springbootwebflux.model.entity.CodeEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : CodeRepository
 * @since : 24. 12. 24.
 */
@Repository
public interface CodeRepository extends ReactiveCrudRepository<CodeEntity, Long> {

    Mono<CodeEntity> findAllByCd(String cd);

}
