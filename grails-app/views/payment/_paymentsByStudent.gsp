<h2 align="center">
    <p>Монголын Үндэсний Их Сургууль</p>
</h2>
<table class="table table-bordered border-t0 key-buttons font-weight-light text-nowrap ">
    <tr>

        <td>Овог нэр: ${student}</td>
        <td>Мэргэжил: ${student?.programme}</td>
        <td>Элсэн орсон жил: ${student.enrollmentDate}</td>
    </tr>
    <tr>
        <td>Төлбөр төлөлтийн үзүүлэлт</td>
        <td>Курс: ${student.yearOf}</td>
        <td>Групп: ${student.groupOf}</td>
    </tr>
</table>
<%
    def groupByYear = student?.payments.groupBy { it-> it.paymentType.year }
    def sumByYear = student?.payments.groupBy { it-> it.paymentType.year }.collect{k,v->[(k):v.amount.sum()]}
%>

<table class="table table-bordered border-t0 key-buttons font-weight-light text-nowrap ">
    <thead>
        <tr>
            <th>Төлбөрийн төрөл</th>
            <th>Төлвөл зохих</th>
            <th>Төлсөн огноо</th>
            <th>Мөнгөн дүн</th>
        </tr>
    </thead>
    <tbody>
    <g:each in="${ (1..<5) }" var="year" status="j">
        <% double totalPay = 0 %>
        <g:each in="${groupByYear[year.toString()]}" var="payment" status="i">
            <% totalPay = totalPay + payment.amount %>
            <tr>
                <td>${payment.paymentType.paymentType}</td>
                <td>${payment.paymentType.amount}</td>
                <td> <g:formatDate format="yyyy-MM-dd" date="${payment.payDate}"/></td>
                <td><g:formatNumber number="${payment.amount}" format="###,###" /></td>
            </tr>
        </g:each>
        <tr>
            <th colspan="2">Курс: ${year}</th>
            <th>Нийт төлсөн дүн:</th>
            <th> <g:formatNumber number="${totalPay}" format="###,###" /></th>
        </tr>
    </g:each>


    </tbody>
</table>
