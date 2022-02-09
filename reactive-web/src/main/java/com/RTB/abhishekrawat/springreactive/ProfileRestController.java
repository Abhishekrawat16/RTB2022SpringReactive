package com.RTB.abhishekrawat.springreactive;

import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping(value = "/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
@org.springframework.context.annotation.Profile("classic")
class ProfileRestController {

	private static final MediaType mediaType = MediaType.APPLICATION_JSON_UTF8;
	private final ProfileService profileRepository;

	ProfileRestController(ProfileService profileRepository) {
		this.profileRepository = profileRepository;
	}

	@GetMapping
	public Publisher<Profile> getAll() {
		return this.profileRepository.all();
	}

	@GetMapping("/{id}")
	public Publisher<Profile> getById(@PathVariable("id") String id) {
		return this.profileRepository.get(id);
	}

	@PostMapping
	public Publisher<ResponseEntity<Profile>> create(@RequestBody Profile profile) {
		return this.profileRepository
				.create(profile.getEmail(), profile.getInvestments(), profile.getAssets(), profile.getPremiums(),
						profile.getDebts())
				.map(p -> ResponseEntity.created(URI.create("/profiles/" + p.getId())).contentType(mediaType).build());
	}

	@DeleteMapping("/{id}")
	public Publisher<Profile> deleteById(@PathVariable String id) {
		return this.profileRepository.delete(id);
	}

	@PutMapping("/{id}")
	public Publisher<ResponseEntity<Profile>> updateById(@PathVariable String id, @RequestBody Profile profile) {
		return Mono.just(profile)
				.flatMap(p -> this.profileRepository.update(id, p.getEmail(), p.getInvestments(), p.getAssets(),
						p.getPremiums(), p.getDebts()))
				.map(p -> ResponseEntity.ok().contentType(ProfileRestController.mediaType).build());
	}
}
