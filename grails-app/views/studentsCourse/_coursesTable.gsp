%{-- Хайг гэсэн довчийг дарсан тохиолдолд гарч ирнэ энэ хэсэгт тухаин хайлтанд илэрсэн оютнууд түүнтэй холбоотой графикууд гарч ирнэ--}%
<%@ page import="com.ums.edu.exams.Exam" %>
%{--<f:table id="studentsCourseTable" properties="['Student','course','examGrades','totalGrade','mark','student.groupOf']" collection="${studentsCourseList}" template="studentsCourse" except="['id','isActive']"/>--}%

<div class="table-responsive">
    <table id="example"  class="table table-bordered border-t0 key-buttons text-nowrap w-100"  >
        <thead>
        <tr class="text-nowrap w-100" style="white-space: pre-wrap">
            <td>Student</td>
            <td>GradesTeacher</td>
            <td>examGrades</td>
            <td>totalGrade</td>
            <td>mark</td>
            <td>groupOf</td>
            <td>isLooked</td>
            <g:each in="${examTypes}" status="i" var="examTypeInstance">
                <th>${examTypeInstance.percent }</th>
            </g:each>
        </tr>
        </thead>
        <tbody>
        <g:each in="${studentsCourseList}" var="bean" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}" >
                <td>${bean.student.studentCode}</td>
                <td>${bean.gradeFromTeacher}</td>
                <td>${bean.examGrade}</td>
                <td>${bean.totalGrade}</td>
                <td>${bean.mark}</td>
                <td>${bean.student?.groupOf}</td>
                <td>${bean.isLooked}</td>
                <g:each in="${examTypes}" status="j" var="examTypeInstance">
                    <td>
                        <g:if test="${bean.isLooked}">
                            <g:textField name="examGradeFromTeacher" readonly="${!bean.isLooked}"
                                         onblur="saveGrade(${bean.id},${examTypeInstance.id},this.value)"
                                         style="width: 30px" tabindex="${i+1}"
                                         value="${com.ums.edu.exams.Exam.findByStudentCourseAndExamType(bean,examTypeInstance)?.percent}"
                                         maxlength="3" lang="2" onfocus="this.style.color = '#48802C';this.select();">
                            </g:textField>
                        </g:if>
                    </td>
                </g:each>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>

<div class="row">
    <div id="bchart" class="col-md-6 col-sm-12">
    </div>
    <div id="lchart" class="col-md-6 col-sm-12">
    </div>
</div>


<script>
    $(document).ready(function() {
        let table = $('#example').DataTable({
            dom: '<"top_panel"Bf>rt<"bottom_panel"lip><"info_panel"><"clear">',
            buttons: [
                {
                    extend: 'print',
                    exportOptions: {
                        columns: [ 0,1,5,6 ]
                    },
                    message: 'Шалгалтын хуудас',
                    title:"Монголын Үндэсний Их Сургууль",
                    customize: function ( win ) {
                        $(win.document.body).css( 'font-size', '10pt' ).prepend( '<asset:image  src="apple-touch-icon-retina.png"  style="position:absolute; top:0; left:0;" />' );
                        $(win.document.body).find( 'table' ).addClass( 'compact' ) .css( 'font-size', 'inherit' );
                        $(win.document.body).find( 'table' ).before("<h2 align='left'><p>Монголын </p></h2>")

                    }
                },
                {
                    text: 'Chart',
                    action: function ( e, dt, node, config ) {

                        this.disable(); // disable button
                    },
                }
            ]
        });

        var bchart = Highcharts.chart('bchart', {
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie'
            },
            title: {
                text: 'Students grades',
            },
            accessibility: {
                point: {
                    valueSuffix: '%'
                }
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                    }
                }
            },
            series: [
                {
                    data: chartData(table,4),
                },
            ],
        });

        // On each draw, update the data in the chart
        table.on('draw', function () {
            bchart.series[0].setData(chartData(table,4));
            //console.log("Hello word!"+JSON.stringify(chartData(table,4)));
        });

        var lchart = Highcharts.chart('lchart', {
            chart: {
                type: 'line'
            },
            title: {
                text: ' Суралцагсдын дүнгийн тархалт'
            },
            subtitle: {
                text: 'Real time'
            },
            xAxis: {
               // categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
            },
            yAxis: {
                title: {
                    text: 'Оноо'
                }
            },
            plotOptions: {
                line: {
                    dataLabels: {
                        enabled: true
                    },
                    enableMouseTracking: false
                }
            },
            series: chartDataLine(table,2)
        });
    });
    function chartData(table,col) {
        var counts = {};
        // Count the number of entries for each position
        table
            .column(col, { search: 'applied' })
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
    function chartDataLine(table,col) {
        var counts = {};
        let itemsMap = {}
        let items    = []
        // Count the number of entries for each position
        var columns = table.columns();
        table.columns().every(function(index) {
            // console.log(table.column(index).header().textContent);
            let items    = []
            table
                .column(index, { search: 'applied' })
                .data()
                .each(function (val) {

                    if(val != ""){

                        items.push(parseFloat(val))

                    }else{
                        items.push(0)
                    }

                });
            if(index == 1 ){
                itemsMap[table.column(index).header().textContent] = items
            }
            if(index == 2 ){
                itemsMap[table.column(index).header().textContent] = items
            }
            if(index == 3 ){
                itemsMap[table.column(index).header().textContent] = items
            }

        })
        // And map it to the format highcharts uses
        /* itemsMap.delete("#")
         itemsMap.delete("Голч оноо")*/

        console.log("item"+JSON.stringify(itemsMap));

        return $.map(itemsMap, function (val, key) {
            return {
                name: key,
                data: val,
            };
        });
    }
    function saveGrade(studentCourseId,examTypeId,gradeFromTeacher) {
         url="/studentsCourse/insertExamGradeFromTeacher"
        $.ajax({
            type: "POST",
            url: url,
            data: {scId:studentCourseId,etId:examTypeId,gt:gradeFromTeacher}, // serializes the form's elements.
            success: function (response) {
                $("#showCourseDiv").html(response)
                toastr.info("ok",response)

            },
            error: function (xhr, status, error) {
                $("#showList").addClass('alert alert-info').append(xhr.responseText);
            }
        });
    }
</script>