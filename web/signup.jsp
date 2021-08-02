<%-- 
    Document   : signup
    Created on : Nov 4, 2020, 10:24:55 PM
    Author     : Admin
--%>

<%@page import="fu.dtos.UserErrorDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up Page</title>
    </head>
    <body>
        <%
            UserErrorDTO error= (UserErrorDTO)request.getAttribute("ERROR");
            if(error==null){
                error= new UserErrorDTO("", "", "", "", "", "", "", "");
            }
        %>
        <form action="MainController" method="POST">
            <table style="border: none">
                <tbody>
                    <tr>
                        <td>User ID:</td>
                        <td><input type="text" name="txtUserID" value="" /></td>
                        <td><font color="red"><%=error.getUserIDError() %></font></td>
                    </tr>
                    <tr>
                        <td>Full Name:</td>
                        <td><input type="text" name="txtFullName" value="" /></td>
                        <td><font color="red"><%=error.getFullNameError() %></font></td>
                    </tr>
                    <tr>
                        <td>Identity Card:</td>
                        <td><input type="number" name="txtIdentityCard" value="" /></td>
                        <td><font color="red"><%=error.getIdentityCardError() %></font></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="password" name="txtPassword" value="" /></td>
                        <td><font color="red"><%=error.getPasswordError() %></font></td>
                    </tr>
                    <tr>
                        <td>Confirm:</td>
                        <td><input type="password" name="txtConfirm" value="" /></td>
                        <td><font color="red"><%=error.getConfirmError() %></font></td>
                    </tr>
                    <tr>
                        <td>Email:</td>
                        <td><input type="email" name="txtEmail" value="" /></td>
                        <td><font color="red"><%=error.getEmailError() %></font></td>
                    </tr>
                    <tr>
                        <td>Phone:</td>
                        <td><input type="number" name="txtPhone" value="" /></td>
                        <td><font color="red"><%=error.getPhoneError() %></font></td>
                    </tr>
                    <tr>
                        <td>Address:</td>
                        <td><input type="text" name="txtAddress" value="" /></td>
                        <td><font color="red"><%=error.getAddressError() %></font></td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" value="Sign Up New" name="btnAction" />
            <input type="reset" value="Reset" />
        </form>
        <a href="login.jsp" target="_top">Back</a>
    </body>
</html>
