/**
 * Created by grupo-becm on 1/29/16.
 */


angular.module('CapitalBusApp').factory('CostoPulsera', function ($resource) {
    return $resource('costoPulsera/:id', {id: '@id'}, {
        get: {
            method: 'GET',
            url: 'costoPulsera/:id'
        },
        update: {
            method: 'PUT',
            url: 'costoPulsera/'
        },
        delete: {
            method: 'DELETE',
            url: 'costoPulsera/:id'
        },
        create: {
            method: 'GET',
            url: 'costoPulsera/create/'
        },
        save: {
            method: 'POST',
            url: 'costoPulsera/'
        }
    });
});