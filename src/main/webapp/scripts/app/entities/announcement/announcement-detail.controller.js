'use strict';

angular.module('citygardenWeb1App')
    .controller('AnnouncementDetailController', function ($scope, $rootScope, $stateParams, entity, Announcement) {
        $scope.announcement = entity;
        $scope.load = function (id) {
            Announcement.get({id: id}, function(result) {
                $scope.announcement = result;
            });
        };
        var unsubscribe = $rootScope.$on('citygardenWeb1App:announcementUpdate', function(event, result) {
            $scope.announcement = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
