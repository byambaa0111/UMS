<div class="col-lg-6 col-xl-3 col-md-6 col-sm-6 col-12">
    <div class="card bg-primary">
        <div class="card-body">
            <div class="card-order">
                <h6 class="mb-2">${title}</h6>
                <h2 class="text-right"><i class="fa fa-calculator mt-2 float-left"></i><span>${value}</span></h2>
                <p><br/></p>
                <g:each in="${objectName.keySet().sort()}" var="tc" status="i">
                    <p class="mb-0">${tc} - Семтеср<span class="float-right">${objectName.get(tc)} кредит</span></p>
                </g:each>
                <p>${body}</p>
            </div>
        </div>
    </div>
</div>
