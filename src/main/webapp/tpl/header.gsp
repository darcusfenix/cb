<!-- BEGIN HEADER INNER -->
<div data-ng-controller="HeaderController"
     class="page-header navbar navbar-fixed-top">
    <div class="page-header-inner" xmlns:g="http://www.w3.org/1999/xhtml">
        <!-- BEGIN LOGO -->
        <div class="page-logo">

            <div class="menu-toggler sidebar-toggler">
                <!-- DOC: Remove the above "hide" to enable the sidebar toggler button on header -->
            </div>
        </div>
        <!-- END LOGO -->
        <!-- BEGIN RESPONSIVE MENU TOGGLER -->
        <a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse"
           data-target=".navbar-collapse"></a>
        <!-- END RESPONSIVE MENU TOGGLER -->

        <!-- BEGIN PAGE TOP -->
        <div class="page-top">

            <!-- BEGIN TOP NAVIGATION MENU -->
            <div class="top-menu">
                <ul class="nav navbar-nav pull-right">
                    <li class="separator hide"></li>

                    <!-- BEGIN USER LOGIN DROPDOWN -->
                    <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
                    <li class="dropdown dropdown-user dropdown-dark">
                        <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"
                           data-close-others="true">
                            <span class="username username-hide-on-mobile">
                                <sec:access expression="hasRole('ROLE_SUPER_ADMIN')">
                                    SUPER ADMINISTRADOR
                                </sec:access>
                                <sec:access expression="hasRole('ROLE_SALESMAN')">
                                    VENDEDOR
                                </sec:access>
                            </span>

                            <img alt="" class="img-circle"
                                 src="https://cdn3.iconfinder.com/data/icons/line/36/person-32.png"/>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-default">

                            <li>
                                <a href="/logoff">
                                    <i class="icon-key"></i> Salir</a>
                            </li>
                        </ul>
                    </li>
                    <!-- END USER LOGIN DROPDOWN -->

                </ul>
            </div>
            <!-- END TOP NAVIGATION MENU -->
        </div>
        <!-- END PAGE TOP -->
    </div>
</div>
<!-- END HEADER INNER -->