<div class="col-xl-6 col-lg-12 col-md-12">
    <div class="card">
        <div class="card-header">
            <h4>Badges TreeView</h4>
        </div>
        <div class="card-body ">
            <div class="tree mb-0">
                <ul class="mb-0">
                    <g:each in="${com.ums.edu.uni.Faculty.list()}" var="faculty">
                        <li >
                            <span  title="Expand this branch"><i class="ti-calendar icon-plus-sign"></i> ${faculty.facultyName}</span>
                            <ul>
                                <g:each in="${faculty?.departments}" var="department">
                                    <li >
                                        <span class="badge badge-success" title="Expand this branch"> ${department.departmentNameMN}</span>
                                        <ul>
                                            <g:each in="${department?.programmes}" var="programme">
                                                <li name="treeRoot">
                                                    <span><a href=""> ${programme.programmeMN}</a></span>
                                                </li>
                                            </g:each>
                                        </ul>
                                    </li>
                                </g:each>
                            </ul>
                        </li>
                    </g:each>
                </ul>
            </div>
        </div>
    </div>
</div>