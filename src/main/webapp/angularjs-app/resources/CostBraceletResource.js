/**
 * Created by becm on 2/12/16.
 */

angular.module('CapitalBusApp').factory('CostBracelet', function ($resource) {

    return $resource('costBracelet/:id', {id: '@id'}, {
        get: {
            method: 'GET',
            url: 'costBracelet/:id'
        },
        update: {
            method: 'PUT',
            url: 'costBracelet/'
        },
        delete: {
            method: 'DELETE',
            url: 'costBracelet/:id'
        },
        create: {
            method: 'GET',
            url: 'costBracelet/'
        },
        save: {
            method: 'POST',
            url: 'costBracelet'
        },
        getCostBraceletByCircuit: {
            method: 'GET',
            isArray: true,
            url: 'costBracelet/circuit/:id'
        }
    });
});