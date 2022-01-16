package com.example.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import sudoku.BacktrackingSudokuSolver;
import sudoku.SudokuBoard;
import sudoku.factories.SudokuBoardDaoFactory;
import java.io.File;


public class MainApp extends Application {
    public static final Logger logger = LogManager.getLogger(MainApp.class);

    @Override
    public void start(Stage stage) {
        try {
            File f = new File("@../../config.txt");
            if(!f.exists() && !f.isDirectory()) {
                File myObj = new File("config.txt");
                myObj.createNewFile();
                var JDBCdao =
                        SudokuBoardDaoFactory.getJdbcDao("create", "jdbc:derby:SudokuBase;create=true");
                JDBCdao.close();
            }


            org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
            logger.debug("App run...");
            MainMenu m = new MainMenu();
            m.menuShow();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    public static void main(String[] args) {
        launch(args);
    }

}