<%-- 
    Document   : login
    Created on : Nov 8, 2020, 2:51:07 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="google-signin-scope" content="profile email">
        <meta name="google-signin-client_id" content="216569937196-k1rji1iujrqm1ah5iockl3h2047d63lq.apps.googleusercontent.com">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!--Bootsrap 4 CDN-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

        <!--Fontawesome CDN-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

        <!------ Include the above in your HEAD tag ---------->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <script src='https://www.google.com/recaptcha/api.js'></script>
        <title>login Page</title>
    </head>
    <style scope>
        /* Made with love by Mutiullah Samim*/

        html,body{
            background-image: url('http://getwallpapers.com/wallpaper/full/a/5/d/544750.jpg');
            background-size: cover;
            background-repeat: no-repeat;
            height: 100%;
            font-family: 'Numans', sans-serif;
        }

        .container{
            height: 100%;
            align-content: center;
        }

        .card{
            height: auto;
            margin-top: auto;
            margin-bottom: auto;
            width: 400px;
            background-color: rgba(0,0,0,0.5) !important;
        }

        .social_icon span{
            font-size: 60px;
            margin-left: 10px;
            color: #FFC312;
        }

        .social_icon span:hover{
            color: white;
            cursor: pointer;
        }

        .card-header h3{
            color: white;
        }

        .social_icon{
            position: absolute;
            right: 20px;
            top: -45px;
        }

        .input-group-prepend span{
            width: 50px;
            background-color: #FFC312;
            color: black;
            border:0 !important;
        }

        input:focus{
            outline: 0 0 0 0  !important;
            box-shadow: 0 0 0 0 !important;

        }

        .remember{
            color: white;
        }

        .remember input
        {
            width: 20px;
            height: 20px;
            margin-left: 15px;
            margin-right: 5px;
        }

        .login_btn{
            color: black;
            background-color: #FFC312;
            width: 100px;
        }

        .login_btn:hover{
            color: black;
            background-color: white;
        }

        .links{
            color: white;
        }

        .links a{
            margin-left: 4px;
        }
    </style>

    <%
        String errorID = (String) request.getAttribute("ERRORID");
        String errorPs = (String) request.getAttribute("ERRORPS");
        String error = (String) request.getAttribute("ERRORACC");
        String errorCaptcha = (String) request.getAttribute("RECERROR");
        if (errorID == null) {
            errorID = "";
        }
        if (errorPs == null) {
            errorPs = "";
        }
        if (error == null) {
            error = "";
        }
        if (errorCaptcha == null) {
            errorCaptcha = "";
        }
    %>
    <body>
        <div class="container">
            <div class="d-flex justify-content-center h-100">
                <div class="card">
                    <div class="card-header">
                        <h3>Sign In</h3>
                        <div class="d-flex justify-content-end social_icon">
                            <span><i class="fab fa-facebook-square"></i></span>
                            <span><i class="fab fa-google-plus-square"></i></span>
                            <span><i class="fab fa-twitter-square"></i></span>
                        </div>
                    </div>
                    <div class="card-body">
                        <div class="text-danger">
                            <span ><%=error%></span><br>
                            <span><%=errorID%></span><br>
                            <span><%=errorPs%></span><br>
                            <span><%=errorCaptcha%></span>
                        </div>
                         <!-- action : controller muoons tra ve-->
                        <form action="MainController" method="POST">                    
                            <div class="input-group form-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fas fa-user"></i></span>
                                </div>
                                <input type="text" class="form-control" name="txtUserID" placeholder="username">
                            </div>
                            <div class="input-group form-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fas fa-key"></i></span>
                                </div>
                                <input type="password" class="form-control" name="txtPassword" placeholder="password">
                            </div>
                            <div class="g-recaptcha d-flex j"
                                 data-sitekey="6LcXeuAZAAAAAD69Aaft7x3a2Icf49qYqf8IptGL"></div>

                            <div class="row align-items-center remember mb-3">
                                <input type="checkbox">Remember Me
                            </div>
                            <div class="form-group d-flex justify-content-center">
                                <input type="reset" value="Reset" class="btn  float-right login_btn  mr-2">
                                <input  type="submit" value="Sign Up" name="btnAction" class="btn float-right login_btn mr-2">
                                <input  type="submit" name="btnAction" value="Login" class="btn float-right login_btn mr-2">
                            </div>
                        </form>
                    </div>
                    <div class="card-footer">
                        <div class="d-flex justify-content-center links">
                            Don't have an account?<a href="#">Sign Up</a>
                        </div>
                        <div class="d-flex justify-content-center">
                            <a href="#">Forgot your password?</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            function onSignIn(googleUser) {
                // Useful data for your client-side scripts:
                var auth2 = gapi.auth2.getAuthInstance();
                auth2.signOut().then(function () {
                    console.log('User signed out.');
                });
                var profile = googleUser.getBasicProfile();
                window.location.href = 'MainController?btnAction=LoginGG&txtUserID=' + profile.getId() + '&txtEmail=' + profile.getEmail() + '&txtFullName=' + profile.getName();
            }
        </script>
    </body>
</html>
