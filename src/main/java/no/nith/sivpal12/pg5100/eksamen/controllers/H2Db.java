package no.nith.sivpal12.pg5100.eksamen.controllers;

import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Startup
@Singleton
public class H2Db {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2Db.class);
    private Server server;

    // Forbid object creation
    private H2Db() {
    }

    @PostConstruct
    private void startH2ConsoleAccess() throws SQLException {
        try {
            server = Server.createWebServer("-webAllowOthers")
                    .start();
            LOGGER.info(String.format("H2 console started at: %s",
                    server.getURL()));
        } catch (SQLException e) {
            LOGGER.warn("Could no start H2 console access");
            throw e;
        }
    }

    @PreDestroy
    private void stopH2ConsoleAccess() {
        LOGGER.info("Shutting down H2 console access.");
        server.stop();
    }

}
