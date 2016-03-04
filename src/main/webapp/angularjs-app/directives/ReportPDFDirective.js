angular.module('Capitalbus', [])
    .directive('directiveReportPdf', function() {
        return function(scope, element, attrs) {
            if (scope.$last){
                var
                    form = $('#p-b-history-bracelets'),
                    cache_width = form.width(),
                    a4 = [595.28, 841.89];  // for a4 size paper width and height
                $('.btn-get-report').live("click", function () {
                    $('body').scrollTop(0);
                    createPDF();
                });
                createPDF = function () {
                    getCanvas().then(function (canvas) {
                        var
                            img = canvas.toDataURL("image/png"),
                            doc = new jsPDF({
                                unit: 'px',
                                format: 'a4'
                            });
                        doc.addImage(img, 'JPEG', 20, 20);
                        doc.save('acuse.pdf');
                        form.width(cache_width);
                    });
                };
                getCanvas = function () {
                    form.width((a4[0] * 1.33333) - 80).css('max-width', 'none');
                    return html2canvas(form, {
                        imageTimeout: 2000,
                        removeContainer: true
                    });
                }
            }
        };
    });