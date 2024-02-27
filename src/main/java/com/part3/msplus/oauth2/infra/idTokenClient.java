package com.part3.msplus.oauth2.infra;

import com.part3.msplus.global.exception.dto.Error;
import com.part3.msplus.oauth2.command.domain.Oauth2Client;
import com.part3.msplus.oauth2.command.domain.Oauth2Property;
import com.part3.msplus.oauth2.command.domain.State;
import com.part3.msplus.oauth2.command.domain.StateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class idTokenClient implements Oauth2Client {

    private final RestTemplate restTemplate;

    private final StateRepository stateRepository;

    private final JSONParser jsonParser = new JSONParser();

    public String getAuthorizationUri(Oauth2Property oauth2Property, State state) {
        State savedState = stateRepository.save(state);

        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl(oauth2Property.getAuthorizationUri())
                .queryParam("client_id", oauth2Property.getClientId())
                .queryParam("response_type", oauth2Property.getResponseType())
                .queryParam("redirect_uri", oauth2Property.getRedirectUri())
                .queryParam("scope", oauth2Property.getScope())
                .queryParam("state", savedState.getId().toString())
                .build(true);

        return uriComponents.toUriString();
    }

    public String getToken(String code, String state, Oauth2Property oauth2Property) {
        validateState(state);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        MultiValueMap<String, String> bodyData = new LinkedMultiValueMap<>();
        bodyData.setAll(Map.of(
                "client_id", oauth2Property.getClientId(),
                "client_secret", oauth2Property.getSecretId(),
                "code", code,
                "grant_type", oauth2Property.getGrantType(),
                "redirect_uri", oauth2Property.getRedirectUri()));

        ResponseEntity<String> response = restTemplate.postForEntity(
                oauth2Property.getAccessTokenUri(),
                new HttpEntity<>(bodyData, headers),
                String.class);

        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody());
            return (String) jsonObject.get("id_token");
        } catch (ParseException e) {
            log.error("{}", e.getMessage());
            throw new RuntimeException(e);
        }
    }


    private void validateState(String state) {
        if (stateRepository.findById(UUID.fromString(state)).isEmpty()) {
            throw new InvalidStateException(Error.INVALID_STATE_VALUE);
        }
    }
}
