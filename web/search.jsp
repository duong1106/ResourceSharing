<%-- 
   
--%>

<%@page import="fu.dtos.SearchErrorDTO"%>
<%@page import="fu.dtos.RoomDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.time.LocalDate"%>
<%@page import="fu.dtos.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--Bootsrap 4 CDN-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

        <!--Fontawesome CDN-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <title>Search Page</title>
    </head>
    <body>
        <%
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            if (user != null) {

                String checkIn = (String) session.getAttribute("CHECKIN");
                String checkOut = (String) session.getAttribute("CHECKOUT");
                String priceMax = (String) session.getAttribute("PRICEMAX");
                String priceMin = (String) session.getAttribute("PRICEMIN");
                String maxPeople = (String) session.getAttribute("MAXPEOPLE");
                if (checkIn == null) {
                    checkIn = java.time.LocalDate.now().toString();
                }
                if (checkOut == null) {
                    checkOut = java.time.LocalDate.now().plusDays(1).toString();
                }
                if (priceMin == null) {
                    priceMin = "1";
                }
                if (priceMax == null) {
                    priceMax = "1000000000";
                }
                if (maxPeople == null) {
                    maxPeople = "1";
                }
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
        <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img class="d-block w-100" src="https://d2e5ushqwiltxm.cloudfront.net/wp-content/uploads/sites/92/2019/11/20071929/0919-AJS-NOI-Hotel-des-Arts-SGN-1091-Web-1500x690.jpg" alt="First slide">
                </div>
                <div class="carousel-item">
                    <img class="d-block w-100" src="https://d2e5ushqwiltxm.cloudfront.net/wp-content/uploads/sites/92/2016/12/21080508/SuitesHUY_9791-1500x690.jpg" alt="Second slide">
                </div>
                <div class="carousel-item">
                    <img class="d-block w-100" src="https://d2e5ushqwiltxm.cloudfront.net/wp-content/uploads/sites/92/2017/08/13032850/Hotel-des-Arts-Saigon-Sky-Lounge3-1500x690.jpg" alt="Third slide">
                </div>
            </div>
            <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="#">Home</a></li>
                <li class="breadcrumb-item active" aria-current="page">Search</li>
            </ol>
        </nav>
        <div class="container">
            <h1 class="text-center mt-3 mb-3">Search</h1>
            <table style=" border: none" >
                <thead>
                    <tr>
                        <th> Check In </th>
                        <th> Check Out </th>
                        <th> Price Min </th>
                        <th> Price Max</th>
                        <th> Max People </th>
                    </tr>
                </thead>
                <tbody>
                <form action="MainController">               
                    <tr>
                        <td> <input class="form-control" type="date" name="txtCheckIn" value="<%=checkIn%>" min="<%= java.time.LocalDate.now()%>" /> </td>
                        <td> <input class="form-control" type="date" name="txtCheckOut" value="<%=checkOut%>" min="<%= java.time.LocalDate.now().plusDays(1)%>" /> </td>
                        <td> <input class="form-control" type="number" name="txtPriceMin" value="<%=priceMin%>" min="1" max="1000000000" /> </td>
                        <td> <input class="form-control" type="number" name="txtPriceMax" value="<%=priceMax%>" min="<%=priceMin%>" max="1000000000"/> </td>
                        <td> <input class="form-control" type="number" name="txtMaxPeople" value="<%=maxPeople%>" min="1" max="10" /> </td>
                        <td> <input class="btn btn-primary" type="submit" value="Search" name="btnAction"/> </td>
                        <td> <input class="btn btn-primary" type="submit" value="View Booking" name="btnAction" /> </td>
                    </tr>
                </form>
                <%
                    SearchErrorDTO error = (SearchErrorDTO) request.getAttribute("SEARCHERROR");
                    if (error != null) {
                %>
                <tr>
                    <th> <%=error.getCheckInError()%> </th>
                    <th> <%=error.getCheckOutError()%> </th>
                    <th> <%=error.getPriceMinError()%> </th>
                    <th> <%=error.getPriceMaxError()%></th>
                    <th> <%=error.getMaxPeopleError()%> </th>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>

            <%
                List<RoomDTO> list = (List<RoomDTO>) session.getAttribute("LISTROOMS");
                if (list != null && list.size() != 0) {
            %>   
            <%
                String message = (String) request.getAttribute("MESSAGE");
                if (message != null) {
            %>
            <h3 color="red" class="text-center mt-3 mb-3"><%=message%> </h3>
            <%
                }
            %>
            <div class="row mt-3">
                <%
                    for (RoomDTO room : list) {
                %>
                <div class="col-md-4">
                    <form action="MainController">    
                        <div class="card">
                            <img class="card-img-top" src="https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20046314-397f86761703259662aa6d5a62ad3dd4.jpeg?tr=q-40,c-at_max,w-740,h-500&_src=imagekit" alt="Card image cap">
                            <div class="card-body">
                                <small># <%=room.getRoomID()%></small>
                                <h5 class="card-title"><%=room.getTypeName()%></h5>
                                <span><%=room.getTypeName()%></span><br>
                                <span><%=(int) room.getPrice()%> VND</span><br>
                                <p><i class="fas fa-user-friends mr-2   "></i><%=room.getMaxPeople()%></p>
                                <p class="card-text"><%=room.getDescription()%></p>
                                <input class="btn btn-primary mt-2" type="submit" value="Booking Now" name="btnAction"/>
                            </div>
                        </div>
                        <input type="hidden" name="txtRoomID" value="<%=room.getRoomID()%>" />
                        <input type="hidden" name="txtTypeRoom" value=" <%=room.getTypeName()%>" />
                        <input type="hidden" name="txtPrice" value="<%=room.getPrice()%>" />
                        <input type="hidden" name="txtCheckIn" value="<%=checkIn%>" />
                        <input type="hidden" name="txtCheckOut" value="<%=checkOut%>" />
                        <input type="hidden" name="txtPriceMin" value="<%=priceMin%>" />
                        <input type="hidden" name="txtPriceMax" value="<%=priceMax%>" />
                        <input type="hidden" name="txtMaxPeople" value="<%=maxPeople%>" />  
                    </form>
                </div>
                <%
                    }
                %>
            </div>
            <%
                }

            %>
            <%                }
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
                    <!--Grid column-->

                    <!--Grid column-->
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
                    <!--Grid column-->
                </div>
                <!--Grid row-->
            </div>
            <!-- Grid container -->

            <!-- Copyright -->
            <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2);">
                © 2020 Copyright:
                <a href="https://mdbootstrap.com/">MDBootstrap.com</a>
            </div>
            <!-- Copyright -->
        </footer>
        <!-- Footer -->
    </body>
</html>
