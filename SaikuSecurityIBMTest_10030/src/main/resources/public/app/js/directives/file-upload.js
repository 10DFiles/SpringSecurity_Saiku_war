/**
 * Created by Henrikh Kantuni on 4/25/16.
 */

app.directive('fileUpload', ['$parse', function ($parse) {
  return {
    restrict: 'A',
    link: function (scope, elem, attrs) {
      elem.bind('change', function () {
        $parse(attrs.fileUpload).assign(scope, elem[0].files);
        scope.$apply();
        scope.uploadFile(elem, scope.images);
      });
    }
  };
}]);