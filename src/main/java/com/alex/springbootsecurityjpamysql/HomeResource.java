package com.alex.springbootsecurityjpamysql;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

    /**
     * This API should be publicly accessible
     */
    @GetMapping("/")
    public String home() {
        return ("<h1>Welcome</h1>");
    }

    /**
     * This API should be accessible a user or admin role
     */
    @GetMapping("/user")
    public String user() {
        return ("<h1>Welcome User</h1>");
    }

    /**
     * This API should be accessible an admin role
     */
    @GetMapping("/admin")
    public String admin() {
        return ("<h1>Welcome Admin</h1>");
    }
}
