package no.nsd.qddt.domain.user;

import no.nsd.qddt.domain.BaseService;

import java.util.UUID;

/**
 * @author Dag Østgulen Heradstveit
 * @author Stig Norland
 */

public interface UserService extends BaseService<User,UUID> {

    /**
     * Return a {@link User} by email
     * @param email users email
     * @return the user belonging to the email
     */
    User findByEmail(String email);

}
