<%-- 
    Document   : viewcart
    Created on : Nov 6, 2020, 4:47:53 PM
    Author     : Admin
--%>

<%@page import="fu.dtos.OrderDetailDTO"%>
<%@page import="fu.dtos.CartDTO"%>
<%@page import="fu.dtos.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>
        <!--Bootsrap 4 CDN-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

        <!--Fontawesome CDN-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

    </head>
    <body>
        <%
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            if (user != null) {
        %>
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
                <a class="navbar-brand" href="#">Hello: <%= user.getFullName()%></a>
                <ul class="navbar-nav mr-auto"></ul>
                <form action="MainController" class="form-inline my-2 my-lg-0">
                    <input type="submit" value="Sign Out" name="btnAction"  class="form-control mr-sm-2">
                </form>
            </div>
        </nav>
        </div>
        <%
            CartDTO cart = (CartDTO) session.getAttribute("CART");
            if (cart != null && cart.getCart().size() != 0) {
        %>
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="#">Home</a></li>
                <li class="breadcrumb-item active" aria-current="page">View cart</li>
            </ol>
        </nav>
        <div class="container">
            <h1 class="text-center mt-3 mb-3">View Cart</h1>
            <table border="1" class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>Room ID</th>
                        <th>Price</th>
                        <th></th>

                    </tr>
                </thead>
                <tbody>
                <form action="MainController">           
                    <%
                        float totalPrice = 0;
                        for (OrderDetailDTO orderDetail : cart.getCart().values()) {
                            totalPrice += orderDetail.getPrice();
                    %>
                    <tr>
                        <td>
                            <%=orderDetail.getRoomID()%>                
                        </td>
                        <td><%=(int) orderDetail.getPrice()%></td>
                        <td> <a href="MainController?btnAction=Remove&txtRoomID=<%=orderDetail.getRoomID()%>" >remove</a> </td>
                    </tr>
                </form>
                <%
                    }
                %>
                </tbody>
            </table>
            <table border="0" class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>Check In</th>
                        <th>Check Out</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td> <input class="form-control" type="date" name="txtCheckIn" value="<%=cart.getCheckIn()%>" readonly="true"/> </td>
                        <td> <input class="form-control" type="date" name="txtCheckOut" value="<%=cart.getCheckOut()%>" readonly="true" /> </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>   
                            <div class="text-right">
                                <form action="MainController">
                                    <h3>Total: <%=(int) totalPrice%> </h3>

                                    <input class="btn btn-success" type="submit" value="Check Out" name="btnAction" />
                                    <a href="MainController?btnAction=Booking more" class="btn btn-primary">Booking more!</a>
                                </form>
                            </div></td>
                    </tr>
                </tbody>
            </table>

            <%
                String message = (String) request.getAttribute("MESSAGE");
                if (message != null) {
            %>
            <h1>
                <%=message%>
            </h1>
            <%
                }
            } else {
            %>
            <h1>you have not booked yet <br>
            <a href="MainController?btnAction=Booking more" class="btn btn-primary">Back To Booking!</a></h1>
            <%
                      }
                      
                }
            %>
        </div>
        <div class="mt-3">
            <img src="https://cdn1.tablethotels.com/media/ecs/global/email/assets/20200402_Zoom/TabletHotels_Public-Mirrored.jpg"  class="img img-fluid" width="100%"/>
        </div>
        <!-- Footer -->
        <footer class="bg-light text-center text-lg-start bg-dark text-white">
            <!-- Grid container -->
            <div class="container p-4">
                <!--Grid row-->
                <div class="row">
                    <!--Grid column-->
                    <div class="col-lg-6 col-md-12 mb-4 mb-md-0">
                        <h5 class="text-uppercase">Footer Content</h5>

                        <p>
                            Lorem ipsum dolor sit amet consectetur, adipisicing elit. Iste atque ea quis
                            molestias. Fugiat pariatur maxime quis culpa corporis vitae repudiandae aliquam
                            voluptatem veniam, est atque cumque eum delectus sint!
                        </p>
                    </div>
                    <!--Grid column-->

                    <!--Grid column-->
                    <div class="col-lg-3 col-md-6 mb-4 mb-md-0">
                        <h5 class="text-uppercase">Links</h5>

                        <ul class="list-unstyled mb-0">
                            <li>
                                <a href="#!" class="text-dark">Link 1</a>
                            </li>
                            <li>
                                <a href="#!" class="text-dark">Link 2</a>
                            </li>
                            <li>
                                <a href="#!" class="text-dark">Link 3</a>
                            </li>
                            <li>
                                <a href="#!" class="text-dark">Link 4</a>
                            </li>
                        </ul>
                    </div>

                    <div class="col-lg-3 col-md-6 mb-4 mb-md-0">
                        <h5 class="text-uppercase mb-0">Links</h5>

                        <ul class="list-unstyled">
                            <li>
                                <a href="#!" class="text-dark">Link 1</a>
                            </li>
                            <li>
                                <a href="#!" class="text-dark">Link 2</a>
                            </li>
                            <li>
                                <a href="#!" class="text-dark">Link 3</a>
                            </li>
                            <li>
                                <a href="#!" class="text-dark">Link 4</a>
                            </li>
                        </ul>
                    </div>
                   
                </div>
            </div>

            <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2);">
                © 2020 Copyright:
                <a href="https://mdbootstrap.com/">MDBootstrap.com</a>
            </div>
        </footer>
    </body>
</html>
