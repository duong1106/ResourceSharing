<%-- 
   
--%>

<%@page import="fu.dtos.TypeRoomDTO"%>
<%@page import="java.util.List"%>
<%@page import="fu.dtos.RoomDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
                <a class="navbar-brand" href="#">Hello: ${sessionScope.LOGIN_USER.fullName}</a>
                <ul class="navbar-nav mr-auto"></ul>
                <form action="MainController" class="form-inline my-2 my-lg-0">
                    <input type="submit" value="Sign Out" name="btnAction"  class="form-control mr-sm-2">
                </form>
            </div>
        </nav>
        <div>
            <img src="https://media-exp1.licdn.com/dms/image/C5616AQEc3Flv9Lw5xQ/profile-displaybackgroundimage-shrink_200_800/0/1517761080684?e=1620864000&v=beta&t=zF6ET_yst6435WECtWpr5yScwKmqDRzeJvwfD9osY3U" class="img img-fluid" width="100%" />
        </div>      
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="#">Home</a></li>
                <li class="breadcrumb-item active" aria-current="page">Admin</li>
            </ol>
        </nav> 
        <div class="container">
            <div class="m-3">
                <h1 class="text-center">Table Room</h1>
            </div>
            <div class="m-3">
                <a class="btn btn-success" href="MainController?btnAction=CreateRooms">Create Room</a>
            </div>
            <%
                List<RoomDTO> roomList = (List<RoomDTO>) session.getAttribute("ROOMS");
                List<TypeRoomDTO> typeList = (List<TypeRoomDTO>) session.getAttribute("TYPES");
                if (roomList != null && typeList != null) {
                    String error = "";
                    String priceError = (String) request.getAttribute("ERRORPRICE");
                    String maxPError = (String) request.getAttribute("ERRORMP");
                    String desError = (String) request.getAttribute("ERRORDES");
                    String deleteError = (String) request.getAttribute("ERRORDELETE");
                    String update = (String) request.getAttribute("UPDATE");
                    if (deleteError != null) {
                        error += deleteError;
                    }
                    if (priceError != null) {
                        error += priceError;
                    }
                    if (maxPError != null) {
                        error += maxPError;
                    }
                    if (desError != null) {
                        error += desError;
                    }

                    if (!error.isEmpty()) {
            %>
            <div class="alert alert-danger" role="alert">
                <%=error%>
            </div>
            <%
                }
                if (update == null) {
                    update = "";
                } else {
            %>
            <div class="alert alert-success" role="alert">
                <%=update%>
            </div>
            <%
                }
            %>

            <table class="table table-striped table-hover table-bordered">
                <thead>
                    <tr>
                        <th>Room ID</th>
                        <th>Type Name</th>
                        <th>Price</th>
                        <th>Description</th>
                        <th>Max People</th>
                        <th>Deleted</th>
                        <th colspan="2" class="text-center">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (RoomDTO room : roomList) {

                    %>
                <form action="MainController">        
                    <tr>
                        <td><%=room.getRoomID()%></td>
                    <input type="hidden" name="txtRoomID" value="<%=room.getRoomID()%>">
                    <td>
                        <div class="form-group">
                            <select class="form-control" name="cbxType" >

                                <%
                                    for (TypeRoomDTO type : typeList) {
                                        if (type.getTypeName().equals(room.getTypeName())) {
                                %>
                                <option value="<%=type.getTypeID()%>" selected="" ><%=type.getTypeName()%></option>

                                <%
                                } else {
                                %>
                                <option value="<%=type.getTypeID()%>"><%=type.getTypeName()%></option>
                                <%
                                        }
                                    }
                                %>
                            </select>
                        </div>
                    </td>
                    <td> 

                        <div class="row">
                            <div class="col-sm-10">
                                <input class="form-control" type="number" name="txtPrice" value="<%=(int) room.getPrice()%>" size="100%" max="100000000"/> 
                            </div>
                            <div class="col-sm-2 text-center"><p>VND</p></div>
                        </div>
                    </td>
                    <td width= "500px;"> <textarea  class="form-control" name="txtDescription" rows="9" cols="70"> <%=room.getDescription()%></textarea> </td>
                    <td> <input type="number"  class="form-control" name="txtMaxPeople" value="<%=room.getMaxPeople()%>" min="1" max="10"/> </td>
                    <td class="text-center">
                        <%
                            if (room.isIsDelete()) {
                        %>
                        <input type="checkbox" name="txtIsDelete" checked="" /> 
                        <%
                        } else {
                        %>
                        <input type="checkbox" name="txtIsDelete"  /> 
                        <%
                            }
                        %>
                    </td>
                    <td> <input type="submit" class="btn btn-success" value="Update" name="btnAction" /> </td>
                    <td> <input type="submit" class="btn btn-danger" value="Delete" name="btnAction" /> </td>
                    </tr>
                </form>
                <%
                    }
                %>
                </tbody>
                <tfooter>
                    <tr>
                        <th>Room ID</th>
                        <th>Type Name</th>
                        <th>Price</th>
                        <th>Description</th>
                        <th>Max People</th>
                        <th>Deleted</th>
                        <th colspan="2" class="text-center">Action</th>
                    </tr>
                </tfooter>
            </table>

            <%
                }
            %>

        </div>
        
        <footer class="bg-light text-center text-lg-start bg-dark text-white">
            <div class="container p-4">
                <div class="row">
                    <div class="col-lg-6 col-md-12 mb-4 mb-md-0">
                        <h5 class="text-uppercase">Footer Content</h5>

                        <p>
                            Lorem ipsum dolor sit amet consectetur, adipisicing elit. Iste atque ea quis
                            molestias. Fugiat pariatur maxime quis culpa corporis vitae repudiandae aliquam
                            voluptatem veniam, est atque cumque eum delectus sint!
                        </p>
                    </div>

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
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>
