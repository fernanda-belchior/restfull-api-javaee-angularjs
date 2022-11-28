var app = angular.module("app", []);
angular.module('app').constant('SERVER_URL','/restful_api_javaee_angularjs_war/webapi/product');

app.controller("appController",  function ($scope, $http, SERVER_URL) {

    $scope.products = [];
    _refreshPageData();


    $scope.save = function (id) {
        var method = "";
        var data = {};
        var url;

        data.name = $scope.name;
        data.quantity = $scope.quantity;
        data.value = $scope.value;

        if (id=="") {
            url = "/save";
            method = "POST";
            url = SERVER_URL + url;
        }
        else
        {
            document.getElementById("updateDetail").style.display = "none";
            url = SERVER_URL + "/update";
            method = "PUT";
            data.id = $scope.id;
        }

        $http({
            method: method,
            url: url,
            data: angular.toJson(data),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
    };


    $scope.update = function (product){
        document.getElementById("updateDatails").style.display = "block";
        $scope.id = product.id;
        $scope.name = product.name;
        $scope.quantity = product.quantity;
        $scope.value = product.value;
    };

    $scope.remove = function (product) {
        var method = "";
        var url = "/delete";
        var data = {};

        method = "DELETE";
        url = SERVER_URL+url;
        data.id= product.id;
        data.name = product.name;
        data.quantity = product.quantity;
        data.value = product.value;

        $http({
            method: method,
            url: url,
            data: angular.toJson(data),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
    };

    /* Private Methods */

    //HTTP GET- get all customers collection
    function _refreshPageData() {
        $http({
            method: 'GET',
            url: SERVER_URL+"/list"
        }).then(function successCallback(response) {
            $scope.products = response.data;
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    }

    function _success(response) {
        _refreshPageData();
        _clearForm()
    }

    function _error(response) {
        alert(response.data.message || response.statusText);
    }

    //Clear the form
    function _clearForm() {
        $scope.name = "";
        $scope.quantity = "";
        $scope.value = "";
        $scope.id = "";
    }
});




