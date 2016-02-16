/**
 * Created by darcusfenix on 1/26/16.
 */

angular.module('CapitalBusApp').factory('Salesman', function ($resource) {

    return $resource('salesman/:id', {id: '@id'}, {
        get: {
            method: 'GET',
            url: 'salesman/:id'
        },
        update: {
            method: 'PUT',
            url: 'salesman/'
        },
        delete: {
            method: 'DELETE',
            url: 'salesman/:id'
        },
        create: {
            method: 'GET',
            url: 'salesman/'
        },
        save: {
            method: 'POST',
            url: 'salesman'
        },
        getTotalPulseras: {
            method: 'GET',
            isArray: true,
            url: 'salesman/:id/pulseras/total'
        },
        getAsignaciones: {
            method: 'GET',
            isArray: true,
            url: 'salesman/:id/pulseras/asignaciones'
        }
    });
});