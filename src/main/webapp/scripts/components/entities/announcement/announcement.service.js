'use strict';

angular.module('citygardenWeb1App')
    .factory('Announcement', function ($resource, DateUtils) {
        return $resource('api/announcements/:id', {}, {
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
