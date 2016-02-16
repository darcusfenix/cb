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
                    <a href="#/buscar-vendedor">
                        <i class="icon-settings"></i>
                        <span class="title">Buscar Vendedor</span>
                    </a>
                </li>
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
            </sec:access>

        </ul>
        <!-- END SIDEBAR MENU -->
    </div>
</div>