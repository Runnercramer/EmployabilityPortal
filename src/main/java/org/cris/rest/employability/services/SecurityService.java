package org.cris.rest.employability.services;

public interface SecurityService {
    String obfuscateData(String data, boolean isSensitive);
}
