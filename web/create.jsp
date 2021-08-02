<%-- 
   
--%>

<%@page import="fu.dtos.TypeRoomDTO"%>
<%@page import="java.util.List"%>
<%@page import="fu.dtos.RoomErrorDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Room Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">


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
                <li class="breadcrumb-item active" aria-current="page">Create</li>
            </ol>
        </nav> 
        <div class="container">
            <%
                List<TypeRoomDTO> typeList = (List<TypeRoomDTO>) session.getAttribute("TYPES");
                RoomErrorDTO error = (RoomErrorDTO) request.getAttribute("ERROR");
                if (error == null) {
                    error = new RoomErrorDTO("", "", "", "", "");
                }
            %>
            <h1 class="text-center m-3">Create Room</h1>
            <form action="MainController">
                <table border="0" class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Room ID</th>
                            <th> <input class="form-control" type="text" name="txtRoomID" value="" /> </th>
                            <th> <font color="red"><%=error.getRoomIDError()%></font> </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Type Name</td>
                            <td>
                                <select name="cbxType" class="form-control">
                                    <%
                                        for (TypeRoomDTO type : typeList) {
                                    %>
                                    <option value="<%=type.getTypeID()%>" selected="" ><%=type.getTypeName()%></option>

                                    <%
                                        }
                                    %>
                                </select>
                            </td>
                            <th> <font color="red"><%=error.getTypeNameError()%></font> </th>
                        </tr>
                        <tr>
                            <td>Price</td>
                            <td> <input class="form-control" type="number" name="txtPrice" value="" min="1" max="1000000000"/> </td>
                            <td> <font color="red"><%=error.getPriceError()%> </td>
                        </tr>
                        <tr>
                            <td>Description</td>
                            <td> <textarea  class="form-control"  name="txtDescription"></textarea> </td>
                            <td> <font color="red"><%=error.getDescriptionError()%> </td>
                        </tr>
                        <tr>
                            <td>Max People</td>
                            <td> <input  class="form-control"  type="number" name="txtMaxPeople" value="" min="1" max="10"/> </td>
                            <td> <font color="red"><%=error.getMaxPeopleError()%> </td>
                        </tr>
                    </tbody>
                </table>
                <br>
                <div class="text-right mb-3">
                    <input class="btn btn-success" type="submit" value="Create" name="btnAction" />
                    <a class="btn btn-success" href="MainController?btnAction=ViewAD">Back</a>
                </div>
            </form>
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
