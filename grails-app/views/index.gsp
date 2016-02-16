<!DOCTYPE html>

<html lang="en" data-ng-app="CapitalBusApp">

<head>
    <title></title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="" name="author"/>

    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet"
          type="text/css"/>
    <link href="${resource(dir: 'rs/global/plugins/font-awesome/css', file: 'font-awesome.min.css')}" rel="stylesheet"
          type="text/css"/>
    <link href="${resource(dir: 'rs/global/plugins/simple-line-icons', file: 'simple-line-icons.min.css')}"
          rel="stylesheet" type="text/css"/>
    <link href="${resource(dir: 'rs/global/plugins/bootstrap/css', file: 'bootstrap.min.css')}" rel="stylesheet"
          type="text/css"/>
    <link href="${resource(dir: 'rs/global/plugins/uniform/css', file: 'uniform.default.css')}" rel="stylesheet"
          type="text/css"/>
    <link href="${resource(dir: 'rs/global/plugins/bootstrap-switch/css', file: 'bootstrap-switch.min.css')}"
          rel="stylesheet" type="text/css"/>
    <!-- END GLOBAL MANDATORY STYLES -->

    <!-- BEGIN DYMANICLY LOADED CSS FILES(all plugin and page related styles must be loaded between GLOBAL and THEME css files ) -->
    <link id="ng_load_plugins_before"/>
    <!-- END DYMANICLY LOADED CSS FILES -->

    <!-- BEGIN THEME STYLES -->
    <!-- DOC: To use 'rounded corners' style just load 'components-rounded.css' stylesheet instead of 'components.css' in the below style tag -->
    <link href="${resource(dir: 'rs/global/css', file: 'components-rounded.min.css')}" id="style_components"
          rel="stylesheet" type="text/css"/>
    <link href="${resource(dir: 'rs/global/css', file: 'plugins.min.css')}" rel="stylesheet" type="text/css"/>
    <link href="${resource(dir: 'rs/layouts/layout4/css', file: 'layout.min.css')}" rel="stylesheet" type="text/css"/>
    <link href="${resource(dir: 'rs/layouts/layout4/css/themes', file: 'light.min.css')}" rel="stylesheet"
          type="text/css" id="style_color"/>
    <link href="${resource(dir: 'rs/layouts/layout4/css', file: 'custom.min.css')}" rel="stylesheet" type="text/css"/>
    <!-- END THEME STYLES -->

    <link rel="shortcut icon" href="favicon.ico"/></head>

<body ng-controller="AppController"
      class="page-header-fixed page-sidebar-closed-hide-logo page-container-bg-solid page-sidebar-closed-hide-logo page-on-load"
      ng-class="{'page-sidebar-closed': settings.layout.pageSidebarClosed}">
<!-- BEGIN PAGE SPINNER -->
<div ng-spinner-bar class="page-spinner-bar">
    <div class="bounce1"></div>

    <div class="bounce2"></div>

    <div class="bounce3"></div>
</div>
<!-- END PAGE SPINNER -->

<!-- BEGIN HEADER -->
<g:include view="tpl/header.gsp"/>
<!-- END HEADER -->
<div class="clearfix"></div>
<!-- BEGIN CONTAINER -->
<div class="page-container">

    <!-- BEGIN SIDEBAR -->
    <g:include view="tpl/sidebar.gsp"/>
    <!-- END SIDEBAR -->

    <div class="page-content-wrapper">
        <div class="page-content">
            <!-- BEGIN PAGE HEAD -->
            <g:include view="tpl/page-head.gsp"/>
            <!-- END PAGE HEAD -->

            <!-- BEGIN ACTUAL CONTENT -->
            <div ui-view class="fade-in-up">

            </div>
            <!-- END ACTUAL CONTENT -->
        </div>
    </div>

</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<g:include view="tpl/sidebafooterr.gsp"/>
<!-- END FOOTER -->

<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

<!-- BEGIN CORE JQUERY PLUGINS -->

<!--[if lt IE 9]>
	<script src="${resource(dir: 'rs/global/plugins', file: 'respond.min.js')}"></script>
	<script src="${resource(dir: 'rs/global/plugins', file: 'excanvas.min.js')}"></script>
	<![endif]-->

<script type="text/javascript">
    var BASE_URL = '${request.contextPath}/static/';
    var BASE_RESOURCES = 'rs';
</script>

<script src="${resource(dir: 'rs/global/plugins', file: 'jquery.min.js')}" type="text/javascript"></script>
<script src="${resource(dir: 'rs/global/plugins', file: 'jquery-migrate.min.js')}" type="text/javascript"></script>
<script src="${resource(dir: 'rs/global/plugins/bootstrap/js', file: 'bootstrap.min.js')}"
        type="text/javascript"></script>
<script src="${resource(dir: 'rs/global/plugins/bootstrap-hover-dropdown', file: 'bootstrap-hover-dropdown.min.js')}"
        type="text/javascript"></script>
<script src="${resource(dir: 'rs/global/plugins/jquery-slimscroll', file: 'jquery.slimscroll.min.js')}"
        type="text/javascript"></script>
<script src="${resource(dir: 'rs/global/plugins', file: 'jquery.blockui.min.js')}" type="text/javascript"></script>
<script src="${resource(dir: 'rs/global/plugins', file: 'jquery.cokie.min.js')}" type="text/javascript"></script>
<script src="${resource(dir: 'rs/global/plugins/uniform', file: 'jquery.uniform.min.js')}"
        type="text/javascript"></script>
<!-- END CORE JQUERY PLUGINS -->

<!-- BEGIN CORE ANGULARJS PLUGINS -->
<script src="${resource(dir: 'rs/global/plugins/angularjs', file: 'angular.min.js')}" type="text/javascript"></script>
<script src="${resource(dir: 'rs/global/plugins/angularjs', file: 'angular-route.min.js')}"
        type="text/javascript"></script>
<script src="${resource(dir: 'rs/global/plugins/angularjs', file: 'angular-resource.min.js')}"
        type="text/javascript"></script>
<script src="${resource(dir: 'rs/global/plugins/angularjs', file: 'angular-sanitize.min.js')}"
        type="text/javascript"></script>
<script src="${resource(dir: 'rs/global/plugins/angularjs', file: 'angular-touch.min.js')}"
        type="text/javascript"></script>
<script src="${resource(dir: 'rs/global/plugins/angularjs', file: 'plugins/angular-ui-router.min.js')}"
        type="text/javascript"></script>
<script src="${resource(dir: 'rs/global/plugins/angularjs/plugins', file: 'ocLazyLoad.min.js')}"
        type="text/javascript"></script>
<script src="${resource(dir: 'rs/global/plugins/angularjs/plugins', file: 'ui-bootstrap-tpls.min.js')}"
        type="text/javascript"></script>
<!-- END CORE ANGULARJS PLUGINS -->

<!-- BEGIN APP LEVEL ANGULARJS SCRIPTS -->
<script src="${resource(dir: 'angularjs-app', file: 'App.js')}" type="text/javascript"></script>
<script src="${resource(dir: 'angularjs-app/', file: 'directives.js')}" type="text/javascript"></script>

<!-- END APP LEVEL ANGULARJS SCRIPTS -->

<!-- BEGIN APP LEVEL JQUERY SCRIPTS -->
<script src="${resource(dir: 'rs/global/scripts', file: 'app.js')}" type="text/javascript"></script>
<script src="${resource(dir: 'rs/layouts/layout4/scripts', file: 'layout.min.js')}" type="text/javascript"></script>
<script src="${resource(dir: 'rs/layouts/global/scripts', file: 'quick-sidebar.min.js')}"
        type="text/javascript"></script>
<script src="${resource(dir: 'rs/layouts/layout4/scripts', file: 'demo.min.js')}" type="text/javascript"></script>
<!-- END APP LEVEL JQUERY SCRIPTS -->
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->

</html>