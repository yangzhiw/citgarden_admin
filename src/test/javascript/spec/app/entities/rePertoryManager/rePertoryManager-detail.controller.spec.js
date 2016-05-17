'use strict';

describe('Controller Tests', function() {

    describe('RePertoryManager Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockRePertoryManager;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockRePertoryManager = jasmine.createSpy('MockRePertoryManager');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'RePertoryManager': MockRePertoryManager
            };
            createController = function() {
                $injector.get('$controller')("RePertoryManagerDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'citygardenWeb1App:rePertoryManagerUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
