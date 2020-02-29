package com.std.boot.springboot.config;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;


@Configuration
public class H2ServerConfiguration {

    @Bean
    public Server h2tcpserver() throws SQLException {
        return Server.createTcpServer().start();
    }
}
