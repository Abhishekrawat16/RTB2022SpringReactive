package com.RTB.abhishekrawat.springreactive;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
class ProfileService {

    private final ApplicationEventPublisher publisher;
    private final ProfileRepository profileRepository;

    ProfileService(ApplicationEventPublisher publisher, ProfileRepository profileRepository) {
        this.publisher = publisher;
        this.profileRepository = profileRepository;
    }

    public Flux<Profile> all() { 
        return this.profileRepository.findAll();
    }

    public Mono<Profile> get(String id) {
        return this.profileRepository.findById(id);
    }

    public Mono<Profile> update(String id, String email, double investment, double asset, double premium, double debt) {
        return this.profileRepository
            .findById(id)
            .map(p -> new Profile(p.getId(), email, investment, asset, premium, debt))
            .flatMap(this.profileRepository::save);
    }

    public Mono<Profile> delete(String id) {
        return this.profileRepository
            .findById(id)
            .flatMap(p -> this.profileRepository.deleteById(p.getId()).thenReturn(p));
    }

    public Mono<Profile> create(String email, double investment, double asset, double premium, double debt) {
        return this.profileRepository
            .save(new Profile(null, email, investment, asset, premium, debt))
            .doOnSuccess(profile -> this.publisher.publishEvent(new ProfileCreatedEvent(profile)));
    }
}