package org.cris.rest.employability.services.impl;

import org.cris.rest.employability.services.SecurityService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class SecurityServiceImpl implements SecurityService {
    @Override
    public String obfuscateData(String data, boolean isSensitive) {
        char[] response = new char[10];

        if (isSensitive) {
            Arrays.fill(response, '*');
            return new String(response);
        }

        Arrays.fill(response, '*');
        response[0] = data.charAt(0);
        response[1] = data.charAt(1);
        response[8] = data.charAt(data.length() - 2);
        response[9] = data.charAt(data.length() - 1);

        return new String(response);
    }

}
