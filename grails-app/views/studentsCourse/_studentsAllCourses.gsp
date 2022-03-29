<%@ page import="helper.FunctionHelper; java.text.DecimalFormat; com.ums.edu.exams.Exam" %>
<h2 align="center">
    <p>Монголын Үндэсний Их Сургууль</p>
</h2>
<table class="table table-bordered border-t0 key-buttons font-weight-light text-nowrap " >
    <tr>
        <td>Мэргэжил : ${studentsCourseList[0]?.programmePlan} </td>
        <td>Жил : ${studentsCourseList[0]?.year}  </td>
    </tr>
    <tr>
        <td>Нийт сонгосон хичээл:${courses.size()}</td>
        <td>Дүнгийн хуудас</td>
    </tr>
</table>
<%
    def studentsCourseListGrouped = studentsCourseList.groupBy { it.student };
    helper.FunctionHelper helper = new FunctionHelper()
%>
<table id="example"  class="table table-bordered border-t0 key-buttons text-nowrap w-100"  >
    <thead>
        <tr>
        <td>#</td>
        <td>Код/Овог/Нэр</td>
       <g:each in="${courses}" status="i" var="c">
           <td>${c.courseCode}</td>
       </g:each>
        <td>Голч оноо</td>
        <td>Голч дүн</td>
    </tr>
    </thead>

    <tbody>
    <tr class="text-blue">
        <td>#</td>
        <td>Кредит</td>
        <g:each in="${courses}" status="i" var="c">
            <td>${c.totalCredit}</td>
        </g:each>
        <td>Голч оноо</td>
        <td>Голч дүн</td>
    </tr>
    <g:each in="${studentsCourseListGrouped}" var="bean" status="i">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}" >
            <td>${i+1}</td>
            <td>${bean.getKey()}</td>
            <%
                float GPA = 0;
                float sumOfCredits = 0;
                def studentsMapByCourse = bean?.getValue()?.groupBy {it.course }
            %>
            <g:each in="${courses}" status="j" var="grade">
                <%
                    def c = new ArrayList<>();
                  //  println("[===============--2]-"+studentsMapByCourse[grade])
                    if (studentsMapByCourse[grade]) {
                        c.addAll(studentsMapByCourse[grade])
                        GPA = GPA + c[0]?.totalGrade * c[0]?.course?.totalCredit;
                        sumOfCredits = sumOfCredits + c[0]?.course?.totalCredit;
                    }
                    /*Оюутныг хичээлийн дүнгийн хамт хэвлэлж байна*/
                %>
                <td>${c[0]?.totalGrade}</td>
            </g:each>
            <td> ${helper.getCalcGPA((GPA / sumOfCredits).shortValue())}</td>
            <td>${new java.text.DecimalFormat("0.0").format(GPA / sumOfCredits)}</td>
        </tr>
    </g:each>
    </tbody>
</table>
<p>
    Нийт оюүтан : ${studentsCourseListGrouped?.size()} <br>
</p>
<div id="lchart" class="col-md-12 col-sm-12">
</div>

<script>
    $(document).ready(function() {
        /*data table uusgej baina*/
        var table = $('#example').DataTable({
            dom: '<"top_panel"B>rt<"bottom_panel"lip><"info_panel"><"clear">',
            buttons: ['copy', 'excel',
                {
                    text: 'Chart',
                    action: function (e, dt, node, config) {
                        alert();
                        // disable button
                    },
                },
                {
                    extend: 'colvis',
                    postfixButtons: [ 'colvisRestore' ]
                }
            ]
        });
        var lchart = Highcharts.chart('lchart', {
            chart: {
                type: 'line'
            },
            title: {
                text: 'Сонгосон хичээлүүд дээрхи оютнуудын дүн'
            },
            subtitle: {
                text: 'Дүн хувиар'
            },
            xAxis: {
                // categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
            },
            yAxis: {
                title: {
                    text: 'Дүн хувиар'
                }
            },
            plotOptions: {
                line: {
                    dataLabels: {
                        enabled: true
                    },
                    enableMouseTracking: true
                }
            },
            series:chartData(table)
        });
        console.log(chartData(table,1))
    });
    function chartData(table) {
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
            if(index > 1 ){
                itemsMap[table.column(index).header().textContent] = items.sort((a, b) => a - b)
            }

        })
        // And map it to the format highcharts uses
       /* itemsMap.delete("#")
        itemsMap.delete("Голч оноо")*/

        return $.map(itemsMap, function (val, key) {
            return {
                name: key,
                data: val,
            };
        });

    }
</script>

