/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mesu.Digital.LoginJWT.Controller;

import Mesu.Digital.LoginJWT.Request.RegistrationRequest;
import Mesu.Digital.LoginJWT.Service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author AZM
 */
@RestController
@RequestMapping("/api/v1/registration")
@AllArgsConstructor
public class RegistrationController
{

    private RegistrationService registrationService;

    @PostMapping
    public String register (@RequestBody RegistrationRequest request)
    {
        return registrationService.register (request);

    }

    @GetMapping(path = "confirm")
    public String confirm (@RequestParam("token") String token)
    {
        return registrationService.confirmToken (token);
    }
}
