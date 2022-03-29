<!--page-header open-->
<div class="page-header">
    <h4 class="page-title"></h4>
    <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="#" class="text-light-color">Home</a></li>
    </ol>
</div>
<!--page-header closed-->
<div class="row">
    <div class="col-md-10 col-sm-12">
        <div class="card">
            <div class="card-header">
                <h4>Actions</h4>
            </div>
            <div class="card-body">
                <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                    <g:link class="btn btn-app mt-2" controller="${c.logicalPropertyName}"><i class="fa fa-save"></i> ${c.name}</g:link>
                </g:each>
            </div>
        </div>
    </div>
</div>