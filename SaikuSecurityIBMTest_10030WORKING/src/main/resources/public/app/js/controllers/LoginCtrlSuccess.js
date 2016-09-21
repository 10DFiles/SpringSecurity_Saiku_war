/**
 * @author Adonay
 * @date Mar'15 2016
 *
 */

app.controller('LoginCtrlSuccess', ['Facebook','$modal','$log', '$scope', '$http', '$location', '$cookieStore', 'RestURL','authFactory',
    function (Facebook,$modal, $log, $scope, $http, $location, $cookieStore, RestURL,authFactory) {

        var self = $scope;
        authFactory.global.loggedIn = true;
        $location.path('/');
    }]);
