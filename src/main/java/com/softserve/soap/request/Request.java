package com.softserve.soap.request;

import com.sforce.soap.enterprise.*;
import com.sforce.soap.enterprise.sobject.*;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;
import com.softserve.soap.config.Config;

public class Request
{
    private EnterpriseConnection connection;
    private ConnectorConfig config;
    private LoginResult loginResult;

    public Request()
    {
        login();
    }

    public void login()
    {
        config = new ConnectorConfig();
        config.setUsername(Config.getConfig().getUserName());
        config.setPassword(Config.getConfig().getPassword());
        try {
            connection = Connector.newConnection(config);
            loginResult = connection.login(Config.getConfig().getUserName(), Config.getConfig().getPassword());
            System.out.println(loginResult.getSessionId());
            System.out.println(loginResult.getUserInfo());
        } catch (ConnectionException e1) {
            System.out.println("fail to login");
        }
        printUserInfo(config);
    }

    public void logout()
    {
        try
        {
            connection.logout();
            System.out.println("Logged out.");
        } catch (ConnectionException ce) {
            System.out.println("fail to logout");
        }
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

    public SObject[] getMerchandises()
    {
        QueryResult qResult = null;
        String soqlQuery = "SELECT Name, Price__c, Quantity__c FROM Merchandise__c";
        SObject[] records = null;
        try
        {
            qResult = connection.query(soqlQuery);
            records = qResult.getRecords();
        }
        catch (ConnectionException e)
        {
            e.printStackTrace();
        }
        return records;
    }

    public SObject[] getInvoices()
    {
        SObject[] records = null;
        QueryResult qResult = null;
        String soqlQuery = "SELECT Id,Name, Invoice_Total__c, Status__c FROM Invoice__c";
        try
        {
            qResult = connection.query(soqlQuery);
            records = qResult.getRecords();
        }
        catch (ConnectionException e)
        {
            e.printStackTrace();
        }
        return records;
    }

    public SObject[] getInv()
    {
        SObject[] records = null;
        QueryResult qResult = null;
        String soqlQuery = "SELECT Id,Name,(SELECT Name,Invoice__c,Line_Item_Total__c,Quantity__c,Unit_Price__c " +
                "FROM Line_Items__r) FROM Invoice__c";
        try
        {
            qResult = connection.query(soqlQuery);
            records = qResult.getRecords();
        }
        catch (ConnectionException e)
        {
            e.printStackTrace();
        }
        return records;
    }

    public SObject[] getLineItem(String id)
    {
        SObject[] records = null;
        QueryResult qResult = null;
        String soqlQuery = "SELECT Invoice__c,Line_Item_Total__c, Quantity__c, Unit_Price__c, " +
                " Name FROM Line_Item__c where Invoice__c='"+id+"'";
        try
        {
            qResult = connection.query(soqlQuery);
            records = qResult.getRecords();
        }
        catch (ConnectionException e)
        {
            e.printStackTrace();
        }
        return records;
    }

    public SObject[] getInvoicesWithLineItem(String id)
    {
        SObject[] records = null;
        QueryResult qResult = null;
        String soqlQuery = "SELECT Name,(SELECT Name,Invoice__c,Line_Item_Total__c,Quantity__c,Unit_Price__c" +
                " FROM Line_Items__r)FROM Invoice__c i WHERE i.id = '"+id+"'";
        try
        {
            qResult = connection.query(soqlQuery);
            records = qResult.getRecords();
        }
        catch (ConnectionException e)
        {
            e.printStackTrace();
        }
        return records;
    }

    public SObject[] getLineItem()
    {
        SObject[] records = null;
        QueryResult qResult = null;
        String soqlQuery = "SELECT Id, Invoice__c,Line_Item_Total__c, Quantity__c, Unit_Price__c,  Name FROM Line_Item__c ";
        try
        {
            qResult = connection.query(soqlQuery);
            records = qResult.getRecords();
        }
        catch (ConnectionException e)
        {
            e.printStackTrace();
        }
        return records;
    }
}
