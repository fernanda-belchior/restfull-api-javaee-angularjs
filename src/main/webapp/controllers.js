//
// angular.module('restfulApi',["ngResource"]).controller('restfulControler',
//     ["ngResource", "$scope", function ($resource, $scope) {
//     var angularResource = $resource("/restful_api_javaee_angularjs_war/webapi/product");
//     var angulrResourceParam = $resource("/restful_api_javaee_angularjs_war/webapi/product/:id", {id : "@id"})
// }]);
//
// $scope.product = angularResource.query(function(){});
// $scope.save = function() {
//     var newResource = new angularResource($scope.product);
//     newResource.$save(function(){
//         $scope.atendimentos.push(newResource);
//         $scope.novo();
//     });
// }
//
// $scope.update = function() {
//     var newResource = new angularResource($scope.atendimento);
//     newResource.$save(function(){
//         $scope.novo();
//     });
// }
// 24.
// 25.  $scope.excluir = function() {
//     26.   var newResource = new angularResourceParam($scope.atendimento);
//     27.   newResource.$delete(function() {
//         28.     $scope.atendimentos.splice($scope.atendimentos.indexOf($scope.atendimento), 1);
//         29.  $scope.novo();
//     });
// }
// 32.
// 33.  $scope.novo = function () {
//     34.   $scope.atendimento = "";
//     35.  };
// 36.
// 37.  $scope.seleciona = function (atendimento) {
//     38.   $scope.atendimento = atendimento;
//     39.  };
// 40. }])
