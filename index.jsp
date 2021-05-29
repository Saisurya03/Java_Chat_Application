<%-- 
    Document   : index
    Created on : 29 Apr, 2021, 7:00:48 PM
    Author     : 123
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users</title>
    </head>
    <body>
        <h1>Databases</h1>
        <form action="processTable">
            <table>
                <tr><td>Table Name:</td><td><input type="text" name="tableName"></td></tr>
                <tr><td>Column Names:</td><td><input type="text" name="column"></td></tr>
                <tr><td>Condition:</td><td><input type="text" name="con" placeholder="optional"></td></tr>
                <tr><td><input type="submit" value="submit"></td></tr>
            </table>
        </form>
    </body>
</html>
