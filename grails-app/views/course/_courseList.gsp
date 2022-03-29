<div class="card">
    <div class="card-header">
        <h4>Course:</h4>
    </div>
    <div class="card-body">

        <f:table properties="['courseCode','courseNameMN','totalCredit','courseType']" collection="${courseList}" except="['id','isActive']"/>
        <div class="pagination">
            <g:paginate total="${courseTypeCount ?: 0}" />
        </div>
    </div>
</div>