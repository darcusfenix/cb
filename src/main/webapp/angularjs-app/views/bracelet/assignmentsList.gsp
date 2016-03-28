<div class="row margin-bottom-20 margin-right-10">
    <div id="dashboard-report-range" class="pull-right tooltips btn btn-fit-height green" data-placement="top"
         data-original-title="Change dashboard date range">
        <i class="icon-calendar"></i>&nbsp;
        <span class="thin uppercase hidden-xs" id="span"></span>&nbsp;
        <i class="fa fa-angle-down"></i>
    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <!-- BEGIN PORTLET-->
        <div class="portlet light bordered ">
            <div class="portlet-title">
                <div class="caption caption-md">
                    <span class="caption-subject bold uppercase">Brazaletes asignados, coming soon</span>
                </div>

                <div class="tools">
                    <a href="javascript:;" class="collapse"></a>
                </div>
            </div>

            <div class="portlet-body" id="rs-busqueda">
                <div class="table-scrollable table-scrollable-borderless">
                    <table class="table table-hover table-light">
                        <thead>
                        <tr class="uppercase">
                            <th class="bold ">#</th>
                            <th class="bold ">Vendedor</th>
                            <th class="bold ">Total asignados</th>
                        </tr>
                        </thead>
                        <tr ng-repeat="assignment in assignmentsList">
                            <td>{{$index + 1}}</td>
                            <td>{{assignment[2].firstName}} {{assignment[2].lastName}}</td>
                            <td>{{assignment[1]}}</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <!-- END PORTLET-->
    </div>
</div>
