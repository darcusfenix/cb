<%--
  Created by IntelliJ IDEA.
  User: becm
  Date: 3/4/16
  Time: 9:55 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <link href="${resource(dir: 'rs/global/plugins/bootstrap/css', file: 'bootstrap.min.css')}" rel="stylesheet"
          type="text/css"/>
</head>

<body>

<div class="text-center">
    <div class="btn btn-danger text-center btn-sm" id="create_pdf">Obtener PDF</div>
</div>

<section id="acuse">
    <div class="container">
        <div class="row">
            <div class="col-md-6 text-center">
                <img src="https://capitalbus.mx/capitalbus/global/foundr/img/logo.png" style="margin-top: 12px" alt="logo" class="logo-default">
            </div>
            <div class="col-md-6 text-center">
                <h6 class="text-right">Capital bus 2016</h6>
                <h3 id="name-salesman">${salesman.firstName}  ${salesman.lastName}</h3>
                <h5 class="text-right">Acuse de recibido - Corte de caja</h5>
                <h5 class="text-right">${date}</h5>
            </div>
        </div>
        <div class="row" style="margin-top: 50px;">
            <div class="col-md-12">
                <h5><b>Corte de caja generado con fecha: ${date}</b></h5>
            </div>
        </div>
        <div class="row" style="margin-top: 50px;">
            <div class="col-md-12">
                <table class="table table-striped">
                    <thead>
                    <th></th>
                    <g:each var="serie" in="${resultados}">
                        <th class="text-right">Serie ${serie[2].id}</th>
                    </g:each>
                    <th class="text-center">Total</th>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Total de brazaletes</td>
                        <g:each var="serie" in="${resultados}">
                            <td  class="text-right">${serie[1]}</td>
                        </g:each>
                        <td  class="text-right">
                            ${totalBracelets}
                        </td>
                    </tr>
                    <tr>
                        <td>Total </td>
                        <g:each var="serie" in="${resultados}">
                            <td  class="text-right">
                                <g:formatNumber number="${serie[1] * serie[2].cost}" type="currency" currencyCode="USD" />
                            </td>
                        </g:each>
                        <td  class="text-right">
                            <g:formatNumber number="${totalMoney}" type="currency" currencyCode="USD"/>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row" style="margin-top: 350px;">
            <div class="col-md-6 text-center">
                <h4>${salesman.firstName}  ${salesman.lastName}</h4>
                <hr style="border: 1px  solid black; margin-top: 85px"/>
                <h6>nombre y firma</h6>
            </div>
            <div class="col-md-6 text-center">
                <h4>JUAN CRISÓSTOMO VÁZQUEZ</h4>
                <hr style="border: 1px  solid black; margin-top: 85px"/>
                <h6>nombre y firma</h6>
            </div>
        </div>
    </div>
</section>
<script src="${resource(dir: 'rs/global/plugins', file: 'jquery.min.js')}" type="text/javascript"></script>
<script src="${resource(dir: 'rs/pdf', file: 'jspdf.min.js')}" type="text/javascript"></script>
<script src="${resource(dir: 'rs/pdf', file: 'html2canvas.min.js')}" type="text/javascript"></script>
<script src="${resource(dir: 'rs/pdf', file: 'app.js')}" type="text/javascript"></script>

</body>
</html>