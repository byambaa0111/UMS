<%@ page import="java.text.DecimalFormatSymbols; java.text.DecimalFormat" %>
<h2 align="center">
    <p>Монголын Үндэсний Их Сургууль</p>
</h2>
<table class="table table-bordered border-t0 key-buttons font-weight-light text-nowrap ">
    <tr>
        <td>Мэргэжил: ${studentList[0]?.programme}</td>
        <td>Жил: ${schoolYear}</td>
    </tr>
    <tr>
        <td>Төлбөр төлөлтийн үзүүлэлт</td>
        <td></td>
    </tr>
</table>
<%
    double niitUldegdel = 0;
    double niitOrlogo   = 0;
    double totalSuppose   = 0;
    int totalStudent = 0
    java.text.DecimalFormat df = new DecimalFormat("0");
%>
<div class="table-responsive">
    <table class="table table-bordered border-t0 key-buttons font-weight-light ">
        <thead>
        <tr>
            <td>#</td>
            <td>${message(code: 'student.studentCode.label', default: 'Student Code')} </td>
            <td>${message(code: 'student.firstName.label', default: 'First Name')} </td>
            <td>${message(code: 'student.lastName.label', default: 'Last Name')}</td>
            <td>${message(code: 'student.registerNumber.label', default: 'yearOf')} </td>
            <td>${message(code: 'student.registerNumber.label', default: 'GroupOf')} </td>
            <g:if test="${bundleParam['paymentBy'] == "0" }">
                <td><g:message code="default.supposeToPay.label" default="Төлвөл зохих"></g:message> </td>
                <td><g:message code="default.totalPayment.label" default="Нийт төлсөн"></g:message> </td>
                <td><g:message code="default.totalPayment.label" default="Үлдэгдэл"></g:message> </td>
            </g:if>
            <g:if test="${bundleParam['paymentBy'] == "1" }">
                <td>#</td>
            </g:if>
        </tr>
        </thead>
        <tbody>
        <g:each in="${studentList}" status="i" var="st">
            <%  double sumOfPayment = 0; double  supposeToPay = 0; totalStudent = i+1; %>
            <tr>
                <td> ${i+1}</td>
                <td><f:display bean="${st}" property="studentCode" /></td>
                <td><f:display bean="${st}" property="firstName" /></td>
                <td><f:display bean="${st}" property="lastName" /></td>
                <td><f:display bean="${st}" property="yearOf" /></td>
                <td><f:display bean="${st}" property="groupOf" /></td>
                <g:if test="${bundleParam['paymentBy']  == "0" }">
                    <td>
                        <g:each in="${ st?.payments?.sort{a,b-> a.payDate.compareTo(b.payDate)}}" var="payment">
                            <g:if test="${payment?.paymentType?.startDate == schoolYear}">
                                <%
                                    sumOfPayment  =  sumOfPayment + payment.amount ;
                                    supposeToPay = payment?.paymentType.isPayment ? payment?.paymentType.amount : 0
                                    totalSuppose = supposeToPay
                                %>
                            </g:if>
                        </g:each>
                        <g:formatNumber number="${supposeToPay}" format="###,###" />
                    </td>
                    <td><g:formatNumber number="${sumOfPayment}" format="###,###" /></td>
                    <td>
                        <%
                            double uld   = supposeToPay - sumOfPayment ;
                            niitUldegdel = niitUldegdel + uld
                            niitOrlogo   = niitOrlogo + sumOfPayment;
                        %>
                        <span class="badge  badge-pink"><i class="${uld > 0 ? 'fa fa-chevron-up':'fa fa-chevron-down' }"></i>
                            <g:formatNumber number="${uld}" format="###,###" />
                        </span>
                    </td>
                </g:if>
                <g:if test="${bundleParam['paymentBy'] == "1" }">
                    <td>
                    <g:each in="${ st?.payments?.sort{a,b-> a.payDate.compareTo(b.payDate)}}" var="payment">
                        <g:if test="${payment?.paymentType?.startDate == schoolYear}">
                            <p>${payment?.paymentType}:<span class="badge  badge-info"> <g:formatNumber number="${payment.amount}" format="###,###" /></span></p>
                        </g:if>
                    </g:each>
                    ${com.ums.payment.PaymentType.findByProgrammeAndStartDateAndYear(studentList[0]?.programme,schoolYear,bundleParam['yearOf'])}

                        <g:textField name="amount" id="amount${i}" value="" onblur="savePayment(${st.id})" ></g:textField>
                        <g:submitButton name="saveamount" value="Save"></g:submitButton>
                    </td>
                </g:if>
            </tr>
        </g:each>
        </tbody>
        <tfoot>
        <tr>
            <td colspan='6'>Нийт оюутан : ${totalStudent}, Төлбөр төлөлтийн хувь:<g:formatNumber number="${  niitOrlogo*100 / (totalSuppose * totalStudent) }" maxFractionDigits="2" />%   </td>
            <td><g:formatNumber number="${totalSuppose * totalStudent}"  format="###,###" maxFractionDigits="0" /></td>
            <td><g:formatNumber number="${niitOrlogo}"  format="###,###" maxFractionDigits="0" /></td>
            <td><g:formatNumber number="${niitUldegdel}" format="###,###" maxFractionDigits="0" /></td>
        </tr>
        </tfoot>
    </table>
</div>
<script>
    function savePayment(studentCourseId,examTypeId,gradeFromTeacher) {
        url="/payment/savePaymentsBy"
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
