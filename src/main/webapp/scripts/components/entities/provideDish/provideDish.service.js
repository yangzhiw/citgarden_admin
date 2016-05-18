'use strict';

angular.module('citygardenWeb1App')
    .factory('ProvideDish', function ($resource, DateUtils) {
        return $resource('api/provideDishs/:id', {}, {
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
