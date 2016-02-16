<%--
  Created by IntelliJ IDEA.
  User: becm
  Date: 2/4/16
  Time: 11:15 AM
--%>
<div class="row center" ng-show="error.flag">
    <div class="col-md-6 form  col-md-offset-3">
        <h1 class="text-danger">{{error.message}}</h1>
    </div>
</div>

<div class="row" ng-hide="error.flag" >
    <div class="col-md-8" >
        <div class="portlet light bordered " id=".-personales">
            <div class="portlet-title">
                <div class="caption caption-md">
                    <i class="icon-bar-chart font-red"></i>
                    <span class="caption-subject font-red bold uppercase">Datos Personales</span>

                </div>
            </div>
            <div class="portlet-body"  data-ng-include="'angularjs-app/views/vendedor/tpl/datos-personales.gsp'">
            </div>
        </div>
    </div>
</div>


<!-- BEGIN SAMPLE TABLE PORTLET-->
<div class="portlet box yellow">
    <div class="portlet-title">
        <div class="caption">
            <i class="fa fa-cogs"></i>All in One Bootstrap 3.0 Responsive Table </div>
        <div class="tools">
            <a href="javascript:;" class="collapse"> </a>
            <a href="#portlet-config" data-toggle="modal" class="config"> </a>
            <a href="javascript:;" class="reload"> </a>
            <a href="javascript:;" class="remove"> </a>
        </div>
    </div>
    <div class="portlet-body">
        <div class="table-responsive">
            <table class="table table-striped table-bordered table-hover">
                <thead>
                <tr>
                    <th> # </th>
                    <th> Table heading </th>
                    <th> Table heading </th>
                    <th> Table heading </th>
                    <th> Table heading </th>
                    <th> Table heading </th>
                    <th> Table heading </th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td> 1 </td>
                    <td> Table cell </td>
                    <td> Table cell </td>
                    <td> Table cell </td>
                    <td> Table cell </td>
                    <td> Table cell </td>
                    <td> Table cell </td>
                </tr>
                <tr>
                    <td> 2 </td>
                    <td> Table cell </td>
                    <td> Table cell </td>
                    <td> Table cell </td>
                    <td> Table cell </td>
                    <td> Table cell </td>
                    <td> Table cell </td>
                </tr>
                <tr>
                    <td> 3 </td>
                    <td> Table cell </td>
                    <td> Table cell </td>
                    <td> Table cell </td>
                    <td> Table cell </td>
                    <td> Table cell </td>
                    <td> Table cell </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<!-- END SAMPLE TABLE PORTLET-->