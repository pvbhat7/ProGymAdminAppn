<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script>

    </script>
</head>
<body>
</br></br></br></br></br></br>
<div class="container d-flex justify-content-center align-items-center">

    <div class="card bg-transparent border-success" style="width: 20rem;height: 8rem">
        </br>
        <h5 align="center">${serverResponse}</h5>
        </br>
        <c:if test="${positive}">
            <div align="center" class="form-group mx-sm-3 mb-2">
                <a href="login"><button type="submit" class="btn btn-success btn-sm" >Proceed to Application</button></a>
            </div>
        </c:if>
        <c:if test="${negative}">
            <div align="center" class="form-group mx-sm-3 mb-2">
                <a href="handleLicenceValidation"><button type="submit" class="btn btn-primary btn-sm" >Try Again</button></a>
            </div>
        </c:if>

    </div>

</div>

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>