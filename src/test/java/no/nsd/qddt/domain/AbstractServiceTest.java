package no.nsd.qddt.domain;

import no.nsd.qddt.QDDT;
import no.nsd.qddt.utils.BeforeSecurityContext;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Default test class that can be implemented by all
 * test classes testing {@link org.springframework.stereotype.Service} classes.
 *
 * Created by Dag Østgulen Heradstveit.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = QDDT.class)
public abstract class AbstractServiceTest implements ServiceTest {

    private List<BaseRepository> baseRepository;

    private BeforeSecurityContext beforeSecurityContext;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Before
    public void setup() {

        this.beforeSecurityContext = new BeforeSecurityContext(authenticationManager);
        this.beforeSecurityContext.createSecurityContext();
    }

    public void setBaseRepositories(BaseRepository...baseRepository) {
        this.baseRepository =  Arrays.asList(baseRepository);
    }

    public List<BaseRepository> getBaseRepository() {
        return baseRepository;
    }

    public void setBaseRepository(List<BaseRepository> baseRepository) {
        this.baseRepository = baseRepository;
    }

    public BeforeSecurityContext getBeforeSecurityContext() {
        return beforeSecurityContext;
    }

    @After
    public void tearDown() {
        baseRepository.forEach(CrudRepository::deleteAll);
    }
}