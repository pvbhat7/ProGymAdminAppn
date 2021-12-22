<!--MONTHLY DATA TAB 2 START -->
<div class="product-tab-list tab-pane fade" id="view_monthly_data">
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="review-content-section">
                <!-- table -->
                <div class="row mg-b-15">
                    <div class="col-lg-12">
                        <div class="sparkline8-list">
                            <div class="sparkline8-graph">
                                <div class="static-table-list">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th hidden="true">Id</th>
                                            <th>Date</th>
                                            <th>Month</th>
                                            <th>Weight</th>
                                            <th>Height</th>
                                            <th>Neck</th>
                                            <th>Chest</th>
                                            <th>Weist</th>
                                            <th>Arm</th>
                                            <th>Thigh</th>
                                            <th>Upper Hips</th>
                                            <th>Hips</th>
                                            <th>Calf</th>
                                            <th>Ankle</th>
                                            <th>Delete</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${femaleAditionalDataList}" var="obj" varStatus="status">
                                            <tr>
                                                <td>
                                                    <c:out value="${obj.date}"/>
                                                </td>
                                                <td>
                                                    <c:out value="${obj.month}"/>
                                                </td>
                                                <td>
                                                    <c:out value="${obj.weight}"/>
                                                </td>
                                                <td>
                                                    <c:out value="${obj.height}"/>
                                                </td>
                                                <td>
                                                    <c:out value="${obj.neck}"/>
                                                </td>
                                                <td>
                                                    <c:out value="${obj.chest}"/>
                                                </td>
                                                <td>
                                                    <c:out value="${obj.weist}"/>
                                                </td>
                                                <td>
                                                    <c:out value="${obj.arm}"/>
                                                </td>
                                                <td>
                                                    <c:out value="${obj.thigh}"/>
                                                </td>
                                                <td>
                                                    <c:out value="${obj.upperHips}"/>
                                                </td>
                                                <td>
                                                    <c:out value="${obj.hips}"/>
                                                </td>
                                                <td>
                                                    <c:out value="${obj.calf}"/>
                                                </td>
                                                <td>
                                                    <c:out value="${obj.ankle}"/>
                                                </td>
                                                <td>
                                                    <a href="<c:url value='deleteFemaleClientAdditionalDetails?id=${obj.id}&gender=${clientObject.gender}&clientid=${clientObject.id}'/>">
                                                        <input class="btn btn-danger btn-xs" type="button" value="Delete" />
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- table end -->
            </div>
        </div>
    </div>
</div>
<!--MONTHLY DATA TAB 2 END -->

<!--ADD MONTHLY DATA TAB 3 START -->
<div class="product-tab-list tab-pane fade" id="add_monthly_data_female">
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <form:form action="submitFemaleAditionalDataForm" class="dropzone dropzone-custom needsclick add-professors" id="addTransaction" modelAttribute="femaleAditionalDataFormObject" method="post">
                <div class="review-content-section">
                    <div class="row">
                        <div class="col-lg-3">
                            <div class="form-group">
                                <form:label path="day">Select Day</form:label>
                                <form:select path="day" class="form-control" required="required">
                                    <form:option value="NONE" label="--- Select Day ---"/>
                                    <form:options items="${daysList}"/>
                                </form:select>
                            </div>
                        </div>
                        <div class="col-lg-3">
                            <div class="form-group">
                                <form:label path="month">Select Month</form:label>
                                <form:select path="month" class="form-control" required="required">
                                    <form:option value="NONE" label="--- Select Month ---"/>
                                    <form:options items="${monthsList}"/>
                                </form:select>
                            </div>
                        </div>
                        <div class="col-lg-3">
                            <div class="form-group">
                                <form:label path="year">Select Year</form:label>
                                <form:select path="year" class="form-control" required="required">
                                    <form:option value="NONE" label="--- Select Year ---"/>
                                    <form:options items="${yearsList}"/>
                                </form:select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-3">
                            <div class="form-group">
                                <form:input path="clientId" title="" name="clientId" type="hidden" class="form-control" placeholder="Enter Weight"></form:input>
                            </div>
                            <div class="form-group">
                                <form:input path="gender" title="" name="gender" type="hidden" class="form-control" placeholder="Enter Weight"></form:input>
                            </div>
                            <div class="form-group">
                                <form:label path="weight">Weight</form:label>
                                <form:input path="weight" title="" name="weight" type="number" class="form-control" placeholder="Enter Weight"></form:input>
                            </div>
                            <div class="form-group">
                                <form:label path="height">Height</form:label>
                                <form:input path="height" name="height" type="number" class="form-control" placeholder="Enter Height"></form:input>
                            </div>
                            <div class="form-group">
                                <form:label path="neck">Neck</form:label>
                                <form:input path="neck" name="neck" type="number" class="form-control" placeholder="Enter Neck"></form:input>
                            </div>
                            <div class="form-group">
                                <form:label path="chest">Chest</form:label>
                                <form:input path="chest" name="chest" type="number" class="form-control" placeholder="Enter Chest"></form:input>
                            </div>
                            <div class="form-group">
                                <form:label path="weist">Weist</form:label>
                                <form:input path="weist" name="weist" type="number" class="form-control" placeholder="Enter Weist"></form:input>
                            </div>
                            <div class="form-group">
                                <form:label path="arm">Arm</form:label>
                                <form:input path="arm" name="arm" type="number" class="form-control" placeholder="Enter Arm"></form:input>
                            </div>
                        </div>
                        <div class="col-lg-3">
                            <div class="form-group">
                                <form:label path="thigh">Thigh</form:label>
                                <form:input path="thigh" name="thigh" type="number" class="form-control" placeholder="Enter Theigh"></form:input>
                            </div>
                            <div class="form-group">
                                <form:label path="upperHips">Upper Hips</form:label>
                                <form:input path="upperHips" name="upperHips" type="number" class="form-control" placeholder="Enter Upper Hips"></form:input>
                            </div>
                            <div class="form-group">
                                <form:label path="hips">Hips</form:label>
                                <form:input path="hips" name="hips" type="number" class="form-control" placeholder="Enter Hips"></form:input>
                            </div>
                            <div class="form-group">
                                <form:label path="Calf">Calf</form:label>
                                <form:input path="calf" name="calf" type="number" class="form-control" placeholder="Enter Calf"></form:input>
                            </div>
                            <div class="form-group">
                                <form:label path="ankle">Ankle</form:label>
                                <form:input path="ankle" name="ankle" type="number" class="form-control" placeholder="Enter Ankle"></form:input>
                            </div>
                        </div>
                    </div>
                    </br>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="payment-adress mg-t-15">
                                <button type="submit" class="btn btn-primary waves-effect waves-light mg-b-15">Submit</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>
<!--ADD MONTHLY DATA TAB 3 END -->