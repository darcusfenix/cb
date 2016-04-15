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
        toAssign: {
            method: 'POST',
            url: 'bracelet/toAssignForSalesman'
        },
        history: {
            method: 'GET',
            isArray: true,
            url: 'bracelet/history'
        },
        costs: {
            method: 'GET',
            isArray: true,
            url: 'bracelet/costs'
        },
        getMyAssignmentsSold: {
            method: 'GET',
            isArray: true,
            url: 'bracelet/salesman/sold'
        },
        historyBySalesman: {
            method: 'GET',
            isArray: true,
            url: 'bracelet/salesman/history'
        },
        getResumeHistoryByDate: {
            method: 'GET',
            isArray: true,
            url: 'bracelet/salesman/history/resume'
        },
        getBraceletsNotSold: {
            method: 'GET',
            isArray: true,
            url: 'bracelet/salesman/notSold'
        },
        saveCorteCaja: {
            method: 'POST',
            url: 'salesman/corteCaja'
        },
        getBraceletsYesSold: {
            method: 'GET',
            isArray: true,
            url: 'bracelet/salesman/yesSold'
        },
        getHistory:{
            method: 'GET',
            url: 'bracelet/getHistorySalesman'
        },
        findByIdOrCode:{
            method: 'GET',
            url: 'bracelet/findByIdOrCode'
        },
        findByIdOrCodeWithRange:{
            method: 'GET',
            isArray: true,
            url: 'bracelet/findByIdOrCodeWithRange'
        }
    });
});