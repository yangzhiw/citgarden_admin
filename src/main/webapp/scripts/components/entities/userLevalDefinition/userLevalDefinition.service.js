'use strict';

angular.module('citygardenWeb1App')
    .factory('UserLevalDefinition', function ($resource, DateUtils) {
        return $resource('api/userLevalDefinitions/:id', {}, {
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
