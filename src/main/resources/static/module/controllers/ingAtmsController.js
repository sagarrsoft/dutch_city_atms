
angular.module('ingAtmsApp').controller('ingAtmsController', [ '$scope', 'exDialog', '$location',
    function ( $scope, exDialog, $location) {

        $scope.$on('$locationChangeStart', function (event, newUrl, oldUrl) {
            if (newUrl !== oldUrl && exDialog.hasOpenDialog()) {
                exDialog.closeAll();

            }
        });
    }
]);

angular.module('ingAtmsApp').controller('headerController', ['loginService', '$scope', '$rootScope', '$state', 'loginService', 'notificationFactory',
    function headerController(loginService, $scope, $rootScope, $state, loginService, notificationFactory) {

        $rootScope.logedInUserName = loginService.getLogedInUserName();

        function logoutSuccessAction() {
            $state.go("login");
        }

        function logoutErrorAction(error) {
            notificationFactory.handleError($scope, error);
        }

        $scope.logout = function () {
            loginService.logout(logoutSuccessAction, logoutErrorAction);
        };

        $scope.goHome = function () {
            switch (loginService.getLogedInRole()) {
                case "ADMIN":
                    return $state.go("admin-home");
                    break;
                default:
                    return $state.go("login");
            }
        };
    }
]);
/* Setup Layout Part - Footer */
angular.module('ingAtmsApp').controller('footerController', ['$scope',
    function ($scope) {
    }
]);