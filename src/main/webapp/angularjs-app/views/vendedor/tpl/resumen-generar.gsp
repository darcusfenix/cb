<%--
  Created by IntelliJ IDEA.
  User: darcusfenix
  Date: 2/1/16
  Time: 1:30 PM
--%>

<div class="row">
    <div class="table-scrollable table-scrollable-borderless">
        <table class="table table-hover table-light">
            <thead>
            <tr class="uppercase">
                <th class="bold font-green text-center"></th>
                <th class="bold font-green text-center" ng-repeat="daysDuration in daysDurationList">
                    {{daysDuration.days }}
                </th>
                <th class="text-center bold font-green">Total</th>
            </tr>
            </thead>

            <tr ng-repeat="kindPerson in kindPersontList">
                <th class="font-blue text-uppercase  text-center">{{kindPerson.name}}</th>
                <th class=" text-center" ng-repeat="daysDuration in daysDurationList">
                    {{ getTotalBetweenKindPersonAndDaysDuration(kindPerson.id, daysDuration.id) }}
                </th>
                <th class="text-center bold font-green">
                    {{ getTotalByKIndPerson(kindPerson.id) }}
                </th>
            </tr>

            <tr>
                <th class="font-blue text-uppercase  text-center">Total</th>
                <th class="bold font-green text-center" ng-repeat="daysDuration in daysDurationList">
                    {{ getTotalByDaysDuration(daysDuration.id) }}
                </th>
                <th class="text-center bold font-green">
                    {{ getTotalOfBracelets() }}
                </th>
            </tr>
        </table>
    </div>
</div>