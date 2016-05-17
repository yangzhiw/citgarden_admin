'use strict';

angular.module('citygardenWeb1App')
    .factory('RePertoryManager', function ($resource, DateUtils) {
        return $resource('api/rePertoryManagers/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
