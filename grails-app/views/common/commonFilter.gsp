<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 11/14/2020
  Time: 3:20 PM
--%>

<div class="col-md-12 col-lg-4 col-xl-3">
    <div class="card">
        <div class="card-header">
            <h4>Шүүлт хайлт:</h4>
        </div>
        <div class="card-body">
            <g:form name="filterForm" id="filterForm" method="POST">
                <div class="form-group custom-switches-stacked">
                    <label class="form-label">Хүйс</label>
                    <g:each in="${grails.util.Holders.config.person.gender.list}" var="gender" status="i">
                        <label class="custom-switch">
                            <input type="radio" name="gender" id ="gender" value="${gender}" class="custom-switch-input"  onchange="reloadDataTable()">
                            <span class="custom-switch-indicator"></span>
                            <span class="custom-switch-description">${gender}</span>
                        </label>
                    </g:each>
                    <label class="custom-switch">
                        <input type="radio" name="gender" value="all" checked="true" onclick="reloadDataTable()" class="custom-switch-input">
                        <span class="custom-switch-indicator"></span>
                        <span class="custom-switch-description">Бүгд</span>
                    </label>
                </div>

                <div class="form-group custom-switches-stacked">
                    <g:each in="${grails.util.Holders.config.teacher.teacherType.list}" var="teacherType" status="i">
                        <div class="form-group">
                            <input type="checkbox" id="teacherType" name="teacherType" onchange="reloadDataTable()" value="${teacherType}" class="flat-blue" checked>
                            <label for="teacherType">${teacherType}</label>
                        </div>
                    </g:each>
                </div>
                <div class="form-group overflow-hidden">
                    <label>Салбар Сургууль</label>
                    <select class="form-control select2 w-100" id="faculties" name="faculties" multiple="multiple" data-placeholder="Select a faculty">
                        <g:each in="${com.ums.edu.uni.Faculty.list()}" var="faculty" status="i">
                            <option value="${faculty?.id}">${faculty?.facultyName}</option>
                        </g:each>
                    </select>
                </div>

                <a class="btn btn-info" href="#"onclick="reloadDataTable()" >Search</a>


            </g:form>
        </div>
    </div>

</div>