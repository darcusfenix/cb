/**
 * Created by becm on 2/23/16.
 */
/**
 Demo script to handle the theme demo
 **/
var ResumeBraceletsService = function () {



    return {
        init: function() {

        }
    };

}();

if (App.isAngularJsApp() === false) {
    jQuery(document).ready(function() {
        ResumeBraceletsService.init(); // init metronic core componets
    });
}