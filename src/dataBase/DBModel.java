/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class DBModel {
    
    Properties properties = new Properties();
    InputStream inputStream;
    String db;
    
    
    
    public void loadPropertiesFile(){
        try {
            inputStream = new FileInputStream("database.properties");
            properties.load(inputStream);
            db = properties.getProperty("db");
        } catch (IOException e) {
            System.out.println("DDDD");
        }
    }

    PreparedStatement pst;

    public void createDataBase() {
        loadPropertiesFile();
        DBConnection con = new DBConnection();
        try {
            pst = con.mkDataBase().prepareStatement("create database if not exists "+db+" DEFAULT CHARACTER SET utf8 \n"
                    + "  DEFAULT COLLATE utf8_general_ci");
            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists "+db+".`User` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `UsrName` VARCHAR(20) NOT NULL,\n"
                    + "  `FullName` VARCHAR(100) ,\n"
                    + "  `EmailAddress` VARCHAR(100) ,\n"
                    + "  `ContactNumber` VARCHAR(100) ,\n"
                    + "  `Salary` double DEFAULT NULL,\n"
                    + "  `Address` text,\n"
                    + "  `Password` VARCHAR(45),\n"
                    + "  `Status` tinyint(1) NOT NULL DEFAULT '0',\n"
                    + "  `UserImage` mediumblob,\n"
                    + "  `Date` date NOT NULL,\n"
                    + "  `CreatorId` int(11),\n"
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");
            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists "+db+".`UserPermission` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `AddProduct` tinyint(1) DEFAULT NULL,\n"
                    + "  `AddSupplyer` tinyint(1) DEFAULT NULL,\n"
                    + "  `AddBrand` tinyint(1) DEFAULT NULL,\n"
                    + "  `AddCatagory` tinyint(1) DEFAULT NULL,\n"
                    + "  `AddUnit` tinyint(1) DEFAULT NULL,\n"
                    + "  `AddCustomer` tinyint(1) DEFAULT NULL,\n"
                    + "  `UpdateProduct` tinyint(1) DEFAULT NULL,\n"
                    + "  `UpdateSupplyer` tinyint(1) DEFAULT NULL,\n"
                    + "  `UpdateBrand` tinyint(1) DEFAULT NULL,\n"
                    + "  `UpdateCatagory` tinyint(1) DEFAULT NULL,\n"
                    + "  `UpdateUnit` tinyint(1) DEFAULT NULL,\n"
                    + "  `UpdateCustomer` tinyint(1) DEFAULT NULL,\n"
                    + "  `RMAManage` tinyint(1) DEFAULT NULL,\n"
                    + "  `SellProduct` tinyint(1) DEFAULT NULL,\n"
                    + "  `ProvideDiscount` tinyint(1) DEFAULT NULL,\n"
                    + "  `EmployeManage` tinyint(1) DEFAULT NULL,\n"
                    + "  `OrgManage` tinyint(1) DEFAULT NULL,\n"
                    + "  `ChangeOwnPass` tinyint(1) DEFAULT NULL,\n"
                    + "  `UserId` int(11) NOT NULL, \n"
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");
            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists "+db+".`Organize` (\n"
                    + "  `Id` int(1) NOT NULL ,\n"
                    + "  `OrgName` varchar(100) DEFAULT NULL,\n"
                    + "  `OrgWeb` varchar(100) DEFAULT NULL,\n"
                    + "  `OrgContactNumbers` text DEFAULT NULL,\n"
                    + "  `OrgContactAddress` text DEFAULT NULL,\n"
                    + "  `OrgLogo` mediumblob,\n"
                    + "  `UserId` int(11) DEFAULT NULL,\n"
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");
            pst.execute();

            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists "+db+".`Supplyer` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `SupplyerName` varchar(100) DEFAULT NULL,\n"
                    + "  `SupplyerPhoneNumber` text DEFAULT NULL,\n"
                    + "  `SupplyerAddress` text DEFAULT NULL,\n"
                    + "  `SuplyerDescription` text DEFAULT NULL,\n"
                    + "  `CreatorId` int(11) DEFAULT NULL,\n"
                    + "  `Date` date NOT NULL,\n"
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");
            pst.execute();

            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists "+db+".`Brands` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `BrandName` varchar(70) DEFAULT NULL,\n"
                    + "  `Description` text DEFAULT NULL,\n"
                    + "  `SupplyerId` varchar(20)  DEFAULT NULL,\n"
                    + "  `CreatorId` int DEFAULT NULL,\n"
                    + "  `Date` date,\n"
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");

            pst.execute();

            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists "+db+".`Catagory` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `CatagoryName` varchar(70) DEFAULT NULL,\n"
                    + "  `CatagoryDescription` text DEFAULT NULL,\n"
                    + "  `BrandId` varchar(20) DEFAULT NULL,\n"
                    + "  `SupplyerId` int(11) DEFAULT NULL,\n"
                    + "  `CreatorId` int(11) DEFAULT NULL,\n"
                    + "  `Date` date,\n"
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");

            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists "+db+".`Unit` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `UnitName` varchar(50) DEFAULT NULL,\n"
                    + "  `UnitDescription` text DEFAULT NULL,\n"
                    + "  `CreatorId` int(11) DEFAULT NULL,\n"
                    + "  `Date` date,\n"
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");

            pst.execute();

            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists "+db+".`RMA` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `RMAName` varchar(100) DEFAULT NULL,\n"
                    + "  `RMADays` varchar(11) NOT NULL,\n"
                    + "  `Comment` text DEFAULT NULL,\n"
                    + "  `CreatorId` int(11) DEFAULT NULL,\n"
                    + "  `Date` date,\n"
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");

            pst.execute();
            
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`Products` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `ProductId` varchar(20) NOT NULL,\n"
                    + "  `ProductName` varchar(150) NOT NULL,\n"
                    + "  `Quantity` varchar(11) NOT NULL DEFAULT '0', \n"
                    + "  `Description` text ,\n"
                    + "  `SupplyerId` varchar(11) NOT NULL,\n"
                    + "  `BrandId` varchar(11) NOT NULL,\n"
                    + "  `CatagoryId` varchar(11) NOT NULL,\n"
                    + "  `UnitId` varchar(11) NOT NULL,\n"
                    + "  `PursesPrice` varchar(100) NOT NULL,\n"
                    + "  `SellPrice` varchar(100) NOT NULL,\n"
                    + "  `RMAId` varchar(11) NOT NULL,\n"
                    + "  `UserId` varchar(11) NOT NULL,\n"
                    + "  `Date` date NOT NULL,\n"
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");
            pst.execute();


            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`Customer` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `CustomerName` varchar(200) NOT NULL,\n"
                    + "  `CustomerContNo` varchar(200) DEFAULT NULL,\n"
                    + "  `CustomerAddress` text,\n"
                    + "  `TotalBuy` varchar(50) DEFAULT NULL,\n"
                    + "  `CreatorId` varchar(11) DEFAULT NULL,\n"
                    + "  `Date` datetime NOT NULL,\n"
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");
            pst.execute();

            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`Sell` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `SellId` varchar(10) NOT NULL,\n"
                    + "  `CustomerId` varchar(11) NOT NULL,\n"
                    + "  `ProductId` varchar(11) NOT NULL,\n"
                    + "  `PursesPrice` double NOT NULL,\n"
                    + "  `SellPrice` double NOT NULL,\n"
                    + "  `Quantity` int(10) NOT NULL,\n"
                    + "  `TotalPrice` double NOT NULL,\n"
                    + "  `WarrentyVoidDate` varchar(20) NOT NULL,\n"
                    + "  `SellerId` int(11) NOT NULL,\n"
                    + "  `SellDate` datetime NOT NULL,\n"
                    + "  PRIMARY KEY (`Id`)\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
            pst.execute();
            
            
          //create table for employee....
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`EmployeeDetails` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `EmployeeId` varchar(50) NOT NULL,\n"
                    + "  `EmployeeName` varchar(150) NOT NULL,\n"
                    + "  `Gender` varchar(20) NOT NULL, \n"
                    + "  `DOB` date NOT NULL,\n"
                    + "  `Qualification` varchar(50) NOT NULL,\n"
                    + "  `Address` varchar(150) NOT NULL,\n"
                    + "  `ContactNo` varchar(50) NOT NULL,\n"
                    + "  `EmailAddress` varchar(100) NOT NULL,\n"
                    + "  `DOJ` date NOT NULL,\n"
                    + "  `Role` varchar(50) NOT NULL,\n"
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");
            pst.execute();
            
            //create table for Project....
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`Project` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `ProjectId` varchar(50) NOT NULL,\n"
                    + "  `ProjectName` varchar(150) NOT NULL,\n"
                    + "  `ProjectDescription` varchar(20) NOT NULL, \n"
                    + "  `DeliveryDate` date NOT NULL,\n"
                    + "  `Duration` int(15) NOT NULL,\n"
                    + "  `ClientName` varchar(150) NOT NULL,\n"
                    + "  `ProjectManager` varchar(150) NOT NULL,\n"                    
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");
            pst.execute();
            
          //create table for Department....
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`Department` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `DepartmentId` varchar(100) NOT NULL,\n"
                    + "  `DepartmentName` varchar(150) NOT NULL,\n"
                    + "  `DepartmentLocation` varchar(120) NOT NULL, \n"                    
                    + "  `DepartmentType` varchar(150) NOT NULL,\n"                    
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");
            
            
          //create table for BugTracking....
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`BugTrack` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `BugId` varchar(100) NOT NULL,\n"
                    + "  `BugName` varchar(150) NOT NULL,\n"
                    + "  `BugType` varchar(120) NOT NULL, \n"                    
                    + "  `BugLevel` varchar(150) NOT NULL,\n"  
                    + "  `Severity` varchar(150) NOT NULL,\n" 
                    + "  `ProjectName` varchar(150) NOT NULL,\n" 
                    + "  `TesterCode` varchar(150) NOT NULL,\n" 
                    + "  `RaisedOn` Date NOT NULL,\n" 
                    + "  `DeveloperCode` varchar(150) NOT NULL,\n" 
                    + "  `Status` varchar(150) NOT NULL,\n" 
                    + "  `Solution` varchar(150) NOT NULL,\n" 
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");
            pst.execute();
            
            
            //create table for Report....
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`Report` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `ReportId` varchar(100) NOT NULL,\n"
                    + "  `ReportName` varchar(150) NOT NULL,\n"
                    + "  `BugName` varchar(120) NOT NULL, \n"                    
                    + "  `Severity` varchar(150) NOT NULL,\n"                      
                    + "  `ProjectName` varchar(150) NOT NULL,\n" 
                    + "  `RaisedDate` Date NOT NULL,\n" 
                    + "  `Status` varchar(200) NOT NULL,\n" 
                    + "  `ResolvedDate` Date,\n"                     
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");
            pst.execute();
            System.out.println("Create Database Sucessfuly");

        } catch (SQLException ex) {
            System.err.println(ex);
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/view/Server.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Server Configur");
                stage.showAndWait();
            } catch (IOException ex1) {
                Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
}
