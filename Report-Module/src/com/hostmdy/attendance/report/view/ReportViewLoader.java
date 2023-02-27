package com.hostmdy.attendance.report.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ReportViewLoader {
	public Parent getReportUI() {

		Parent reportUI = null;
		try {
			reportUI = FXMLLoader.load(getClass().getResource("ReportUI.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reportUI;
	}
}
