let app = angular.module("app", []);
angular.module('app').constant('SERVER_URL','/restful_api_javaee_angularjs_war/webapi/product');

app.controller("appController",  function ($scope, $http, SERVER_URL) {

    $scope.products = [];
    _refreshPageData();

    $scope.save = function () {
        $scope.updateDetail = false;
        let method;
        let data = {};
        let url;

        data.name = $scope.name;
        data.quantity = $scope.quantity;
        data.value = $scope.value;
        $scope.message = 'Product added!';

        if ($scope.id>=0){
            $scope.message = 'Product data updated!';
            url = SERVER_URL + "/update";
            method = "PUT";
            data.id = $scope.id;
        }

        else
        {   url = SERVER_URL+"/save";
            method = "POST";
        }

        $http({
            method: method,
            url: url,
            data: angular.toJson(data),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then (function success(){
                $scope.errorMessage = '';
                alert($scope.message);
                _refreshPageData();
                _clearForm();

            },
            function error(){
                $scope.errorMessage = 'Error adding product!';
                $scope.message = '';
                alert($scope.errorMessage);
            });
    };


    $scope.update = function (product){
        $scope.updateDetail = true;
        $scope.id = product.id;
        $scope.name = product.name;
        $scope.quantity = product.quantity;
        $scope.value = product.value;

    };

    $scope.remove = function (product) {
        let method;
        let data = {};
        let url;

        method = "DELETE";
        url = SERVER_URL+"/delete";
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
        }).then (function success() {
                $scope.message = 'Product deleted!';
                $scope.errorMessage='';
                alert($scope.message);
                _refreshPageData();
                _clearForm()

            },
            function error() {
                $scope.errorMessage = 'Error deleting product!';
                $scope.message='';
                alert($scope.errorMessage);
        });
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

    //Clear the form
    function _clearForm() {
        $scope.name = "";
        $scope.quantity = "";
        $scope.value = "";

    }

});



