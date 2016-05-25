'use strict';

describe('Controller Tests', function() {

    describe('UserLevalDefinition Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockUserLevalDefinition;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockUserLevalDefinition = jasmine.createSpy('MockUserLevalDefinition');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'UserLevalDefinition': MockUserLevalDefinition
            };
            createController = function() {
                $injector.get('$controller')("UserLevalDefinitionDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'citygardenWeb1App:userLevalDefinitionUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
