/**
 * Created by becm on 2/24/16.
 */
angular.module('Capitalbus', [])
    .directive('directiveEndRepeatReporteCaja', function() {
        return function(scope, element, attrs) {
            if (scope.$last){
                $(".color-demo").click(function () {
                     var element = $(this).find(".color-view");
                     var f = $(this).attr("soldBracelet").toString();
                     var bg = $(this).attr("bs-bg-color");
                     if (f === "false") {
                         element.css({"background-color": "#03D511"});
                         $(this).attr("soldBracelet", "true");
                     }else{
                         element.css({"background-color": bg});
                         $(this).attr("soldBracelet", "false");
                     }
                });
            }
        };
    });