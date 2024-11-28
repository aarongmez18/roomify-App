package com.app.roomify.provider;

import com.app.roomify.domain.Profile;
import java.util.List;

public interface RestProfileProvider {

    List<Profile> getAllProfiles();
    Profile getProfileById(int id);
    Profile saveProfile(Profile profile);
    void deleteProfile(int id);
}
