'use strict';

/**
 *配置开始
 */

var appConfig = window.appConfig || {};

appConfig.debugState = false; //调试
appConfig.api = restbase; //'api'; //Rest地址
appConfig.menu_speed = 200; //速度
appConfig.smartSkin = "smart-style-2"; //默认风格
appConfig.DEFPAGESIZE=50; //一页默认几条
appConfig.PRJCNAME = "易健"; //项目中文名

appConfig.skins = [
    {name: "smart-style-0",
        logo: "styles/img/logo.png",
        class: "btn btn-block btn-xs txt-color-white margin-right-5",
        style: "background-color:#4E463F;",
        label: "Smart Default"},

    {name: "smart-style-1",
        logo: "styles/img/logo-white.png",
        class: "btn btn-block btn-xs txt-color-white",
        style: "background:#3A4558;",
        label: "Dark Elegance"},

    {name: "smart-style-2",
        logo: "styles/img/logo-blue.png",
        class: "btn btn-xs btn-block txt-color-darken margin-top-5",
        style: "background:#fff;",
        label: "Ultra Light"},

    {name: "smart-style-3",
        logo: "styles/img/logo-pale.png",
        class: "btn btn-xs btn-block txt-color-white margin-top-5",
        style: "background:#f78c40",
        label: "Google Skin"},

    {name: "smart-style-4",
        logo: "styles/img/logo-pale.png",
        class: "btn btn-xs btn-block txt-color-white margin-top-5",
        style: "background: #bbc0cf; border: 1px solid #59779E; color: #17273D !important;",
        label: "PixelSmash"},

    {name: "smart-style-5",
        logo: "styles/img/logo-pale.png",
        class: "btn btn-xs btn-block txt-color-white margin-top-5",
        style: "background: rgba(153, 179, 204, 0.2); border: 1px solid rgba(121, 161, 221, 0.8); color: #17273D !important;",
        label: "Glass"},

    {name: "smart-style-6",
        logo: "styles/img/logo-pale.png",
        class: "btn btn-xs btn-block txt-color-white margin-top-5",
        style: "background: #2196F3; border: 1px solid rgba(121, 161, 221, 0.8); color: #FFF !important;",
        beta: true,
        label: "MaterialDesign"
    }
];



appConfig.sound_path = "sound/";
appConfig.sound_on = true;

/*
 * DEBUGGING MODE
 * debugState = true; will spit all debuging message inside browser console.
 * The colors are best displayed in chrome browser.
 */
appConfig.debugStyle = 'font-weight: bold; color: #00f;';
appConfig.debugStyle_green = 'font-weight: bold; font-style:italic; color: #46C246;';
appConfig.debugStyle_red = 'font-weight: bold; color: #ed1c24;';
appConfig.debugStyle_warning = 'background-color:yellow';
appConfig.debugStyle_success = 'background-color:green; font-weight:bold; color:#fff;';
appConfig.debugStyle_error = 'background-color:#ed1c24; font-weight:bold; color:#fff;';

window.appConfig = appConfig;

$.sound_path = appConfig.sound_path;
$.sound_on = appConfig.sound_on;

/**
 * 配置完成
 */




/**
 * module太长了，定义一下。
 */
var app=angular.module('zusa', [
    'ngSanitize',
    'ngAnimate',
    'restangular',
    'ui.router',
    'ui.bootstrap',
    //,

    // Smartadmin Angular Common Module
    //'SmartAdmin'
    //,

    // App
    'zusa.sig' //单页
    //'zusa.config',
    //'zusa.controllers',
    //'zusa.services'
]);

/**
 * 单页的module
 */
var app_sig=angular.module('zusa.sig', [
    'ui.router',
    'ui.bootstrap'
    //,

    // Smartadmin Angular Common Module
    //'SmartAdmin'
    //,

    // App
    //'zusa.config',
    //'zusa.controllers',
    //'zusa.services'
]);
/**
 * 环境变量
 */
app.constant('ENV', window.appConfig);

//var appconfig=angular.module("zusa.config", []);
//var appctrl=angular.module('zusa.controllers', []);
//var appservice=angular.module('zusa.services', []);


//////下面是固定对象

/**
 * 客户端信息对象，此对象本身是个模板，实例化后会进行本地保存，不同类型客户端请修改cli
 * @type {{ver: number, cli: number, openid: string, openidmd5: string, token: string}}
 * @private
 */
var _ClientInfo=
{
    /**客户端版本号*/
    ver:1,
    /**客户端类型0/NULL为H5,1安卓,2苹果,3微信*/
    cli:0,
    /**客户token*/
    token:''
};
/**微信H5支付请求*/
var _WaJsapiPaymentParam={appid:"",timestamp1:0,noncestr:"",package1:"",signtype:"MD5",paysign:""}
/**
 **当前页码，从1开始
 *Integer pageNo;
 **每页数据量
 *Integer pageSize;
 **排序方式，ex. id desc
 *String orderstr;
 **查询条件
 *String where;
 **指令
 *String cmd;
 * @type {{where: string, pageNo: number, pageSize: number, hasNextPage: boolean, cmd: string}}
 * @private
 */
var _Page = {
    where: '', //条件
    pageNo: 1, //第几页，从1开始
    pageSize:appConfig.DEFPAGESIZE, //每页多少数量
    hasNextPage: true, //是否还有下一页
    cmd: 'USA', //命令
    dbsize:0, //记录总数量
   totalpage:null // 总页
};
//////下面是固定变量名

/**客户端消息*/
var CLIENT_INFO="CLIENT_INFO";
/**本地保存的登录用户信息 user{user,member/admin/} */
var LOCAL_USER="LOCAL_USER";
/**本地保存的临时OBJ,用于页面传递*/
var LOCAL_TMP_OBJ="LOCAL_TMP_OBJ";


