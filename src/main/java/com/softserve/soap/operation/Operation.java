package com.softserve.soap.operation;

import com.sforce.soap.enterprise.*;
import com.sforce.soap.enterprise.sobject.*;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;
import com.softserve.soap.config.Config;

import java.util.ArrayList;

public class Operation
{
    private EnterpriseConnection connection;
    private ConnectorConfig config;
    private LoginResult loginResult;

    public boolean logIn()
    {
        config = new ConnectorConfig();
        config.setUsername(Config.getConfig().getUserName());
        config.setPassword(Config.getConfig().getPassword());
        try
        {
            connection = Connector.newConnection(config);
            loginResult = connection.login(Config.getConfig().getUserName(), Config.getConfig().getPassword());
            System.out.println(loginResult.getSessionId());
            System.out.println(loginResult.getUserInfo());
        }
        catch (ConnectionException e1)
        {
            System.out.println("fail to login");
            return false;
        }
        printUserInfo(config);
        return true;
    }

    public boolean logout()
    {
        try
        {
            connection.logout();
            System.out.println("Logged out.");
        } catch (ConnectionException ce) {
            System.out.println("fail to logout");
            return false;
        }
        return true;
    }

    private void printUserInfo(ConnectorConfig config)
    {
        try
        {
            GetUserInfoResult userInfo = connection.getUserInfo();
            System.out.println("\nLogging in ...\n");
            System.out.println("UserID: " + userInfo.getUserId());
            System.out.println("User Full Name: " + userInfo.getUserFullName());
            System.out.println("User Email: " + userInfo.getUserEmail());
            System.out.println();
            System.out.println("SessionID: " + config.getSessionId());
            System.out.println("Auth End Point: " + config.getAuthEndpoint());
            System.out.println("Service End Point: " + config.getServiceEndpoint());
            System.out.println();
        }
        catch (ConnectionException ce)
        {
            ce.printStackTrace();
        }
    }

    public void getMerchandiseFields()
    {
        QueryResult qResult = null;
        String soqlQuery = "SELECT Name, Price__c, Quantity__c FROM Merchandise__c";
        try
        {
            qResult = connection.query(soqlQuery);
            SObject[] records = qResult.getRecords();
            System.out.println("Merchandise__c fields output:");
                for(int i=0;i<records.length;i++)
                {
                    Merchandise__c merchandise__c = (Merchandise__c) records[i];
                    System.out.println("Name" + " " + merchandise__c.getName());
                    System.out.println("Price" + " " +  merchandise__c.getPrice__c());
                    System.out.println("Quantity" + " " +  merchandise__c.getQuantity__c());
                }
            }
        catch (ConnectionException e)
        {
                e.printStackTrace();
        }
    }

    public void getInvoices(){
        QueryResult qResult = null;
        String soqlQuery = "SELECT Name, Invoice_Total__c, Status__c FROM Invoice__c";
        try
        {
            qResult = connection.query(soqlQuery);
            SObject[] records = qResult.getRecords();
            System.out.println("Invoice__c fields output:");
            for(int i=0;i<records.length;i++)
            {
                Invoice__c invoice__c = (Invoice__c) records[i];
                System.out.println("Name" + " " + invoice__c.getName());
                System.out.println("Status" + " " +  invoice__c.getStatus__c());
                System.out.println("Total" + " " +  invoice__c.getInvoice_Total__c());
            }
        }
        catch (ConnectionException e)
        {
            e.printStackTrace();
        }
    }


   /* public void getInvoices(){
        QueryResult qResult = null;
        String soqlQuery = "SELECT Name, Invoice_Total__c, Status__c FROM Invoice__c";
        try
        {
            qResult = connection.query(soqlQuery);
            SObject[] records = qResult.getRecords();
            System.out.println("Invoice__c fields output:");
            for(int i=0;i<records.length;i++)
            {
                Invoice__c invoice__c = (Invoice__c) records[i];
                System.out.println("Name" + " " + invoice__c.getName());
                System.out.println("Status" + " " +  invoice__c.getStatus__c());
                System.out.println("Total" + " " +  invoice__c.getInvoice_Total__c());
            }
        }
        catch (ConnectionException e)
        {
            e.printStackTrace();
        }
    }*/
}
