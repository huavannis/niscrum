package com.hvnis.niscrum.repository;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.stereotype.Repository;

/**
 * @author hvnis
 */
@Repository
public class PersistentLoginRepository extends JdbcTokenRepositoryImpl {

    /**
     * The constructor
     * 
     * @param dataSource
     */
    public PersistentLoginRepository(@Autowired DataSource dataSource) {
        super();
        setDataSource(dataSource);
    }
}
