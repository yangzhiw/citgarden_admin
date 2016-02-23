 'use strict';

angular.module('citygardenWeb1App')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-citygardenWeb1App-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-citygardenWeb1App-params')});
                }
                return response;
            }
        };
    });
