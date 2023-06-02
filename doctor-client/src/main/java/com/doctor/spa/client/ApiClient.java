package com.doctor.spa.client;

import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class ApiClient {
    private final RestTemplate restTemplate;


    public ApiClient() {
        this.restTemplate = new RestTemplate();
    }

    public ResponseEntity<String> get(Map<String, String> requestHeaders, String url, Map<String, String> params) {
        HttpHeaders headers = createHeaders(MediaType.APPLICATION_JSON);
        String authorization = requestHeaders.get("Authorization");
        if (authorization != null && !authorization.isEmpty()) {
            headers.set("Authorization", requestHeaders.get("Authorization"));
        }
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        return restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class, params);
    }

    public ResponseEntity<String> post(Map<String, String> requestHeaders, String url, String requestBody, MediaType contentType) {
        HttpHeaders headers = createHeaders(contentType);
        String authorization = requestHeaders.get("Authorization");
        if (authorization != null && !authorization.isEmpty()) {
            headers.set("Authorization", requestHeaders.get("Authorization"));
        }
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
    }

    private HttpEntity<String> createRequestEntity() {
        HttpHeaders headers = createHeaders(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(headers);
    }

    private HttpHeaders createHeaders(MediaType mediaType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        headers.set("Authorization", getAuthorizationHeader());
        return headers;
    }


    private String getAuthorizationHeader() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                return "Bearer " + userDetails.getPassword(); // Example: Assuming password as the access token
            }
        }
        return null;
    }

    // Additional methods for other HTTP methods (e.g., PUT, DELETE) can be added here

    // Example usage
    public static void main(String[] args) {
/*        ApiClient apiClient = new ApiClient();

        // GET request example
        String getUrl = "https://api.example.com/some-endpoint";
        ResponseEntity<String> getResponse = apiClient.get(getUrl);

        System.out.println("GET Response Body: " + getResponse.getBody());
        System.out.println("GET Status Code: " + getResponse.getStatusCode());

        // POST request example
        String postUrl = "https://api.example.com/some-endpoint";
        String requestBody = "{\"name\":\"John\",\"age\":30}";
        MediaType contentType = MediaType.APPLICATION_JSON;

        ResponseEntity<String> postResponse = apiClient.post(postUrl, requestBody, contentType);

        System.out.println("POST Response Body: " + postResponse.getBody());
        System.out.println("POST Status Code: " + postResponse.getStatusCode());*/
    }
}
