package com.solvd.laba.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConnectionPool {
    final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private CopyOnWriteArrayList<Connection> pool = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<Connection> usedPool = new CopyOnWriteArrayList<>();

    ConnectionPool(int size) {
        for (int i = 0; i < size; i++) {
            pool.add(new Connection());
        }
    }

    public synchronized Connection getConnection() {
        if (pool.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Connection c = pool.remove(0);
        usedPool.add(c);
        return c;
    }

    public synchronized void releaseConnection(Connection c) {
        LOGGER.info("Released");
        usedPool.remove(c);
        pool.add(c);
        notify();
    }
}
