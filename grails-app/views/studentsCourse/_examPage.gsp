<%@ page import="com.ums.edu.exams.Exam" %>
<h2 align="center">
    <p>Монголын Үндэсний Их Сургууль</p>
</h2>
<table class="table table-bordered border-t0 key-buttons text-nowrap w-100" >
    <tr>
        <td>Мэргэжил : ${studentsCourseList[0]?.programmePlan} </td>
        <td>Жил : ${studentsCourseList[0]?.year}  </td>
        <td>Багш : ${studentsCourseList[0]?.teacherName}</td>
    </tr>
    <tr>
        <td>Хичээлийн нэр : ${studentsCourseList[0]?.course}</td>

        <td>Улирал : ${studentsCourseList[0]?.semester}</td>
        <td>Кредит : ${studentsCourseList[0]?.course?.totalCredit}</td>
    </tr>
</table>
    <table id="example"  class="table table-bordered border-t0 key-buttons text-nowrap w-100"  >
        <thead>
        <tr>
            <td>#</td>
            <td>Нэр</td>
            <td>Овог</td>
            <td>Код</td>
            <td>70 оноо</td>
            <td>Шалгалтын оноо</td>
            <td>Дүн</td>
            <td>Багш</td>
        </tr>
        </thead>
        <tbody>
        <g:each in="${studentsCourseList}" var="bean" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}" >
                <td>${i+1}</td>
                <td>${bean.student?.firstName}</td>
                <td>${bean.student?.lastName}</td>
                <td>${bean.student?.studentCode}</td>
                <td>${bean.gradeFromTeacher}</td>
                <td>${bean.examGrade}</td>
                <td>${bean.totalGrade}</td>
                <td>${bean.teacherName}</td>
            </tr>
        </g:each>
        </tbody>
    </table>
<p>
    Нийт оюүтан : ${studentsCourseList?.size()} <br>
    Шалгагдвал зохих:                            <br>
    Шалгасан:                                        <br>
    Шалгалт авсан багшийн гарын үсэг:................................<br>
    Ассистент багшийн гарын үсэг:................................
</p>

