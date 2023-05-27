package com.doctor.spa.api.client;

import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.client.RestTemplate;

public class ApiClient {
    private final RestTemplate restTemplate;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;


    public ApiClient(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.restTemplate = new RestTemplate();
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    public ResponseEntity<String> get(String url) {
        HttpEntity<String> requestEntity = createRequestEntity();
        return restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
    }

    public ResponseEntity<String> post(String url, String requestBody, MediaType contentType) {
        HttpHeaders headers = createHeaders(contentType);
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

    public void authenticate(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        // Set the authenticated user in the SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT token
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        //return generateJwtToken(userDetails);
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
