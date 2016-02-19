<!-- BEGIN SAMPLE TABLE PORTLET-->
<div class="col-md-8 col-md-offset-2">
    <div class="portlet box yellow">
        <div class="portlet-title">
            <div class="caption">
                <i class="fa fa-cogs"></i>Historial de Generaciones de Brazaletes</div>
            <div class="tools">
                <a href="javascript:;" class="collapse"> </a>
            </div>
        </div>
        <div class="portlet-body" id="p-b-history-bracelets">
            <div class="table-responsive">
                <table class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th class="text-center"> # </th>
                        <th class="text-center"> Fecha </th>
                        <th class="text-center"> Cantidad </th>
                        <th>  </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="h in historyList">
                        <td class="text-center"> {{$index + 1}} </td>
                        <td class="text-center"> {{h[1] | date:'yyyy-MM-dd hh:mm a'}} </td>
                        <td class="text-center"> {{h[0]}} </td>
                        <td class="text-center"> <a href="${request.contextPath}/bracelet/date?d={{h[1] | date:'yyyy-MM-dd hh:mm a'}}" class="btn"> Obtener csv</a> </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<!-- END SAMPLE TABLE PORTLET-->
