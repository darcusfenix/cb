<div class="row margin-bottom-20 margin-right-10">
    <div id="dashboard-report-range" class="pull-right tooltips btn btn-fit-height green" data-placement="top"
         data-original-title="Change dashboard date range">
        <i class="icon-calendar"></i>&nbsp;
        <span class="thin uppercase hidden-xs" id="span"></span>&nbsp;
        <i class="fa fa-angle-down"></i>
    </div>
</div>

<div class="row" id="report-bracelet">
    <div class="col-md-12">
        <!-- BEGIN PORTLET-->
        <div class="portlet light bordered ">
            <div class="portlet-title">
                <div class="caption caption-md">
                    <span class="caption-subject bold uppercase">Brazaletes {{textCurrent}}</span>
                </div>
                <div class="actions">
                    <div class="btn-group">
                        <a href="" class="btn dark btn-outline btn-circle btn-sm dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true"> Tipo reporte
                            <span class="fa fa-angle-down"> </span>
                        </a>
                        <ul class="dropdown-menu pull-right">
                            <li>
                                <a href="javascript:;" ng-click="changeReport(1)"> Entregas
                                    <span class="label label-sm {{option == 1  ? 'label-success' : '' }}"> {{option == 1  ? 'seleccionada' : '' }} </span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:;" ng-click="changeReport(2)"> Asignadas
                                    <span class="label label-sm {{option == 2  ? 'label-success' : '' }}"> {{option == 2  ? 'seleccionada' : '' }} </span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="portlet-body" id="rs-busqueda">
                <div class="table-scrollable table-scrollable-borderless">
                    <table class="table table-hover table-light">
                        <thead>
                        <tr class="uppercase">
                            <th class="bold ">#</th>
                            <th class="bold ">Fecha</th>
                            <th class="bold ">Vendedor</th>
                            <th class="bold" ng-repeat=" cost in costs ">
                                Serie {{cost.id}}
                            </th>
                            <th class="bold ">Total {{textCurrent}}</th>
                        </tr>
                        </thead>
                        <tr ng-repeat="m in map ">
                            <td>{{$index + 1}}</td>
                            <td>{{m[0] | date:'MMMM dd yyyy, h:mm:ss a' }}</td>
                            <td>{{m[2].firstName}} {{m[2].lastName}}</td>
                            <th class="bold" ng-repeat=" cost in costs ">
                                <span ng-repeat="s in m[1]">
                                   {{ s[0].id == cost.id ? s[1] : '' }}
                                </span>
                            </th>
                            <td></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <!-- END PORTLET-->
    </div>
</div>
