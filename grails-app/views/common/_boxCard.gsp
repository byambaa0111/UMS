%{--жижиг кард дотроо хувьтай --}%
%{--color #7673e6--}%
<div class="col-lg-6 col-xl-3 col-md-6 col-sm-6 col-12">
    <div class="card">
        <div class="card-body ">
            <h6 class="mb-2">${title}fds</h6>
            <div class="dash2 text-center">
                <div class="mb-0">
                    <input type="text" class="knob" data-thickness="0.2" data-angleArc="250" data-angleOffset="-125" value="${value}" data-width="120" data-height="120" data-fgColor="${value > 50 ? '#7673e6' : '#FF0000'}">
                </div>
                <div class="">
                    <small class="float-left text-muted">${objectName}</small>
                    <h3 class="float-right mb-0">${objectValue}</h3>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(function() {
        $(".knob").knob();
    });
</script>