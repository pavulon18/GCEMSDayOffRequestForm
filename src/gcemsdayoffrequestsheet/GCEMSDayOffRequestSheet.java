/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gcemsdayoffrequestsheet;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Text;


/**
 *
 * @author Jim Baize
 * Copyright 2017
 */

public class GCEMSDayOffRequestSheet extends Application {
    
    Stage stage;
    TextField txtFieldEmployeeOne;
    TextField txtFieldEmployeeTwo;
    TextField dateRequestOff;
    TextField dateEmployeeOneWork;
    TextField dateEmployeeTwoWork;
    TextField stationEmployeeOneWork;
    TextField stationEmployeeTwoWork;
    Date dateToday = new Date();
    SimpleDateFormat ft = new SimpleDateFormat("LLL dd yyyy");
    
    
    
    @Override
    public void start(Stage primaryStage)
    {
        stage = primaryStage;
        
        //Create the form Header
        Label formHeaderOne = new Label("Vacation / Personal Day / Swap");
        Label formHeaderTwo = new Label("Request Form In Progress");
        Label formHeaderThree = new Label("GCEMS");
        
        //Create the employee name label and text field
        Label lblEmployeeName = new Label("Employee Name");
        txtFieldEmployeeOne = new TextField();
        txtFieldEmployeeOne.setPromptText("Employee initiating request");
        String txtEmployeeOne = txtFieldEmployeeOne.getText();
        
        //Create the Today Date pane
        Label lblTodayDate = new Label("Today's Date");
        Label lbldateTodayPlaceHolder = new Label(ft.format(dateToday));
        
        //Create the First Half / Second Half / Whole shift ComboBox
        ComboBox<String> shiftType;
        shiftType = new ComboBox<>();
        shiftType.getItems().addAll("First Half", "Second Half", "Whole Shift");
        shiftType.getSelectionModel().select(null);
        
        //Create the First Half / Second Half / Whole shift ComboBox
        ComboBox<String> shiftDuration;
        shiftDuration = new ComboBox<>();
        shiftDuration.getItems().addAll("First Half", "Second Half", "Whole Shift");
        shiftDuration.getSelectionModel().select(null);
        
        //Create the DayOff type and Date Pane and combobox
        ComboBox<String> cboxDayOffType;
        cboxDayOffType = new ComboBox<>();
        cboxDayOffType.getItems().addAll("Vacation", "Personal", "Swap");
        cboxDayOffType.getSelectionModel().select(null);
        Label lblDayOffDate = new Label(" ");
        
        //Create the Swap Type Pane
        Label lblSwapOne = new Label("Date Employee One is working");
        Label lblSwapOneDate = new Label();
        Label lblSwapTwo = new Label("Date Employee Two is working");
        Label lblSwapTwoDate = new Label();
        DatePicker dpOne = new DatePicker();
        dpOne.setOnAction(e ->
        {
           LocalDate date = dpOne.getValue();
           lblSwapOneDate.setText(date.toString());
        });
        
        DatePicker dpTwo = new DatePicker();
        dpTwo.setOnAction(e ->
        {
           LocalDate date = dpTwo.getValue();
           lblSwapTwoDate.setText(date.toString());
        });
        
        //Create the Vacation Day Pane
        Label lblDayOffVacation = new Label("Vacation Day");
        DatePicker dpDayOffVacation = new DatePicker();
        Label lblEmployeeOneVDay = new Label("Employee's Signature: ");
        Label lblSignatureBlankVDay = new Label("________________________");
        
         //Create the Personal Day Pane
        Label lblDayOffPersonal = new Label("Personal Day");
        DatePicker dpDayOffPersonal = new DatePicker();
        Label lblEmployeeOnePDay = new Label("Employee's Signature: ");
        Label lblSignatureBlankPDay = new Label("________________________");
        
        //Putting the Vacation Day Layout stuff in one Hbox Node so I can (hopefully)
        //more easily add and remove it as needed.
        //SignatureLines sigLineVDay = new SignatureLines();
        HBox vacationHBoxLineOne = new HBox(10);
        vacationHBoxLineOne.getChildren().add(lblDayOffVacation);
        vacationHBoxLineOne.getChildren().add(dpDayOffVacation);
        vacationHBoxLineOne.getChildren().add(shiftDuration);
        //vacationHBoxLineTwo.getChildren().add(sigLineVDay.makeSingleSignatureLine());
        
        HBox vacationHBoxLineTwo = new HBox();
        vacationHBoxLineTwo.getChildren().add(lblEmployeeOneVDay);
        vacationHBoxLineTwo.getChildren().add(lblSignatureBlankVDay);

        VBox vacationVBox = new VBox();
        vacationVBox.getChildren().add(vacationHBoxLineOne);
        vacationVBox.getChildren().add(vacationHBoxLineTwo);
        
        //Building the Personal Day Layout VBox Node
        //SignatureLines sigLinePDay = new SignatureLines();
        HBox personalHBoxLineOne = new HBox(10);
        personalHBoxLineOne.getChildren().add(lblDayOffPersonal);
        personalHBoxLineOne.getChildren().add(dpDayOffPersonal);
        personalHBoxLineOne.getChildren().add(shiftType);
        
        HBox personalHBoxLineTwo = new HBox();
        personalHBoxLineTwo.getChildren().add(lblEmployeeOnePDay);
        personalHBoxLineTwo.getChildren().add(lblSignatureBlankPDay);
        
        VBox personalVBox = new VBox();
        personalVBox.getChildren().add(personalHBoxLineOne);
        personalVBox.getChildren().add(personalHBoxLineTwo);
        
        //Building the Swap Day Layout box ... node... whatever it is that I come up with
        HBox swapEmpOneHBox = new HBox(10);
        Label lblSwapEmpOne = new Label("Employee One:");
        TextField tfSwapEmpOne = new TextField();
        Label lblWillWorkOne = new Label("Will work");
        DatePicker dpSwapOne = new DatePicker();
        Label lblEmployeeOneSDayOne = new Label("Employee One's Signature: ");
        Label lblSignatureBlankSDayOne = new Label("________________________");
        Label lblEmployeeOneSDayTwo = new Label("Employee Two's Signature: ");
        Label lblSignatureBlankSDayTwo = new Label("________________________");
        
        
        ComboBox<String> swapOneShiftType;
        swapOneShiftType = new ComboBox<>();
        swapOneShiftType.getItems().addAll("First Half", "Second Half", "Whole Shift");
        swapOneShiftType.getSelectionModel().select(null);
        
        swapEmpOneHBox.getChildren().add(lblSwapEmpOne);
        swapEmpOneHBox.getChildren().add(tfSwapEmpOne);
        swapEmpOneHBox.getChildren().add(lblWillWorkOne);
        swapEmpOneHBox.getChildren().add(swapOneShiftType);
        swapEmpOneHBox.getChildren().add(dpSwapOne);
        
        HBox swapEmpTwoHBox = new HBox(10);
        Label lblSwapEmpTwo = new Label("Employee Two:");
        TextField tfSwapEmpTwo = new TextField();
        Label lblWillWorkTwo = new Label("Will work");
        DatePicker dpSwapTwo = new DatePicker();
        
        ComboBox<String> swapTwoShiftType;
        swapTwoShiftType = new ComboBox<>();
        swapTwoShiftType.getItems().addAll("First Half", "Second Half", "Whole Shift");
        swapTwoShiftType.getSelectionModel().select(null);
        
        swapEmpTwoHBox.getChildren().add(lblSwapEmpTwo);
        swapEmpTwoHBox.getChildren().add(tfSwapEmpTwo);
        swapEmpTwoHBox.getChildren().add(lblWillWorkTwo);
        swapEmpTwoHBox.getChildren().add(swapTwoShiftType);
        swapEmpTwoHBox.getChildren().add(dpSwapTwo);
        
        HBox swapSigLinesOneHBox = new HBox();
        swapSigLinesOneHBox.getChildren().add(lblEmployeeOneSDayOne);
        swapSigLinesOneHBox.getChildren().add(lblSignatureBlankSDayOne);
        
        HBox swapSigLinesTwoHBox = new HBox();
        swapSigLinesTwoHBox.getChildren().add(lblEmployeeOneSDayTwo);
        swapSigLinesTwoHBox.getChildren().add(lblSignatureBlankSDayTwo);
        
        VBox swapVBox = new VBox();
        swapVBox.getChildren().add(swapEmpOneHBox);
        swapVBox.getChildren().add(swapEmpTwoHBox);
        swapVBox.getChildren().add(swapSigLinesOneHBox);
        swapVBox.getChildren().add(swapSigLinesTwoHBox);
        
        //Building the blank or null HBox Node
        HBox blankHBox = new HBox();
        
        //Create the Scheduler's Signature Area
        Text txtApproved = new Text();
        txtApproved.setText("Appoved:");
        
        Text txtYesNo = new Text();
        txtYesNo.setText("Yes_____  No_____");
        
        Text txtSignature = new Text();
        txtSignature.setText("Scheduler Approval signature:");
        Text txtSigLine = new Text();
        txtSigLine.setText("__________________");
        
        
        Text txtApprovalDate = new Text();
        txtApprovalDate.setText("Date of Approval:");
        Text txtDateLine = new Text();
        txtDateLine.setText("__________________");
        
        // Create the button
        Button btnPrint = new Button();
        btnPrint.setText("Print this form");

        //Create the GridPane Layout
        GridPane grid = new GridPane();
        
        //Add nodes to the gridpane
        //grid.setGridLinesVisible(true);
        grid.addRow(0, formHeaderOne);
        grid.addRow(1, formHeaderTwo);
        grid.addRow(2, formHeaderThree);
        grid.addRow(3, lblEmployeeName, txtFieldEmployeeOne);
        grid.addRow(4, lblTodayDate, lbldateTodayPlaceHolder);
        grid.addRow(5, cboxDayOffType, lblDayOffDate);
        grid.addRow(6, blankHBox);
        grid.addRow(7, txtApproved, txtYesNo);
        grid.addRow(8, txtSignature, txtSigLine);
        grid.addRow(9, txtApprovalDate, txtDateLine);
        
        cboxDayOffType.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> obs, String oldVal, String newVal) ->
        {
            grid.getChildren().remove(vacationVBox);
            grid.getChildren().remove(personalVBox);
            grid.getChildren().remove(swapVBox);
            grid.getChildren().remove(blankHBox);
            switch (newVal)
            {
                case "Vacation":
                    grid.add(vacationVBox, 0, 6);
                    break;
                case "Personal":
                    grid.add(personalVBox, 0, 6);
                    break;
                case "Swap":
                    grid.add(swapVBox, 0, 6);
                    break;
                default:
                    grid.add(blankHBox, 0, 6);
                    break;
            }
        });
        
        
        
        //Set alignments and spanning of the GridPane
        GridPane.setHalignment(formHeaderOne, HPos.CENTER);
        GridPane.setHalignment(formHeaderTwo, HPos.CENTER);
        GridPane.setHalignment(formHeaderThree, HPos.CENTER);
        GridPane.setColumnSpan(formHeaderOne, 2);
        GridPane.setColumnSpan(formHeaderTwo, 2);
        GridPane.setColumnSpan(formHeaderThree, 2);
        GridPane.setColumnSpan(vacationVBox, 2);
        GridPane.setColumnSpan(personalVBox, 2);
        GridPane.setColumnSpan(swapVBox, 2);
        
        BorderPane mainScene = new BorderPane();
        mainScene.setCenter(grid);
        mainScene.setTop(btnPrint);
        
        Scene scene = new Scene(mainScene, 700, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("GCEMS Dayoff Request Form");
        primaryStage.show();
        
        //Add the printing capabilities

        btnPrint.setOnAction((ActionEvent e) ->
        {
            PrinterJob job = PrinterJob.createPrinterJob();
            if (job != null && job.showPrintDialog(mainScene.getScene().getWindow()))
            {
                boolean success;
                success = job.printPage(mainScene.getCenter());
                if (success)
            {
                job.endJob();
            }
        }
        });        
        
    }
    
    /*public class SignatureLines
    {
    HBox hboxSingleSignatureLine = new HBox();
    Label lblEmployeeOne = new Label("Employee's Signature: ");
    Label lblSignatureBlank = new Label("________________________");
    
    VBox vboxDoubleSignatureLine = new VBox();
    HBox hboxSingleSignatureLineOne = new HBox();
    Text txtEmployeeOne = new Text("Employee One's Signature: ");
    Text txtSignatureBlank = new Text("________________________");
    HBox hboxSingleSignatureLineTwo = new HBox();
    Text txtEmployeeTwo = new Text("Employee Two's Signature: ");
    Text txtSignatureBlankTwo = new Text("________________________");
    HBox testHBox = new HBox();
    
    public SignatureLines()
    {
    hboxSingleSignatureLine.getChildren().add(lblEmployeeOne);
    hboxSingleSignatureLine.getChildren().add(lblSignatureBlank);
    }
    
    public <singleSignatureLine> singleSignatureLine makeSingleSignatureLine()
    {
    
    
    //testHBox.getChildren().
    return (singleSignatureLine) hboxSingleSignatureLine;
    }
    
    public <doubleSignatureLine> doubleSignatureLine makeDoubleSignatureLine()
    {
    hboxSingleSignatureLineOne.getChildren().add(txtEmployeeOne);
    hboxSingleSignatureLineOne.getChildren().add(txtSignatureBlank);
    
    hboxSingleSignatureLineTwo.getChildren().add(txtEmployeeTwo);
    hboxSingleSignatureLineTwo.getChildren().add(txtSignatureBlankTwo);
    
    return (doubleSignatureLine) vboxDoubleSignatureLine;
    }
    }*/
    
    public void buttonClick()
    {
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    
}