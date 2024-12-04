package com.blog.springbootwebflux.controller;

import com.blog.springbootwebflux.dto.UserDto;
import com.blog.springbootwebflux.service.FluxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/mono")
public class MonoController {

    private final FluxService fluxService;

    public MonoController(FluxService fluxService) {
        this.fluxService = fluxService;
    }

    @GetMapping("/items")
    public Flux<UserDto> getUserList() {
        return null;
    }

    @GetMapping("/items/{userId}")
    public Mono<UserDto> getUserByUserId(@PathVariable String userId) {
        return null;
    }
}
