package com.RTB.abhishekrawat.springreactive;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.util.StringUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;
import java.util.function.Predicate;

@DataMongoTest
@Import(ProfileService.class)
 class ProfileServiceTest {

    private final ProfileService service;
    private final ProfileRepository repository;

    public ProfileServiceTest(@Autowired ProfileService service,
                              @Autowired ProfileRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @Test
    void getAll() {
        Flux<Profile> saved = repository.saveAll(Flux.just(new Profile(null, "Josh"), new Profile(null, "Matt"), new Profile(null, "Jane")));

        Flux<Profile> composite = service.all().thenMany(saved);

        Predicate<Profile> match = profile -> saved.any(saveItem -> saveItem.equals(profile)).block();

        StepVerifier
            .create(composite)
            .expectNextMatches(match)
            .expectNextMatches(match)
            .expectNextMatches(match)
            .verifyComplete();
    }

    @Test
    void save() {
        Mono<Profile> profileMono = this.service.create("email@email.com", 1000, 100000, 100, 10000);
        StepVerifier
            .create(profileMono)
            .expectNextMatches(saved -> StringUtils.hasText(saved.getId()))
            .verifyComplete();
    }

    @Test
    void delete() {
        String test = "test";
        Mono<Profile> deleted = this.service
            .create(test, 1000, 100000, 100, 10000)
            .flatMap(saved -> this.service.delete(saved.getId()));
        StepVerifier
            .create(deleted)
            .expectNextMatches(profile -> profile.getEmail().equalsIgnoreCase(test))
            .verifyComplete();
    }

    @Test
    void update() throws Exception {
        Mono<Profile> saved = this.service
            .create("test", 1000, 100000, 100, 10000)
            .flatMap(p -> this.service.update(p.getId(), "test1", 1000, 100000, 100, 10000));
        StepVerifier
            .create(saved)
            .expectNextMatches(p -> p.getEmail().equalsIgnoreCase("test1"))
            .verifyComplete();
    }

    @Test
    void getById() {
        String test = UUID.randomUUID().toString();
        Mono<Profile> deleted = this.service
            .create(test, 1000, 100000, 100, 10000)
            .flatMap(saved -> this.service.get(saved.getId()));
        StepVerifier
            .create(deleted)
            .expectNextMatches(profile -> StringUtils.hasText(profile.getId()) && test.equalsIgnoreCase(profile.getEmail()))
            .verifyComplete();
    }
}