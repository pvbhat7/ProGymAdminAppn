<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="profile-info-inner">
    <div class="profile-img" >
        <a id="uploadPhotoLink" href="#">
            <c:if test="${clientObject.photo == 'NA'}">
                <img id="displayPhoto" src="img/upload_photo.jpg">
            </c:if>
            <c:if test="${clientObject.photo != 'NA'}">
                <img id="displayPhoto" src="${clientObject.photo}">
            </c:if>
        </a>
    </div>
    <div class="profile-details-hr">
        <div class="row">
            <div class="" align="center">
                <div class="address-hr">
                    <h1>${clientObject.name}</h1>
                    </p>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-6 col-md-12 col-sm-12 col-xs-6">
                <div class="address-hr">
                    <p><b>Birth Date</b><br /> ${clientObject.birthDate}</p>
                </div>
            </div>
            <div class="col-lg-6 col-md-12 col-sm-12 col-xs-6">
                <div class="address-hr tb-sm-res-d-n dps-tb-ntn">
                    <c:if test="${clientObject.gender == 'male'}">
                        <p><b>Mobile</b><br /> ${clientObject.mobile}</p>
                    </c:if>
                    <c:if test="${clientObject.gender == 'female'}">
                        <c:if test="${sessionScope.maskMobile != null}">
                            <p><b>Mobile</b><br /> ${clientObject.mobile}</p>
                        </c:if>
                        <c:if test="${sessionScope.maskMobile == null}">
                            <p><b>Mobile</b><br /> ********** </p>
                            <button type="button" id="btnViewMobile" class="btn btn-primary btn-block btn-xs">
                                view mobile
                            </button>
                        </c:if>
                    </c:if>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-6 col-md-12 col-sm-12 col-xs-6">
                <div class="address-hr">
                    <p><b>Blood Group</b><br /> ${clientObject.bloodGroup}</p>
                </div>
            </div>
            <div class="col-lg-6 col-md-12 col-sm-12 col-xs-6">
                <div class="address-hr tb-sm-res-d-n dps-tb-ntn">
                    <%-- <p><b>Refer Points</b><br /> ${clientObject.referPoints}</p> --%>
                    <button type="button" id="btnreferral" class="btn btn-primary btn-block btn-xs">
                        Refer Points : ${clientObject.referPoints}
                    </button>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-6 col-md-12 col-sm-12 col-xs-6">
                <div class="address-hr">
                    <p><b>Email</b><br /> ${clientObject.email}</p>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-6 col-md-12 col-sm-12 col-xs-6">
            </div>
            <div class="col-lg-6 col-md-12 col-sm-12 col-xs-6">
                <button type="button" id="btnDeleteClientProfile" class="btn btn-danger btn-block btn-xs">
                    Delete profile
                </button>
                <a href="<c:url value='deleteClientProfile?gender=${clientObject.gender}&clientid=${clientObject.id}'/>">
                </a>
            </div>
        </div>
    </div>
</div>