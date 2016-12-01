<%-- 
    Document   : email
    Created on : 1 déc. 2016, 10:51:16
    Author     : huang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="POST" action="Controleur?action=emailSend">
    <label for="to">To:</label><input id="to" name="to" type="text"/><br/>
    <label for="subject">Subject:</label><input id="subject" name="subject" type="text"/><br/>
    <textarea name="body" cols="60" rows="15"></textarea><br/>
    <input type="submit" value="Send"/>
        </form>
    </body>
</html>
