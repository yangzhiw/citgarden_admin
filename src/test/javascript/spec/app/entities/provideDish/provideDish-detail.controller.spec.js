'use strict';

describe('Controller Tests', function() {

    describe('ProvideDish Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockProvideDish;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockProvideDish = jasmine.createSpy('MockProvideDish');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'ProvideDish': MockProvideDish
            };
            createController = function() {
                $injector.get('$controller')("ProvideDishDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'citygardenWeb1App:provideDishUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
