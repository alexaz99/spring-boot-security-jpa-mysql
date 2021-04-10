package com.alex.springbootsecurityjpamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * Here we are doing to do jdbc authentication instead of in memory authentication
 * that we did in spring-boot-security project.
 *
 * see resources/schema.sql and resources/data.sql
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    // declare datasource, autoware and configure in application.properties file
    // in out case we configure to point to our mysql database
    @Autowired
    DataSource dataSource;

    /**
     * Authentication override method
     * Use JPA connection.
     * There is no out of the box implementation.
     * The way to get data using JPA is
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource);

                // Case 2. When we have already pre-populated DB with users.
                // in case the table name and fields are different
                // use for flexibility. The simplest is to use defauls database and table creation
/*                .usersByUsernameQuery("select username, password, enabled "
                        + "from users "
                        + "where username = ?")
                .authoritiesByUsernameQuery("select username, authority "
                        + "from authorities "
                        + "where username = ?");*/


                // Case 1 create with default schema and insert users in DB on startup
                // instead of creating on fly, in real world it should be pre-populated. Comment code below
                /*.withDefaultSchema()
                .withUser(
                        User.withUsername("user")
                        .password("pass")
                        .roles("USER")
                )
                .withUser(
                        User.withUsername("admin")
                                .password("pass")
                                .roles("ADMIN")
                );*/
    }

    /**
     * Build on fly.
     * instead of creating on fly, in real world it should be pre-populated.
     * See method above. We created 2 sql scripts; one for ddl schema.sql, second for data data.sql
     */
/*    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
        .withUser(
                User.withUsername("user")
                .password("pass")
                .roles("USER")
        )
        .withUser(
                User.withUsername("admin")
                        .password("pass")
                        .roles("ADMIN")
        );
    }*/

    /**
     * Authorization override method for each API.
     * Allow access to different endpoints based on user role
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // start from the most restrictive access role to less restrictive resources and pages
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("ADMIN", "USER")
                .antMatchers("/").permitAll()
                .and().formLogin();
    }

    /**
     * Also need this encoder
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
