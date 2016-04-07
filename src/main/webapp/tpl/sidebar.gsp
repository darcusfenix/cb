<div data-ng-controller="SidebarController" class="page-sidebar-wrapper">
    <div class="page-sidebar navbar-collapse collapse">
        <ul class="page-sidebar-menu" data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200"
            ng-class="{'page-sidebar-menu-closed': settings.layout.pageSidebarClosed}">
            <li class="heading">
                <h3>Menú</h3>
            </li>
            <li class="start nav-item">
                <a href="#/">
                    <i class="icon-home"></i>
                    <span class="title">Inicio</span>
                </a>
            </li>

            <sec:access expression="hasRole('ROLE_SUPER_ADMIN')">
                <li class="nav-item">
                    <a href="#/generar-brazaletes">
                        <i class="icon-settings"></i>
                        <span class="title">Generar Brazaletes</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="#/historial-brazaletes">
                        <i class="icon-settings"></i>
                        <span class="title">Historial de Brazaletes</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="#/buscar-brazalete">
                        <i class="icon-settings"></i>
                        <span class="title">Buscar brazalete</span>
                    </a>
                </li>
            </sec:access>

            <sec:access expression="hasRole('ROLE_ADMIN_CONTROL_BRACELET')">
                <li class="nav-item">
                    <a href="#/entregar-brazaletes">
                        <i class="icon-settings"></i>
                        <span class="title">Brazaletes</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="#/historial-de-brazaletes">
                        <i class="icon-settings"></i>
                        <span class="title">Historial de brazaletes</span>
                    </a>
                </li>
            </sec:access>
            <sec:access expression="hasRole('ROLE_SALESMAN')">
                <li class="nav-item">
                    <a href="#/corte-de-caja">
                        <i class="icon-settings"></i>
                        <span class="title">Generar corte de caja</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="#/historial-corte-de-caja">
                        <i class="icon-settings"></i>
                        <span class="title">Mis cortes de caja</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="#/mis-asignaciones">
                        <i class="icon-settings"></i>
                        <span class="title">Mis asignaciones</span>
                    </a>
                </li>
            </sec:access>

        </ul>
        <!-- END SIDEBAR MENU -->
    </div>
</div>