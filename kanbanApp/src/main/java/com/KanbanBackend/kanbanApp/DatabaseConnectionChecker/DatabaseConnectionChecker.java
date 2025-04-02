package com.KanbanBackend.kanbanApp.DatabaseConnectionChecker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

@Component
public class DatabaseConnectionChecker {
    private  final DataSource dataSource;
    private  static final Logger logger = LoggerFactory.getLogger(DatabaseConnectionChecker.class);

    @Autowired
    public  DatabaseConnectionChecker(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void checkDatabaseConnection(){
        logger.info("checking database connection....");

        try( Connection connection = dataSource.getConnection()){
            boolean valid = connection.isValid(10);

            if (valid){
                DatabaseMetaData metaData = connection.getMetaData();
                logger.info("Succesfully connected to the Database:{} {}",
                        metaData.getDatabaseProductName(),
                        metaData.getDatabaseProductVersion());
                logger.info("Connection URL: {}", metaData.getURL());
            }else{
                logger.error("Database conncetion is invalid!");
            }
        }catch (SQLException e){
            logger.error("Failed to connect to database: {}", e.getMessage(), e);
        }
    }
    // Method to manually check connection on demand
    public boolean isDatabaseConnected() {
        try (Connection connection = dataSource.getConnection()) {
            return connection.isValid(5);
        } catch (SQLException e) {
            logger.error("Database connection check failed: {}", e.getMessage());
            return false;
        }
    }
}
