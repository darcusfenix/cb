/**
 * Created by grupo-becm on 1/25/16.
 */

angular.module('CapitalBusApp').factory('Bracelet', function ($resource) {
    return $resource('bracelet/:id', {id: '@id'}, {
        get: {
            method: 'GET',
            url: 'bracelet/show/:id'
        },
        update: {
            method: 'PUT',
            url: 'bracelet/update/'
        },
        delete: {
            method: 'DELETE',
            url: 'bracelet/delete/:id'
        },
        create: {
            method: 'GET',
            url: 'bracelet/create/'
        },
        save: {
            method: 'GET',
            url: 'bracelet/save'
        },
        history: {
            method: 'GET',
            isArray: true,
            url: 'bracelet/history'
        }
    });
});