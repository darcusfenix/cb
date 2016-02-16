/**
 * Created by becm on 2/10/16.
 */

angular.module('CapitalBusApp').factory('Circuit', function ($resource) {

    return $resource('circuit/:id', {id: '@id'}, {
        get: {
            method: 'GET',
            url: 'circuit/:id'
        },
        update: {
            method: 'PUT',
            url: 'circuit/'
        },
        delete: {
            method: 'DELETE',
            url: 'circuit/:id'
        },
        create: {
            method: 'GET',
            url: 'circuit/'
        },
        save: {
            method: 'POST',
            url: 'circuit'
        }
    });
});