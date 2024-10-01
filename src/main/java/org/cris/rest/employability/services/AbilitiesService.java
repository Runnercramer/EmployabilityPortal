package org.cris.rest.employability.services;

import java.util.List;
import java.util.Map;

public interface AbilitiesService {
    List<String> saveAbilities(List<Map<String, String>> abilities);
}
