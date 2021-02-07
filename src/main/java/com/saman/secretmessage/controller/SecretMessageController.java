package com.saman.secretmessage.controller;

import com.saman.secretmessage.model.OAuthModel;
import com.saman.secretmessage.model.ServerInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@CrossOrigin("http://localhost:4200")
public class SecretMessageController {

    @CrossOrigin("*")
    @GetMapping("/secret/{token}")
    public ServerInfo secret(@PathVariable String token) throws Exception {
        System.out.println("token: " + token);
        ServerInfo serverInfo = new ServerInfo();
        try{
            if(isValidToken(token)) {
                serverInfo.setWelcomeMsg("Hi, welcome ");
                serverInfo.setSecretMsg("Your secret message is: #$^(( Code working ))^$#");
                serverInfo.setSecretCode(UUID.randomUUID().toString());
            }
        }catch (Exception exc) {
            ResponseStatusException ex = new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "invalid_token", exc);
            throw ex;
        }

        return serverInfo;
    }


    private static boolean isValidToken(String token)
    {
        try {
            final String uri = "http://localhost:9090/oauth/check_token?token="+token;
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getInterceptors().add(
                    new BasicAuthorizationInterceptor("saman", "saman-secure-key"));
            OAuthModel result = restTemplate.postForObject(uri,null, OAuthModel.class);

            System.out.println("token is valid ");
            assert result != null;
            return result.isActive();
        }catch (Exception e){
            System.out.println("token is not valid ");
            throw e;
        }

    }

}
