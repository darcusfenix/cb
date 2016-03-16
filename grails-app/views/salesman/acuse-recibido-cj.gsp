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
    <style type="text/css">
    .separacion {
        background-color: #e7287e;
        width: 100%;
        height: 30px;
        color: white;
        text-transform: uppercase;
        margin-top: 10px;
        margin-bottom: 10px;
    }

    .sold {
        background-color: red;
        color: #fff;
        margin: 1px;
        font-weight: bold;
    }

    td {
        padding: 5px;
    }

    </style>
</head>

<body>

<div class="text-center">
    <div class="btn btn-danger text-center btn-sm active" id="create_pdf">Obtener PDF</div>
</div>

<section id="acuse">
    <div class="container">
        <div class="row text-center">
            <h4><b>CAPITAL BUS, S.A. DE C.V.</b></h4>
        </div>

        <div class="row separacion text-center">
            <h5><b>formato de control de folios - corte final</b></h5>
        </div>

        <div class="row">
            <div class="col-md-6">
                <table>
                    <tr>
                        <td>FECHA:</td>
                        <td>${date}</td>
                    </tr>
                    <tr>
                        <td>AUDÍFONOS:</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>PROMOTOR:</td>
                        <td><b id="name-salesman">${salesman.firstName}  ${salesman.lastName}</b></td>
                    </tr>
                </table>
            </div>

            <div class="col-md-6">
                <table>
                    <tr>
                        <td>PUNTO DE VENTA/BUS:</td>
                        <td></td>
                    </tr>
                </table>
            </div>
        </div>

        <div class="row separacion text-center">
            <h5><b>folios vendidos</b></h5>
        </div>

        <div class="row" style="margin-top: 5px;">
            <table class="table table-striped">
                <g:each var="serie" in="${series}">
                    <tr>
                        <td style="width: 10%">SERIE ${serie[0].id}</td>
                        <td style="width: 90%">
                            <g:each var="brazalete" in="${resultados}">
                                <g:if test="${brazalete.costBracelet.id == serie[0].id}">
                                    ${brazalete.id} -
                                </g:if>
                            </g:each>
                        </td>
                    </tr>
                </g:each>
            </table>
        </div>


        <g:each var="brazalete" in="${brazaletes}">
            <div class="row separacion text-center">
                <h5><b>folios entregados con fecha: <g:formatDate format="yyyy-MM-dd hh:mm:ss a"
                                                                  date="${brazalete.key}"/></b></h5>
            </div>

            <div class="row" style="margin-top: 5px;">
                <table class="table table-striped">
                    <g:each var="serie" in="${series}">
                        <g:if test="${brazalete.value[0].costBracelet.id == serie[0].id}">
                            <tr>
                                <td style="width: 10%">SERIE ${serie[0].id}</td>
                                <td style="width: 90%">
                                    <g:each var="b" in="${brazalete.value}">
                                        <g:if test="${b.costBracelet.id == serie[0].id}">
                                            <span class="${b.sold ? 'sold' : ''}">
                                                ${b.id}
                                            </span>
                                        </g:if>
                                    </g:each>
                                </td>
                            </tr>
                        </g:if>
                    </g:each>
                </table>
            </div>

        </g:each>

        <div class="row separacion text-center">
            <h5><b>Resumen</b></h5>
        </div>

        <div class="row" style="margin-top: 5px;">
            <div class="col-md-12" style="margin-top: 10px;">
                <table class="table table-bordered">
                    <thead>
                    <th></th>
                    <g:each var="serie" in="${series}">
                        <th class="text-right">Serie ${serie[0].id}</th>
                    </g:each>
                    <th class="text-center">Total</th>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Total de brazaletes</td>
                        <g:each var="serie" in="${series}">
                            <th class="text-right">${serie[1]}</th>
                        </g:each>
                        <td class="text-right">
                            ${totalBracelets}
                        </td>
                    </tr>
                    <tr>
                        <td>Total</td>
                        <g:each var="serie" in="${series}">
                            <th class="text-right">
                                <g:formatNumber number="${serie[0].cost * serie[1]}" type="currency" currencyCode="MXN"
                                                currencySymbol="\$ "/>
                            </th>
                        </g:each>
                        <td class="text-right">
                            <g:formatNumber number="${totalMoney}" type="currency" currencyCode="MXN"
                                            currencySymbol="\$ "/>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="row" style="margin-top: 5px;">
            <div class="col-md-12" style="margin-top: 10px;">
                <table class="table table-bordered">
                    <thead>
                    <th>EFECTIVO</th>
                    <th>TPV</th>
                    <th>IZZETLE</th>
                    <th>EN LINEA</th>
                    <th>GRUPON</th>
                    </thead>
                    <tbody>
                    <tr>
                        <td class="text-left">$</td>
                        <td class="text-left">$</td>
                        <td class="text-left">$</td>
                        <td class="text-left">$</td>
                        <td class="text-left">$</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="row" style="margin-top: 5px;">
            <div class="col-md-6 text-center">
                <hr style="border: 1px  solid black; margin-top: 85px"/>
                <h6>ENTREGA</h6>
            </div>

            <div class="col-md-6 text-center">
                <h5>${salesman.firstName}  ${salesman.lastName}</h5>
                <hr style="border: 1px  solid black; margin-top: 60px"/>
                <h6>RECIBE</h6>
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