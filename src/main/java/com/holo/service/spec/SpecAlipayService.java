/*
package com.holo.service.spec;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.holo.common.bean.ApAppapiPaymentParam;




*/
/**
 * 
 * @author Holo
 * 2017年10月16日
 * 阿里支付  两种方式
 * app支付 调用方法获取一个String 剩下的交给app去做
 * 网页支付   调用方法获取form
 *//*

public class SpecAlipayService {
	private  final Log logger = LogFactory.getLog(getClass());
	
	// 字符编码格式 目前支持 gbk 或 utf-8
	static String INPUT_CHARSET = "utf-8";
	// 签名方式 不需修改
	static String SIGN_TYPE = "RSA";
	// 支付宝的公钥
	static String ALIPAY_PUBKEY_QUERY2017="";

	*/
/**
	 * 根据支付宝app支付参数，进行签名等工作，获取手机需要的orderinfo(orderStr  主要包含商户的订单信息，key=“value”形式，以&连接。 )
	 * @param apAppapiPaymentParam 支付宝app支付参数
	 * @return String 主要包含商户的订单信息，key=“value”形式，以&连接
	 * @throws UnsupportedEncodingException 
	 * @throws AlipayApiException 
	 *//*

	public String takeOrderinfo(ApAppapiPaymentParam apAppapiPaymentParam) throws UnsupportedEncodingException, AlipayApiException{
		StringBuffer orderInfo = new StringBuffer();
		// 签约合作者身份ID
		orderInfo.append("partner=" + "\"" + apAppapiPaymentParam.getPartner() + "\"");
		// 签约卖家支付宝账号
		orderInfo.append("&seller_id=" + "\"" + apAppapiPaymentParam.getSellerId() + "\"");
		// 商户网站唯一订单号
		orderInfo.append("&out_trade_no=" + "\"" + apAppapiPaymentParam.getOutTradeNo() + "\"");
		// 商品名称
		orderInfo.append("&subject=" + "\"" + apAppapiPaymentParam.getSubject() + "\"");
		// 商品详情
		orderInfo.append("&body=" + "\"" + apAppapiPaymentParam.getBody() + "\"");
		// 商品金额
		orderInfo.append("&total_fee=" + "\"" + apAppapiPaymentParam.getTotalFee() + "\"");
		// 服务器异步通知页面路径
		orderInfo.append("&notify_url=" + "\"" + URLEncoder.encode(apAppapiPaymentParam.getNotifyUrl(), "UTF-8") + "\"");
		// 服务接口名称， 固定值
		orderInfo.append("&service=\"" + apAppapiPaymentParam.getService() + "\"");
		// 支付类型， 固定值
		orderInfo.append("&payment_type=\"" + apAppapiPaymentParam.getPaymentType() + "\"");
		// 参数编码， 固定值
		orderInfo.append("&_input_charset=\"" + apAppapiPaymentParam.getInputCharset() + "\"");
		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		orderInfo.append("&it_b_pay=\"1d\"");
		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		orderInfo.append("&return_url=\"m.alipay.com\"");
		// 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
		// orderInfo += "&paymethod=\"e	xpressGateway\"";
		//String sign=RSA.sign(orderInfo.toString(), propMe.getAlipayprivatekey(), INPUT_CHARSET);
		//String sign=AlipaySignature.rsaSign(params, propalipay.getAppprivatekey(), PropAlipay.CHARSET);
		String sign=AlipaySignature.rsaSign(orderInfo.toString(), "Alipayprivatekey()", "utf-8");
		orderInfo.append("&sign=\"" + URLEncoder.encode(sign, "UTF-8"));
		orderInfo.append("\"&sign_type=\"RSA\"");
		return orderInfo.toString();
	}
	
	*/
/**
	 * 商品订单转支付宝参数
	 * 4 后台根据订单OrderrXxx生成WaUnifiedPaymentParam 微信统一下单请求对象
	 * 订单转微信统一下单请求对象
	 * @param orderr
	 * @return ApAppapiPaymentParam 支付宝app支付参数
	 * @throws BoException
	 * @throws Exception
	 *//*

	private ApAppapiPaymentParam orderrToApAppapiPaymentParam()throws Exception{
		String body="";//商品或者支付单简要说明
		String outTradeNo="AA"+String.valueOf(123);//订单号=2位字母+订单id

		//支付异步通知回调地址
		String notifyUrl ="";
		//notifyUrl =UrlUtil.urlAdd(FreeMarkerProp.wbase,"/pub/ali/alipaynotify");
		logger.debug("notifyUrl=" + notifyUrl);
		//精简构造 支付宝app支付参数
		ApAppapiPaymentParam apAppapiPaymentParam = new ApAppapiPaymentParam(
				"propMe.getAlipayservice()" , //String 接口名称  接口名称，固定值。 
				"propMe.getAlipaypartner()" , //String 合作者身份ID  签约的支付宝账号对应的支付宝唯一用户号。以2088开头的16位纯数字组成。 
				"utf-8" , //String 参数编码字符集 default=utf-8 商户网站使用的编码格式，固定为utf-8。 
				"RSA" , //String 签名方式 default=RSA 签名类型，目前仅支持RSA。 
				"" , //String 签名   
				notifyUrl , //String 服务器异步通知页面路径  支付宝服务器主动通知商户网站里指定的页面http路径。 
				outTradeNo , //String 商户网站唯一订单号  支付宝合作商户网站唯一订单号。 
				body , //String 商品名称  商品的标题/交易标题/订单标题/订单关键字等。该参数最长为128个汉字。 
				"1" , //String 支付类型 default=1 支付类型。默认值为：1（商品购买）。 
				"propMe.getAlipaysellerid()" , //String 卖家支付宝账号  卖家支付宝账号（邮箱或手机号码格式）或其对应的支付宝唯一用户号（以2088开头的纯16位数字）。 
				12.3 , //Double 总金额 default=0.0 该笔订单的资金总额，单位为RMB-Yuan。取值范围为[0.01，100000000.00]，精确到小数点后两位。 
				body , //String 商品详情  对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。 
				null
				);
		System.err.println("apAppapiPaymentParam="+apAppapiPaymentParam);

		return apAppapiPaymentParam;
	}
}
*/
