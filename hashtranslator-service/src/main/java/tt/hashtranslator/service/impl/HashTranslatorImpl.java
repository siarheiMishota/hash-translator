package tt.hashtranslator.service.impl;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import tt.hashtranslator.config.TranslatorProperties;
import tt.hashtranslator.service.HashTranslator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HashTranslatorImpl implements HashTranslator {

    private final RestTemplate restTemplate;
    private final TranslatorProperties translatorProperties;

    public HashTranslatorImpl(RestTemplate restTemplate, TranslatorProperties translatorProperties) {
        this.restTemplate = restTemplate;
        this.translatorProperties = translatorProperties;
    }

    @Override
    public Map<String, String> translateAll(Collection<String> hashes) {
        return hashes.stream().collect(Collectors.toMap(hash -> hash, this::translate));
    }

    @Override
    public String translate(String hash) {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("hash", hash);
            params.put("hash_type", translatorProperties.getHashType());
            params.put("email", translatorProperties.getEmail());
            params.put("code", translatorProperties.getCode());

            ResponseEntity<String> response = restTemplate.exchange(translatorProperties.getUrl(), HttpMethod.GET, null, String.class, params);
            return response.getBody();
        } catch (RestClientException e) {
            return "";
        }
    }
}
