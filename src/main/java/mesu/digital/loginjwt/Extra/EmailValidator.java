/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mesu.digital.loginjwt.Extra;

import java.util.function.Predicate;
import org.springframework.stereotype.Service;

/**
 *
 * @author AZM
 */
@Service
public class EmailValidator implements Predicate<String>
{

    @Override
    public boolean test (String t)
    {
        //TODO: Regex to validate email
        
        return true;
    }

}
