package com.hostmdy.attendance.attendance.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class AttendanceController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnFilter;

    @FXML
    private Button btnNew;

    @FXML
    private Button btnRefresh;

    @FXML
    private ComboBox<?> cobCourse;

    @FXML
    private TableColumn<?, ?> course;

    @FXML
    private TableView<?> courseTable;

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private Label lblBack;

    @FXML
    private Label lblNowDate;

    @FXML
    private TableColumn<?, ?> semester;

    @FXML
    private TableColumn<?, ?> studentName;

    @FXML
    private TableColumn<?, ?> year;

    @FXML
    void processBack(MouseEvent event) {

    }

    @FXML
    void processDelete(ActionEvent event) {

    }

    @FXML
    void processNew(ActionEvent event) {

    }

    @FXML
    void processRefresh(ActionEvent event) {

    }

}
