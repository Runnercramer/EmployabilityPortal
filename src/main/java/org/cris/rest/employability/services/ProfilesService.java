package org.cris.rest.employability.services;

import org.cris.rest.employability.models.dtos.ProfileDTO;

public interface ProfilesService {
    String createProfile(ProfileDTO profileDTO);
    ProfileDTO getProfileById(String id);
    String updateProfile(String id, ProfileDTO profileDTO);
}
