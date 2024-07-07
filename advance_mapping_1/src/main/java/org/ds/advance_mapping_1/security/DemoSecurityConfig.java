//package org.ds.advance_mapping_1.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.provisioning.UserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DemoSecurityConfig {
//
//    /*    In memory Security*/
////    @Bean
////    public InMemoryUserDetailsManager userDetailsManager() {
////        UserDetails uthpalaH = User.builder()
////                .username("uthpala")
////                .password("{noop}test1234")
////                .roles("EMPLOYEE", "MANAGER", "ADMIN")
////                .build();
////        UserDetails sandeeraJ = User.builder()
////                .username("sandeera")
////                .password("{noop}test123")
////                .roles("EMPLOYEE", "MANAGER")
////                .build();
////        UserDetails nadunD = User.builder()
////                .username("nadun")
////                .password("{noop}test12345")
////                .roles("EMPLOYEE")
////                .build();
////
////        return new InMemoryUserDetailsManager(sandeeraJ, uthpalaH, nadunD);
////    }
//
////    Security using Spring tables
////    @Bean
////    public UserDetailsManager userDetailsManager(DataSource dataSource){
////        return new JdbcUserDetailsManager(dataSource);
////    }
//
//    //  Security using Spring tables
//    @Bean
//    public UserDetailsManager userDetailsManager(DataSource dataSource) {
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//
//
//        // define query to retrieve a user by a username
//        jdbcUserDetailsManager.setUsersByUsernameQuery("select user_id,pw,active from members where user_id = ?");
//
//        // define query to retrieve the authorities/roles by username
//        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id,role from roles where user_id = ?");
//        return jdbcUserDetailsManager;
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.authorizeHttpRequests(configurer -> {
//            configurer.requestMatchers(HttpMethod.GET, "api/v1/employees").hasRole("EMPLOYEE");
//            configurer.requestMatchers(HttpMethod.GET, "api/v1/employees/**").hasRole("EMPLOYEE");
//            configurer.requestMatchers(HttpMethod.POST, "api/v1/employees").hasRole("MANAGER");
//            configurer.requestMatchers(HttpMethod.PUT, "api/v1/employees").hasRole("MANAGER");
//            configurer.requestMatchers(HttpMethod.DELETE, "api/v1/employees/**").hasRole("ADMIN");
//        });
//
//        // use http basic authentication
//        httpSecurity.httpBasic(Customizer.withDefaults());
//
//        //disable CSRF
//        // in general, not required for stateless REST APIs that use POST,PUT, DELETE and/or PATCH
//        httpSecurity.csrf(csrf -> csrf.disable());
//        return httpSecurity.build();
//    }
//}
