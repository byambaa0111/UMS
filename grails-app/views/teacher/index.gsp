<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <link rel="stylesheet" href="https://cdn.datatables.net/autofill/2.3.5/css/autoFill.dataTables.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/keytable/2.5.3/css/keyTable.dataTables.min.css">
        <script src="https://cdn.datatables.net/autofill/2.3.5/js/dataTables.autoFill.min.js"></script>
        <script src="https://cdn.datatables.net/keytable/2.5.3/js/dataTables.keyTable.min.js"></script>

        <g:set var="entityName" value="${message(code: 'teacher.label', default: 'Teacher')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
         <section class="section">
        <!--page-header open-->
        <div class="page-header">
            <h4 class="page-title"></h4>
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a class="text-light-color" href="${createLink(uri: '/')}">
                    <g:message code="default.home.label"/></a>
                </li>
                <li class="breadcrumb-item active" aria-current="page">
                    <g:link class="text-light-color" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link>
                </li>
                <li class="breadcrumb-item active" aria-current="page">
                    <g:link class="text-light-color" action="teachersChart"><g:message code="default.chart.label" args="[entityName]" default="Charts"/></g:link>
                </li>
            </ol>
        </div>
        <!--page-header closed-->
        <!--row open-->
        <div class="row">
             <div class="col-md-12 col-lg-4 col-xl-3">
                 <div class="card">
                     <div class="card-header">
                         <h4>Шүүлт хайлт:</h4>
                     </div>
                     <div class="card-body">
                         <g:form name="my_awesome_form" id="my_awesome_form" method="POST">
                                 <div class="form-group">
                                     <label class="form-label">Салбар сургууль</label>
                                     <g:select class="form-control select2 w-150" name="faculty" id="faculty" noSelection="['all':'Бүгд']"
                                               from="${com.ums.edu.uni.Faculty.list()}"
                                               optionKey="id"
                                               optionValue="facultyName"
                                     />
                                 </div>
                                 <div id="departmentDiv">
                                 </div>

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
                                 <label class="form-label">Ажиллах хэлбэр</label>
                                 <g:each in="${grails.util.Holders.config.teacher.jobType.list}" var="jobType" status="i">
                                     <label class="custom-switch">
                                         <input type="radio" name="jobType" id ="jobType" value="${jobType}" class="custom-switch-input"  onchange="reloadDataTable()">
                                         <span class="custom-switch-indicator"></span>
                                         <span class="custom-switch-description">${jobType}</span>
                                     </label>
                                 </g:each>
                                 <label class="custom-switch">
                                     <input type="radio" name="jobType" value="all" checked="true" onclick="reloadDataTable()" class="custom-switch-input">
                                     <span class="custom-switch-indicator"></span>
                                     <span class="custom-switch-description">Бүгд</span>
                                 </label>
                             </div>

                             <div class="form-group custom-switches-stacked">
                                 <label class="form-label">Боловсролын зэрэг</label>
                                 <g:each in="${grails.util.Holders.config.education.degree.list}" var="degree" status="i">
                                     <label class="custom-switch">
                                         <input type="radio" name="degree" id ="degree" value="${degree}" class="custom-switch-input"  onchange="reloadDataTable()">
                                         <span class="custom-switch-indicator"></span>
                                         <span class="custom-switch-description">${degree}</span>
                                     </label>
                                 </g:each>
                                 <label class="custom-switch">
                                     <input type="radio" name="degree" value="all" checked="true" onclick="reloadDataTable()" class="custom-switch-input">
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
                             %{--<div class="form-group overflow-hidden">
                                 <label>Салбар Сургууль</label>
                                 <select class="form-control select2 w-100" id="faculties" name="faculties" multiple="multiple" data-placeholder="Select a faculty">
                                    <g:each in="${com.ums.edu.uni.Faculty.list()}" var="faculty" status="i">
                                         <option value="${faculty?.id}">${faculty?.facultyName}</option>
                                    </g:each>
                                 </select>
                             </div>--}%
                             <a class="btn btn-info" href="#"onclick="reloadDataTable()" >Search</a>
                         </g:form>
                     </div>
                 </div>

             </div>
             <div class="col-md-12 col-lg-8 col-xl-9">
                 <div class="card">
                    <div class="card-header">
                        <h4><g:message code="default.list.label" args="[entityName]" /></h4>
                    </div>
                    <div class="card-body">
                                    <g:if test="${flash.message}">
                                        <div class="message" role="status">${flash.message}</div>
                                    </g:if>
                                    <f:table id="teacherTable"  collection="${teacherList}"  properties="['teacherCode','firstName','lastName','gender','register','phoneNumber']" maxProperties="8" except="['id','isActive','createDate','updateDate']"/>
                        <div id="bchart" class="col-md-12">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--row closed-->
    </section>
         <script type="application/javascript">
             var table
             var url = '/teacher/data_for_datatable';

             function getTableData(table) {
                 const dataArray = [],
                     countArray = [],
                     populationArray = [],
                     densityArray = [];

                 // loop table rows
                 table.rows({ search: "applied"}).every(function() {
                     const data = this.data();
                     countArray.push(data[0]);
                     //populationArray.push(parseInt(data[1].replace(/\,/g, "")));
                     //densityArray.push(parseInt(data[2].replace(/\,/g, "")));
                 });
                 // store all data in dataArray
                 dataArray.push(countArray, populationArray, densityArray);
                 //console.log(countArray.length)
                 return dataArray;
             }

             function reloadDataTable(){
                // getTableData(table);
                 table.ajax.reload();
             }

             $(document).ready(function () {
             table = $('table').DataTable({
                 "ajax": {
                     "url": url,
                     "type": "POST",
                     "data": function(d) {
                         var frm_data = $('#my_awesome_form').serializeArray();
                         var teacherType =[] ;
                         var faculties =[] ;
                         $.each(frm_data, function(key, val) {

                             if(val.name == "teacherType"){
                                 teacherType.push(val.value)
                             }else{
                                 d[val.name] = val.value;
                             }

                             if(val.name == "faculties"){
                                 faculties.push(val.value)
                             }else{
                                 d[val.name] = val.value;
                             }
                             d["teacherType"] = teacherType;
                             d["faculties"] = faculties;

                             //console.log('Server response', val);
                         });
                     }

                 },

                "lengthChange": true,
                "info":     true,
                "paging":         true,
                "responsive": true,

                dom: '<"top_panel"Bf>rt<"bottom_panel"p><"info_panel"li><"clear">',
                buttons : [ 'copy', 'excel', 'pdf', 'colvis' ,'print'],
                "processing": true,
                "serverSide": true,
                "columns": [
                    {
                        "data": "teacherCode",
                        "render": function (data, type, row, meta) {
                            return '<a href="/teacher/show/' + row.id + '">' + data + '</a>';
                        }
                    },
                    {"data": "firstName"},
                    {"data": "lastName"},
                    {"data": "gender"},
                    {"data": "register"},
                    {"data": "phoneNumber"}
                ]
            });
            table.on( 'xhr', function () {
               var totalParams = table.ajax.params();
            } );
        });
             function changeName()
             {
                 event.preventDefault();
                 $.ajax({
                     url:"<g:createLink url="[action:'index',controller:'teacher']" />",
                     dataType: "json",
                     type:"post",
                     data: {
                         'email':'byambajargal',
                     },
                     success: function() {
                         $( "#resultDiv" ).addClass( 'alert alert-info' ).append( 'Successfully saved event' )
                     },
                     error: function(xhr, status, error) {
                         $( "#resultDiv" ).addClass( 'alert alert-info' ).append(xhr.responseText);
                     }
                 });
             }
             /*===================left menu hide hiij baina================*/
             $(document).ready(function () {
                 /*left menu hid hiij baina*/
                 $("body").addClass("sidenav-toggled");
             });

             /*=============================*/

             $("#faculty").on('change',function (e){
                 var url ="/student/getDepartments"
                 // alert(this.value)
                 $.ajax({
                     type: "POST",
                     url: url,
                     data:  { 'facultyId': this.value  }, // serializes the form's elements.
                     success: function(data)
                     {
                         // alert(data); // show response from the php script.

                         if(data == 'all'){
                             $( "#departmentDiv" ).empty() ;
                         }else{
                             $( "#departmentDiv" ).html(data) ;
                         }
                     },
                     error: function(xhr, status, error) {
                         $( "#showList" ).addClass( 'alert alert-info' ).append(xhr.responseText);
                     }
                 });
             });

             // Create the chart with initial data
             var container = $('#bchart') //insertBefore(table.table().container());

             var chart = Highcharts.chart('bchart', {
                 chart: {
                     type: 'pie',
                 },
                 title: {
                     text: 'Students visual',
                 },
                 series: [
                     {
                         data: chartData(table),
                     },
                 ],
             });

             // On each draw, update the data in the chart
             table.on('draw', function () {
                 chart.series[0].setData(chartData(table));
             });
             function chartData(table) {
                 var counts = {};

                 // Count the number of entries for each position
                 table
                     .column(4, { search: 'applied' })
                     .data()
                     .each(function (val) {
                         if (counts[val]) {
                             counts[val] += 1;
                         } else {
                             counts[val] = 1;
                         }
                     });

                 // And map it to the format highcharts uses
                 return $.map(counts, function (val, key) {
                     return {
                         name: key,
                         y: val,
                     };
                 });
             }
             function reloadDataTable(){

                 table.ajax.reload();
                 console.log("ajax reload function")
             }

         </script>
    </body>
</html>