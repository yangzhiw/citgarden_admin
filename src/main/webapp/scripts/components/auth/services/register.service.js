'use strict';

angular.module('citygardenWeb1App')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


