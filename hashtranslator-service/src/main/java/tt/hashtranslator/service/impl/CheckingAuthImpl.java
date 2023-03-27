package tt.hashtranslator.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import tt.hashtranslator.dto.UserDto;
import tt.hashtranslator.service.CheckingAuth;

@Service
public class CheckingAuthImpl implements CheckingAuth {
    private final RestTemplate restTemplate;

    @Value("${api.auth-url}")
    private String url;

    public CheckingAuthImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public UserDto checkUserIn(String authorization) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.AUTHORIZATION, authorization);

            HttpEntity request = new HttpEntity(headers);

            ResponseEntity<UserDto> response = restTemplate.exchange(url + "/users/check", HttpMethod.POST, request, UserDto.class);
            return response.getBody();
        } catch (RestClientException e) {
            return null;
        }
    }
}
