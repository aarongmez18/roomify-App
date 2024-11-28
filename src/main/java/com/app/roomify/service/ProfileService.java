package com.app.roomify.service;

import com.app.roomify.domain.Profile;

import java.util.List;

public interface ProfileService {

    List<Profile> getAllProfiles();
    Profile getProfileById(int id);
    Profile saveProfile(Profile profile);
    void deleteProfile(int id);
}
