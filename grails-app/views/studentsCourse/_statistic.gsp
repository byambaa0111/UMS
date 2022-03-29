<%@ page import="helper.FunctionHelper; java.text.DecimalFormat; com.ums.edu.exams.Exam" %>
<h2 align="center">
    <p>Монголын Үндэсний Их Сургууль</p>
</h2>
   <table class="table table-bordered border-t0 key-buttons font-weight-light text-nowrap ">
        <tr>
            <td>Мэргэжил: ${studentsCourseList[0]?.programmePlan}</td>
            <td>Жил: ${studentsCourseList[0]?.year}</td>
        </tr>
        <tr>
            <td>Амжилт чанарын үзүүлэлт</td>
            <td></td>
        </tr>
    </table>
<%
    def studentsCourseListGrouped = studentsCourseList.groupBy { it.student };
    helper.FunctionHelper helper = new FunctionHelper()
    def successOfStudents = [:]
    def qualityOfStudents = [:]
    def markByCourse = [:]
    def mark = [:]

    mark.put("A", 0)
    mark.put("B", 0)
    mark.put("C", 0)
    mark.put("D", 0)
    mark.put("F", 0)

%>

<table id="example"  class="table table-bordered border-t0 key-buttons"  >
    <thead>
    <tr>
        <td>#</td>
        <td>Хичээлийн нэр</td>
        <td># шалгагдах оюутан</td>
        <td># шалгагдаагүй оюутан</td>
        <td>&nbspA&nbsp</td>
        <td>&nbspB&nbsp</td>
        <td>&nbspC&nbsp</td>
        <td>&nbspD&nbsp</td>
        <td>&nbspF&nbsp</td>
        <td>Чанар</td>
        <td>Амжилт</td>
    </tr>
    </thead>

    <tbody>
    <g:each in="${studentsCourseList}" status="i" var="studentsCourseInstance">
        <%
            String markstr = helper.getStudentsMark(studentsCourseInstance.totalGrade)
            def copyMark = [:]
            copyMark.put("A", 0)
            copyMark.put("B", 0)
            copyMark.put("C", 0)
            copyMark.put("D", 0)
            copyMark.put("F", 0)

            if (markByCourse.get(studentsCourseInstance.course) != null) {
                copyMark = markByCourse.get(studentsCourseInstance.course).clone();
            }
            copyMark.put(markstr, copyMark.get(markstr) + 1)

            markByCourse.put(studentsCourseInstance.course, copyMark)

            if (studentsCourseInstance.totalGrade >= 80) {

                if (qualityOfStudents.get(studentsCourseInstance.course) != null) {
                    qualityOfStudents.put(studentsCourseInstance.course, qualityOfStudents.get(studentsCourseInstance.course) + 1)
                } else {
                    qualityOfStudents.put(studentsCourseInstance.course, 1)
                }
            }
            if (studentsCourseInstance.totalGrade < 60) {

                if (successOfStudents?.get(studentsCourseInstance.course) != null) {
                    successOfStudents.put(studentsCourseInstance.course, successOfStudents?.get(studentsCourseInstance.course) + 1)
                } else {
                    successOfStudents.put(studentsCourseInstance.course, 1)
                }
            }
        %>
    </g:each>
    <g:each in="${markByCourse}" status="i" var="marks">
        <%
            def mark1 = [:]
            def totalStudent = 0;
            mark1 = marks.value
            totalStudent = mark1.getAt("A")+mark1.getAt("B")+mark1.getAt("C")+mark1.getAt("D")+mark1.getAt("F");
        %>
        <tr>
            <td>${i}</td>
            <td>${marks.getKey()}</td>
            <td>${totalStudent}</td>
            <td>#</td>

            <td>${mark1.getAt("A")}</td>
            <td>${mark1.getAt("B")}</td>
            <td>${mark1.getAt("C")}</td>
            <td>${mark1.getAt("D")}</td>
            <td>${mark1.getAt("F")}</td>
            <td style="text-align: center">
                <%
                    def quality = 0;
                    if (qualityOfStudents.getAt(marks.getKey())!= null) {
                        quality = (qualityOfStudents.getAt(marks.getKey())/totalStudent)* 100
                    }
                    out << new DecimalFormat("0").format(quality);
                %>
            </td>
            <td style="text-align: center">
                <%
                    def success = 0
                    if(successOfStudents.getAt(marks.getKey()) != null){
                        success =(( totalStudent - successOfStudents.getAt(marks.getKey())) / totalStudent) * 100;
                    }
                    out << new DecimalFormat("0").format(success);
                %>
            </td>
        </tr>
    </g:each>
    </tbody>
</table>
<p>
    Нийт оюүтан : ${studentsCourseListGrouped?.size()} <br>
</p>

