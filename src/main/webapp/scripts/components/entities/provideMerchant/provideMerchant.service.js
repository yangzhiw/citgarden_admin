'use strict';

angular.module('citygardenWeb1App')
    .factory('ProvideMerchant', function ($resource, DateUtils) {
        return $resource('api/provideMerchants/:id', {}, {
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
