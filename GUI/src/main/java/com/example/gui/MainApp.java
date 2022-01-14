package com.example.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import sudoku.factories.SudokuBoardDaoFactory;


public class MainApp extends Application {
    public static final Logger logger = LogManager.getLogger(MainApp.class);

    @Override
    public void start(Stage stage) {

        try {
            var dao = SudokuBoardDaoFactory.getJdbcDao("board1","jdbc:derby:baza_sudoku;create=true");


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