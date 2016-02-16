/**
 * Created by grupo-becm on 1/29/16.
 */

angular.module('CapitalBusApp').factory('DaysDuration', function ($resource) {
    return $resource('daysDuration/:id', {id: '@id'}, {
        get: {
            method: 'GET',
            url: 'daysDuration/show/:id'
        },
        update: {
            method: 'PUT',
            url: 'daysDuration/update/'
        },
        delete: {
            method: 'DELETE',
            url: 'daysDuration/delete/:id'
        },
        create: {
            method: 'GET',
            url: 'daysDuration/create/'
        },
        save: {
            method: 'POST',
            url: 'daysDuration/save'
        }
    });
});