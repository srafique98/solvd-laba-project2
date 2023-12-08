package com.solvd.laba.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Connection {

    final Logger LOGGER = LogManager.getLogger(Connection.class);

    public void connect() {
        LOGGER.info("Connected!");
    }
}
