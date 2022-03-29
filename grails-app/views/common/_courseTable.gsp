<div class="card">
    <div class="card-header">
        <h4>Course:</h4>
    </div>
    <div class="card-body">

            %{--<f:table properties="['courseCode','courseNameMN','totalCredit','courseType']" collection="${courseList}" template="tableCourse" except="['id','isActive']"/>--}%

            <div class="pagination">
                <g:paginate total="${courseTypeCount ?: 0}" />
            </div>
    </div>
</div>
<script>
    var url = '/course/data_for_datatable';
    var table;
    $(document).ready(function () {
        table = $('table').DataTable({
            "ajax": {
                url:url,
                "type": "POST",
                "data":function (d){
                    var frm_data = $('#filterForm').serializeArray();
                    var departments =[] ;
                    $.each(frm_data, function(key, val) {
                        if(val.name == "departments"){
                            departments.push(val.value)
                        }else{
                            d[val.name] = val.value;
                        }
                        d["departments"] = departments;
                        console.log('Server response', val.name+"--"+val.value);
                    });
                },
            },

            "lengthChange": true,
            "info"        :  true,
            "paging"      :true,
            "responsive"  : true,
            dom: '<"top_panel"Bf>rt<"bottom_panel"lip><"info_panel"><"clear">',
            buttons: ['copy', 'excel', 'pdf',  'print',
                {
                    extend: 'colvis',
                    postfixButtons: [ 'colvisRestore' ]
                }
            ],
            "processing": true,
            "serverSide": true,
            "columns": [
                {
                    "data": "courseCode",
                    "render": function (data, type, row, meta) {
                        return '<a href="/course/show/' + row.id + '">' + data + '</a>';
                    }
                },
                {"data": "courseNameMN"},
                {"data": "totalCredit"},
                {"data": "courseType"},
                {"data": "semesterType"}
            ]

        });


    });
    function reloadDataTable(){
        table.ajax.reload();
        console.log("ajax reload function")
    }
</script>