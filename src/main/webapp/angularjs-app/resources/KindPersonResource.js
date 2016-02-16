/**
 * Created by grupo-becm on 1/29/16.
 */

angular.module('CapitalBusApp').factory('KindPerson', function ($resource) {
    return $resource('kindPerson/:id', {id: '@id'}, {
        get: {
            method: 'GET',
            url: 'kindPerson/show/:id'
        },
        update: {
            method: 'PUT',
            url: 'kindPerson/update/'
        },
        delete: {
            method: 'DELETE',
            url: 'kindPerson/delete/:id'
        },
        create: {
            method: 'GET',
            url: 'kindPerson/create/'
        },
        save: {
            method: 'POST',
            url: 'kindPerson/save'
        }
    });
});