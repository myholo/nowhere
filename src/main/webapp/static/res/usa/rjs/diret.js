/**
 * 自定义标签 通用版
 * Created by zmax on 2017/2/7.
 */

'use strict';

/**
 * 智能加载页面
 * ex. <div data-smart-include="views/zlayout/header.tpl.html" class="placeholder-header"></div>
 */
app.directive('smartInclude', function () {
        return {
            replace: true,
            restrict: 'A',
            templateUrl: function (element, attr) {
                return attr.smartInclude;
            },
            compile: function(element){
                element[0].className = element[0].className.replace(/placeholder[^\s]+/g, '');
            }
        };
    }
)
/**
 * 让herf无效，不会点到根目录上去
 * ex.<a href-void>XXX</a>
 */
app.directive('hrefVoid', function () {
        return {
            restrict: 'A',
            link: function (scope, element, attributes) {
                element.attr('href','#');
                element.on('click', function(e){
                    e.preventDefault();
                    e.stopPropagation();
                })
            }
        }
    });

/**
 * 动态编译，可以在html中放置ng-click等
 * see http://stackoverflow.com/questions/18157305/angularjs-compiling-dynamic-html-strings-from-database
 * 使用方法 <div dynamic="obj.msg"></div>
 */
app.directive('dynamic', function ($compile) {
        return {
            restrict: 'A',
            replace: true,
            link: function (scope, ele, attrs) {
                scope.$watch(attrs.dynamic, function(html) {
                    ele.html(html);
                    $compile(ele.contents())(scope);
                });
            }
        };
    });
/**
 *防重复提交 使用方法在标签中写 forbid-Re-Submit
 * <button forbid-Re-Submit ng-click ="xxx()">按钮</button>
 */
app.directive('forbidReSubmit', function($timeout) {
        return {
            restrict: 'A',
            link: function($scope, $element, $attributes) {
                $element.on('$destroy', function() {
                    $element.attr('disabled', false);
                });
                $element.bind('click', function() {
                    $timeout(function() {
                        $element.attr('disabled', true);
                    }, 5000);
                });
            }
        };
    })