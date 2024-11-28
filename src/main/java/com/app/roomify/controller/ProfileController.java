package com.app.roomify.controller;

import com.app.roomify.domain.Profile;
import com.app.roomify.provider.exchange.response.ProfileResponse;
import com.app.roomify.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileService profileService;
    private final ConversionService conversionService;

    @GetMapping
    public ResponseEntity<List<ProfileResponse>> getAllProfiles() {
        List<Profile> profiles = profileService.getAllProfiles();
        List<ProfileResponse> profileResponses = profiles.stream()
                .map(profile -> conversionService.convert(profile, ProfileResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(profileResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponse> getProfileById(@PathVariable int id) {
        Profile profile = profileService.getProfileById(id);
        ProfileResponse profileResponse = conversionService.convert(profile, ProfileResponse.class);
        return ResponseEntity.ok(profileResponse);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileResponse createProfile(@RequestBody ProfileResponse profileResponse) {
        Profile profile = conversionService.convert(profileResponse, Profile.class);
        profile = profileService.saveProfile(profile);
        return conversionService.convert(profile, ProfileResponse.class);
    }

    @PutMapping("/{id}")
    public ProfileResponse updateProfile(@PathVariable int id, @RequestBody ProfileResponse profileResponse) {
        profileResponse.setId(id);
        Profile profile = conversionService.convert(profileResponse, Profile.class);
        profile = profileService.saveProfile(profile);
        return conversionService.convert(profile, ProfileResponse.class);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable int id) {
        profileService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
}
