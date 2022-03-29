
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

</head>

<script type="text/javascript">
    window.onload = function() { window.print(); }
</script>
<style type="text/css">
.header {
    width:21.0cm;
    padding: 20px

}
.qrcode{
    width:60px;
}
table.sample {
    border-width: 0 0 0px 0px;
    border-style: solid;
    background-color: white;
    border-spacing: 0;
    border-collapse: collapse;  ]
margin 100px , 0 , 0, 0

}
table.sample td {
    border-width: 0px 0px 0 0;
    border-style: solid;
    margin: 0;
    padding:0px 0px 0px 0px;
}
</style>
<body>
<div class="header" >
    <table width="792" class="sample">
        <tr >
            <td align="center"><img src="${resource(dir:'images',file:'logo_muds.jpg')}" alt="String" height="93" width="103" border="0" /> </td>
        </tr>
        <tr >
            <td align="center"><p align="center"><strong>МОНГОЛЫН ҮНДЭСНИЙ ИХ СУРГУУЛЬ</strong></p>  </td>
        </tr>
        <tr >
            <td align="center"><strong>MONGOLIAN NATIONAL UNIVERSITY</strong><hr></td>
        </tr>

        <tr align="center">
            <td width="784" align="center">
                Монгол улс, Улаанбаатар хот. Баянгол дүүрэг, 11-р хороо
                Утас: 976-11-300301   Факс:976-11-300799 <br>
                E-mail: info@mnun.edu.mn <br>
                ____________________№___________________
            </td>
        </tr>

        <tr >
            <td align="center"><br> <br><br> <br> ТОДОРХОЙЛОЛТ
                <br><br>       <br>
            </td>
        </tr>
        <tr >
            <td align="center">
                ${student?.studentCode} кодтой ${student?.firstName} овогтой ${student?.lastName}  нь МҮИС-д  ${student?.degree} -н зэргийн
                ${student?.programme?.programmeMN} мэргэжилээр ${student?.yearOf} -р курст суралцдаг нь үнэн болохыг тодорхойлов.
            </td>
        </tr>
        <tr><td align="center">
            <br>  <br>
            <br>    <br>
            СУРГАЛТ ЭРХЭЛСЭН ЗАХИРАЛ : ........................../Л.Гэрэлт-Од/

        </td></tr>
    </table>