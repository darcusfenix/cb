<%--
  Created by IntelliJ IDEA.
  User: becm
  Date: 2/5/16
  Time: 10:44 AM
--%>


<!-- BEGIN SAMPLE TABLE PORTLET-->
<div class="portlet box yellow">
    <div class="portlet-title">
        <div class="caption">
            <i class="fa fa-cogs"></i>Vendedores Activos</div>
        <div class="tools">
            <a href="javascript:;" class="collapse"> </a>
            <a href="#portlet-config" data-toggle="modal" class="config"> </a>
            <a href="javascript:;" class="reload"> </a>
            <a href="javascript:;" class="remove"> </a>
        </div>
    </div>
    <div class="portlet-body" id="p-b-vendedor-list">
        <div class="table-responsive">
            <table class="table table-striped table-bordered table-hover">
                <thead>
                <tr>
                    <th> # </th>
                    <th> Nombre </th>
                    <th> Apellidos </th>
                    <th> Correo </th>
                    <th> Total de pulseras </th>
                    <th> Total de pulseras entregadas </th>
                    <th> Total de pulseras vendidas </th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="vendedor in vendedorList">
                    <td> {{vendedor.id}} </td>
                    <td> {{vendedor.nombres}} </td>
                    <td> {{vendedor.apellidos}} </td>
                    <td> {{vendedor.username}} </td>
                    <td> </td>
                    <td>  </td>
                    <td>  </td>
                </tr>
                <tr >
                    <td> {{vendedor.id}} </td>
                    <td> {{vendedor.nombres}} </td>
                    <td> {{vendedor.apellidos}} </td>
                    <td> {{vendedor.username}} </td>
                    <td>  </td>
                    <td>  </td>
                    <td>  </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<!-- END SAMPLE TABLE PORTLET-->