/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mesu.digital.loginjwt.Email;

/**
 *
 * @author AZM
 */
public interface EmailSender 
{
    void send(String to, String email);
}
