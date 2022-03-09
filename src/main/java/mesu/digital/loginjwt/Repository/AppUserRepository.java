/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mesu.digital.loginjwt.Repository;

import Mesu.Digital.LoginJWT.AppUser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author AZM
 */
@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends JpaRepository<AppUser, Long>
{

    Optional<AppUser> findByEmail (String email);

    Optional<AppUser> findByEnable (boolean email);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a "
            + "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser (String email);
}
