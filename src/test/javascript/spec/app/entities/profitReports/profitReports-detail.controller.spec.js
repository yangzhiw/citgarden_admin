'use strict';

describe('Controller Tests', function() {

    describe('ProfitReports Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockProfitReports;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockProfitReports = jasmine.createSpy('MockProfitReports');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'ProfitReports': MockProfitReports
            };
            createController = function() {
                $injector.get('$controller')("ProfitReportsDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'citygardenWeb1App:profitReportsUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
