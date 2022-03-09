/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mesu.Digital.LoginJWT.Service;

import Mesu.Digital.LoginJWT.AppUser.AppUser;
import java.time.LocalDateTime;
import java.util.UUID;
import mesu.digital.loginjwt.Repository.AppUserRepository;
import lombok.AllArgsConstructor;
import mesu.digital.loginjwt.Security.Token.ConfirmationToken;
import mesu.digital.loginjwt.Service.ConfirmationTokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author AZM
 */
@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService
{

    private final static String USER_NOT_FOUND_MSG = "User With email %s not found";
    private final AppUserRepository appUserRepository;
    private final ConfirmationTokenService confirmationTokenService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername (String email) throws UsernameNotFoundException
    {
        return appUserRepository.findByEmail (email)
                .orElseThrow (() -> new UsernameNotFoundException (String.format (USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser (AppUser appUser)
    {
        boolean userExists = appUserRepository.findByEmail (appUser.getEmail ()).isPresent ();

        if (userExists)
        {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new IllegalStateException ("Email alraedy Taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode (appUser.getPassword ());

        appUser.setPassword (encodedPassword);

        appUserRepository.save (appUser);

        String token = UUID.randomUUID ().toString ();
        ConfirmationToken confirmationToken = new ConfirmationToken (
                token, LocalDateTime.now (), LocalDateTime.now ().plusMinutes (15), appUser);

        confirmationTokenService.saveConfirmationToken (confirmationToken);
        //TODO send email
        return token;
    }

    public int enableAppUser (String email)
    {
        return appUserRepository.enableAppUser (email);
    }
}
