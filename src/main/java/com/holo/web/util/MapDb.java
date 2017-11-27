package com.holo.web.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.holo.common.util.JsonUtilAli;
/**
 * 数据库表里的一些，字段属性ISO，如性别之男女
 * @author zmax
 *
 */
public class MapDb {
	/**
	 * 由表设计时就进行确定的标准，格式:Db.TableName.ColumnName，各项首字大写
	 */
	public void init(){
		mymap.put("Memberdoctor.StatusDm",JsonUtilAli.getMapFromJson("{'0':'已取消关注','1':'扫码关系','2':'空中诊室关注'}"));
		mymap.put("Memberdoctor.ItypeFirst",JsonUtilAli.getMapFromJson("{'0':'否','1':'是'}"));
		mymap.put("Dmmsg.Status",JsonUtilAli.getMapFromJson("{'0':'已撤回','1':'普通消息','2':'仅会员有的消息','3':'仅医生有的消息'}"));
		mymap.put("Dmmsg.Readed",JsonUtilAli.getMapFromJson("{'0':'未读','1':'已读'}"));
		mymap.put("Dmmsg.Fromto",JsonUtilAli.getMapFromJson("{'0':'患者发给医生','1':'医生发给患者'}"));
		mymap.put("Dgmmsg.Readed",JsonUtilAli.getMapFromJson("{'0':'未读','1':'已读'}"));
		mymap.put("Dgmmsg.Fromto",JsonUtilAli.getMapFromJson("{'0':'患者发给药师','1':'药师发给患者'}"));
		mymap.put("Drugstore.Attr",JsonUtilAli.getMapFromJson("{'加盟':'加盟','直营':'直营','签约':'签约'}"));
		mymap.put("SellerCount.Itype",JsonUtilAli.getMapFromJson("{'0':'日统计','1':'月统计'}"));
		mymap.put("WaEntityArticle.Status",JsonUtilAli.getMapFromJson("{'0':'无效','1':'上线'}"));
		mymap.put("Wxmenu.Mtype",JsonUtilAli.getMapFromJson("{'menu':'菜单','button':'一级菜单','sub_button':'二级菜单'}"));
		mymap.put("Wxmenu.Type",JsonUtilAli.getMapFromJson("{'click':'点击推时间','view':'跳转URL','scancode_push':'扫码推事件','scancode_waitmsg':'扫码推事件且弹出消息接收中提示框','pic_sysphoto':'弹出系统拍照发图','pic_photo_or_album':'弹出拍照或者相册发图','pic_weixin':'弹出微信相册发图器','location_select':'弹出地理位置选择器','media_id':'下发消息（除文本消息）','view_limited':'跳转图文消息URL'}"));
		mymap.put("WaRecvmsg.Msgtype",JsonUtilAli.getMapFromJson("{'text':'文本','image':'图片','voice':'音频','video':'视频','location':'地理位置','link':'链接','event':'事件'}"));
		mymap.put("WaRecvmsg.Event",JsonUtilAli.getMapFromJson("{'subscribe':'订阅','unsubscribe':'取消订阅','SCAN':'扫描二维码','LOCATION':'上报地理','CLICK':'点击菜单','VIEW':'菜单跳转'}"));
		mymap.put("Oplog.Itype",JsonUtilAli.getMapFromJson("{'0':'其它','1':'增加','2':'删除','3':'修改','4':'查询','5':'列表','6':'其它查询','7':'其它修改','8':'批量修改'}"));
		mymap.put("Wxcfg.Itype",JsonUtilAli.getMapFromJson("{'1':'服务号','2':'企业号','3':'订阅号'}"));
		mymap.put("Wxr.RoleId",JsonUtilAli.getMapFromJson("{'0':'超管','1':'一般管理员','2':'药企商户','3':'操作员','4':'经销商','5':'会员','6':'医生','7':'商品提供商','8':'业务员','9':'药剂师'}"));
		mymap.put("Wxrb.Status",JsonUtilAli.getMapFromJson("{'0':'初创','1':'业务已处理'}"));
		mymap.put("Wxouser.Statusuf",JsonUtilAli.getMapFromJson("{'0':'只有openid','1':'完全版'}"));
		mymap.put("Wxouser.Sex",JsonUtilAli.getMapFromJson("{'1':'男','2':'女','0':'未知'}"));
		mymap.put("Wxouser.Subscribe",JsonUtilAli.getMapFromJson("{'0':'没有关注该公众号','1':'关注过了该公众号'}"));
		mymap.put("Wxousersubscribehis.Act",JsonUtilAli.getMapFromJson("{'0':'取消关注','1':'关注'}"));
		mymap.put("WaQrcodeticketScene.StatusTemporary",JsonUtilAli.getMapFromJson("{'0':'是临时二维码','1':'是永久二维码'}"));
		mymap.put("WaQrcodeticketScene.StatusValid",JsonUtilAli.getMapFromJson("{'0':'无效','1':'有效'}"));
		mymap.put("WaQradv.Rettype",JsonUtilAli.getMapFromJson("{'0':'无返回','1':'返回一个纯文本','2':'返回一个图文'}"));
		mymap.put("Product.StatusRecipe",JsonUtilAli.getMapFromJson("{'0':'非处方药','1':'处方药'}"));
		mymap.put("Product.StatusUsed",JsonUtilAli.getMapFromJson("{'0':'外用','1':'口服','2':'含服','3':'咀嚼'}"));
		mymap.put("Product.StatusMaterial",JsonUtilAli.getMapFromJson("{'0':'原材料','1':'成品'}"));
		mymap.put("Product.ItypeMi",JsonUtilAli.getMapFromJson("{'0':'非医保','1':'医保类'}"));
		mymap.put("Product.ItypeAgent",JsonUtilAli.getMapFromJson("{'0':'非推广','1':'推广'}"));
		mymap.put("Product.Punit",JsonUtilAli.getMapFromJson("{'0':'件','1':'个','2':'箱','3':'盒','4':'瓶','5':'片','6':'包','7':'袋','8':'罐','9':'支','10':'克','11':'对','12':'条','13':'听'}"));
		mymap.put("MedicalrecRpt.Ttype",JsonUtilAli.getMapFromJson("{'0':'血常规','1':'尿常规','2':'肝功能','3':'肾功能','4':'血脂','5':'血糖','6':'电解质','7':'甲状腺功能','8':'肝炎系列','9':'乙肝三系'}"));
		mymap.put("User.Status",JsonUtilAli.getMapFromJson("{'0':'正在申请','1':'有效','-1':'申请被拒','-2':'删除'}"));
		mymap.put("User.RoleId",JsonUtilAli.getMapFromJson("{'0':'超管','1':'一般管理员','2':'药企商户','3':'操作员','4':'经销商','5':'会员','6':'医生','7':'商品提供商','8':'业务员','9':'药剂师','10':'卫生局'}"));
		mymap.put("Member.Usersex",JsonUtilAli.getMapFromJson("{'0':'男','1':'女'}"));
		mymap.put("Member.Idtype",JsonUtilAli.getMapFromJson("{'0':'身份证','1':'军官证','2':'士兵证','3':'护照','4':'身份证'}"));
		mymap.put("Member.StatusRelation",JsonUtilAli.getMapFromJson("{'0':'无','1':'有'}"));
		mymap.put("Member.Ilastdev",JsonUtilAli.getMapFromJson("{'1':'安卓','2':'苹果'}"));
		mymap.put("Doctor.Dlevel",JsonUtilAli.getMapFromJson("{'0':'医师','1':'主治医生','2':'副主任医师','3':'主任医师'}"));
		mymap.put("Doctor.Usersex",JsonUtilAli.getMapFromJson("{'0':'男','1':'女'}"));
		mymap.put("Doctor.Idtype",JsonUtilAli.getMapFromJson("{'0':'身份证','1':'军官证','2':'士兵证','3':'护照','4':'身份证'}"));
		mymap.put("Doctor.Itype",JsonUtilAli.getMapFromJson("{'0':'签约医生','1':'平台医生'}"));
		mymap.put("Doctor.ItypeIn",JsonUtilAli.getMapFromJson("{'0':'经济人医生','1':'自入医生'}"));
		mymap.put("Doctor.ItypeAir",JsonUtilAli.getMapFromJson("{'0':'空中诊室医生','1':'非空中诊室医生'}"));
		mymap.put("Doctor.StatusAir",JsonUtilAli.getMapFromJson("{'0':'否','1':'是'}"));
		mymap.put("Doctor.StatusDuty",JsonUtilAli.getMapFromJson("{'0':'否','1':'是'}"));
		mymap.put("Doctor.StatusRelation",JsonUtilAli.getMapFromJson("{'0':'无','1':'有'}"));
		mymap.put("Doctor.Ilastdev",JsonUtilAli.getMapFromJson("{'1':'安卓','2':'苹果'}"));
		mymap.put("Druggist.Dlevel",JsonUtilAli.getMapFromJson("{'0':'副主任','1':'主任','2':'执业中药师','3':'执业药师'}"));
		mymap.put("Druggist.Usersex",JsonUtilAli.getMapFromJson("{'0':'男','1':'女'}"));
		mymap.put("Druggist.Idtype",JsonUtilAli.getMapFromJson("{'0':'身份证','1':'军官证','2':'士兵证','3':'护照'}"));
		mymap.put("Seller.Dlevel",JsonUtilAli.getMapFromJson("{'0':'专员业务员','1':'地区经理','2':'省区经理','3':'大区经理','4':'总监','9':'合作企业','8':'地区经理'}"));
		mymap.put("Seller.Usersex",JsonUtilAli.getMapFromJson("{'男':'男','女':'女'}"));
		mymap.put("Permittable.RoleId",JsonUtilAli.getMapFromJson("{'0':'超管','1':'一般管理员','2':'药企商户','3':'操作员','4':'经销商','5':'会员','6':'医生','7':'商品提供商','8':'业务员','9':'药剂师','10':'卫生局'}"));
		mymap.put("Permittable.Pall",JsonUtilAli.getMapFromJson("{'0':'无','9':'部分','91':'全部'}"));
		mymap.put("Permittable.Btnnew",JsonUtilAli.getMapFromJson("{'0':'无','9':'部分','91':'全部'}"));
		mymap.put("Permittable.Btnedit",JsonUtilAli.getMapFromJson("{'0':'无','9':'部分','91':'全部'}"));
		mymap.put("Permittable.Btndel",JsonUtilAli.getMapFromJson("{'0':'无','9':'部分','91':'全部'}"));
		mymap.put("Permittable.Btnshow",JsonUtilAli.getMapFromJson("{'0':'无','9':'部分','91':'全部'}"));
		mymap.put("Permitfield.RoleId",JsonUtilAli.getMapFromJson("{'0':'超管','1':'一般管理员','2':'药企商户','3':'操作员','4':'经销商','5':'会员','6':'医生','7':'商品提供商','8':'业务员','9':'药剂师','10':'卫生局'}"));
		mymap.put("Permitfield.Pf41",JsonUtilAli.getMapFromJson("{'0':'无','9':'有','1~sessionobj.id':'1~sessionobj.id'}"));
		mymap.put("Permitfield.Pf42",JsonUtilAli.getMapFromJson("{'0':'无','9':'有'}"));
		mymap.put("Permitfield.Pf51",JsonUtilAli.getMapFromJson("{'0':'无','9':'有'}"));
		mymap.put("Permitfield.Pf52",JsonUtilAli.getMapFromJson("{'0':'无','9':'有'}"));
		mymap.put("StatiAllDoctor.Dlevel",JsonUtilAli.getMapFromJson("{'0':'医师','1':'主治医生','2':'副主任医师','3':'主任医师'}"));
		mymap.put("StatiDoctorDaily.Dlevel",JsonUtilAli.getMapFromJson("{'0':'医师','1':'主治医生','2':'副主任医师','3':'主任医师'}"));
		mymap.put("StatiDoctorMonth.Dlevel",JsonUtilAli.getMapFromJson("{'0':'医师','1':'主治医生','2':'副主任医师','3':'主任医师'}"));
		mymap.put("Orderr.Iotype",JsonUtilAli.getMapFromJson("{'0':'医生药笺','1':'平台医生药笺','2':'用户自采购'}"));
		mymap.put("Orderr.Status",JsonUtilAli.getMapFromJson("{'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'}"));
		mymap.put("Orderr.Statusgoods",JsonUtilAli.getMapFromJson("{'0':'未发货','1':'部分发货','2':'已全部发货','3':'部分收货','4':'已全部收货','-1':'放弃'}"));
		mymap.put("Orderr.ItypeCarriage",JsonUtilAli.getMapFromJson("{'0':'物流发货','1':'到店自提'}"));
		mymap.put("Orderr.ItypePay",JsonUtilAli.getMapFromJson("{'0':'货到付款','1':'门店支付','2':'微信支付','3':'支付宝支付','4':'余额支付'}"));
		mymap.put("Orderr.ItypeBoil",JsonUtilAli.getMapFromJson("{'0':'代煎药','1':'免煎药'}"));
		mymap.put("OrderrFinished.Iotype",JsonUtilAli.getMapFromJson("{'0':'医生药笺','1':'平台医生药笺','2':'用户自采购'}"));
		mymap.put("OrderrFinished.Status",JsonUtilAli.getMapFromJson("{'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'}"));
		mymap.put("OrderrFinished.Statusgoods",JsonUtilAli.getMapFromJson("{'0':'未发货','1':'部分发货','2':'已全部发货','3':'部分收货','4':'已全部收货','-1':'放弃'}"));
		mymap.put("OrderrFinished.ItypeCarriage",JsonUtilAli.getMapFromJson("{'0':'物流发货','1':'到店自提'}"));
		mymap.put("OrderrFinished.ItypePay",JsonUtilAli.getMapFromJson("{'0':'货到付款','1':'门店支付','2':'微信支付','3':'支付宝支付','4':'余额支付'}"));
		mymap.put("OrderrFinished.ItypeBoil",JsonUtilAli.getMapFromJson("{'0':'代煎药','1':'免煎药'}"));
		mymap.put("OrderrDiscard.Iotype",JsonUtilAli.getMapFromJson("{'0':'医生药笺','1':'平台医生药笺','2':'用户自采购'}"));
		mymap.put("OrderrDiscard.Status",JsonUtilAli.getMapFromJson("{'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付'}"));
		mymap.put("OrderrDiscard.Statusgoods",JsonUtilAli.getMapFromJson("{'0':'未发货','1':'部分发货','2':'已全部发货','3':'部分收货','4':'已全部收货','-1':'放弃'}"));
		mymap.put("OrderrDiscard.ItypeCarriage",JsonUtilAli.getMapFromJson("{'0':'物流发货','1':'到店自提'}"));
		mymap.put("OrderrDiscard.ItypePay",JsonUtilAli.getMapFromJson("{'0':'货到付款','1':'门店支付','2':'微信支付','3':'支付宝支付','4':'余额支付'}"));
		mymap.put("OrderrDiscard.ItypeBoil",JsonUtilAli.getMapFromJson("{'0':'代煎药','1':'免煎药'}"));
		mymap.put("Orderrdetail.Iotype",JsonUtilAli.getMapFromJson("{'0':'医生药笺','1':'平台医生药笺','2':'用户自采购'}"));
		mymap.put("Orderrdetail.Status",JsonUtilAli.getMapFromJson("{'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付','-2':'已退货赔付','-3':'退货申请'}"));
		mymap.put("Orderrdetail.Statusgoods",JsonUtilAli.getMapFromJson("{'0':'未发货','1':'部分发货','2':'已全部发货','3':'部分收货','4':'已全部收货','-1':'放弃','-2':'已退货','-3':'退货申请'}"));
		mymap.put("Orderrdetail.Statuscount",JsonUtilAli.getMapFromJson("{'0':'未统计','1':'已统计'}"));
		mymap.put("Orderrdetail.ItypeUse",JsonUtilAli.getMapFromJson("{'0':'无','1':'先煎','2':'后下','3':'包煎','4':'冲服','5':'洋化','6':'打碎','7':'吞服','8':'另煎','9':'另包','10':'研粉'}"));
		mymap.put("Orderrdetail.Punit",JsonUtilAli.getMapFromJson("{'0':'件','1':'个','2':'箱','3':'盒','4':'瓶','5':'片','6':'包','7':'袋','8':'罐','9':'支','10':'克','11':'对','12':'条','13':'听'}"));
		mymap.put("OrderrdetailFinished.Iotype",JsonUtilAli.getMapFromJson("{'0':'医生药笺','1':'平台医生药笺','2':'用户自采购'}"));
		mymap.put("OrderrdetailFinished.Status",JsonUtilAli.getMapFromJson("{'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付','-2':'已退货赔付','-3':'退货申请'}"));
		mymap.put("OrderrdetailFinished.Statusgoods",JsonUtilAli.getMapFromJson("{'0':'未发货','1':'部分发货','2':'已全部发货','3':'部分收货','4':'已全部收货','-1':'放弃','-2':'已退货','-3':'退货申请'}"));
		mymap.put("OrderrdetailFinished.Statuscount",JsonUtilAli.getMapFromJson("{'0':'未统计','1':'已统计'}"));
		mymap.put("OrderrdetailFinished.ItypeUse",JsonUtilAli.getMapFromJson("{'0':'无','1':'先煎','2':'后下','3':'包煎','4':'冲服','5':'洋化','6':'打碎','7':'吞服','8':'另煎','9':'另包','10':'研粉'}"));
		mymap.put("OrderrdetailFinished.Punit",JsonUtilAli.getMapFromJson("{'0':'件','1':'个','2':'箱','3':'盒','4':'瓶','5':'片','6':'包','7':'袋','8':'罐','9':'支','10':'克','11':'对','12':'条','13':'听'}"));
		mymap.put("OrderrdetailDiscard.Iotype",JsonUtilAli.getMapFromJson("{'0':'医生药笺','1':'平台医生药笺','2':'用户自采购'}"));
		mymap.put("OrderrdetailDiscard.Status",JsonUtilAli.getMapFromJson("{'0':'未支付','1':'已发起支付申请','2':'支付成功','-1':'放弃支付','-2':'已退货赔付','-3':'退货申请'}"));
		mymap.put("OrderrdetailDiscard.Statusgoods",JsonUtilAli.getMapFromJson("{'0':'未发货','1':'部分发货','2':'已全部发货','3':'部分收货','4':'已全部收货','-1':'放弃','-2':'已退货','-3':'退货申请'}"));
		mymap.put("OrderrdetailDiscard.Statuscount",JsonUtilAli.getMapFromJson("{'0':'未统计','1':'已统计'}"));
		mymap.put("OrderrdetailDiscard.ItypeUse",JsonUtilAli.getMapFromJson("{'0':'无','1':'先煎','2':'后下','3':'包煎','4':'冲服','5':'洋化','6':'打碎','7':'吞服','8':'另煎','9':'另包','10':'研粉'}"));
		mymap.put("OrderrdetailDiscard.Punit",JsonUtilAli.getMapFromJson("{'0':'件','1':'个','2':'箱','3':'盒','4':'瓶','5':'片','6':'包','7':'袋','8':'罐','9':'支','10':'克','11':'对','12':'条','13':'听'}"));
		mymap.put("UserAddr.Itype",JsonUtilAli.getMapFromJson("{'0':'普通','1':'默认','2':'删除'}"));
		mymap.put("UserAddr.Sex",JsonUtilAli.getMapFromJson("{'0':'男','1':'女'}"));
		mymap.put("Orderrcancel.Status",JsonUtilAli.getMapFromJson("{'0':'申请中','1':'退款完成','2':'无需退款'}"));
		mymap.put("Orderrcancel.Statusgoods",JsonUtilAli.getMapFromJson("{'0':'申请中','1':'申请成功','-1':'驳回','-2':'取消申请'}"));
		mymap.put("Orderrcancel.ReturnReason",JsonUtilAli.getMapFromJson("{'1':'药品破损','2':'药品漏发错发','3':'药品不符','4':'药品质量问题','5':'未收到货','0':'其他'}"));
		mymap.put("OrderrcancelFinished.Status",JsonUtilAli.getMapFromJson("{'0':'申请中','1':'退款完成','2':'无需退款'}"));
		mymap.put("OrderrcancelFinished.Statusgoods",JsonUtilAli.getMapFromJson("{'0':'申请中','1':'申请成功','-1':'驳回','-2':'取消申请'}"));
		mymap.put("OrderrcancelFinished.ReturnReason",JsonUtilAli.getMapFromJson("{'1':'药品破损','2':'药品漏发错发','3':'药品不符','4':'药品质量问题','5':'未收到货','0':'其他'}"));
		mymap.put("OrderrcancelDiscard.Status",JsonUtilAli.getMapFromJson("{'0':'申请中','1':'退款完成','2':'无需退款'}"));
		mymap.put("OrderrcancelDiscard.Statusgoods",JsonUtilAli.getMapFromJson("{'0':'申请中','1':'申请成功','-1':'驳回','-2':'取消申请'}"));
		mymap.put("OrderrcancelDiscard.ReturnReason",JsonUtilAli.getMapFromJson("{'1':'药品破损','2':'药品漏发错发','3':'药品不符','4':'药品质量问题','5':'未收到货','0':'其他'}"));
		mymap.put("Prescript.Status",JsonUtilAli.getMapFromJson("{'0':'无效','1':'有效','-1':'已删除'}"));
		mymap.put("PrescriptProduct.Status",JsonUtilAli.getMapFromJson("{'0':'无效','1':'有效','-1':'已删除'}"));
		mymap.put("PrescriptProduct.ItypeUse",JsonUtilAli.getMapFromJson("{'0':'无','1':'先煎','2':'后下','3':'包煎','4':'冲服','5':'洋化','6':'打碎','7':'吞服','8':'另煎','9':'另包','10':'研粉'}"));
		mymap.put("PrescriptProduct.Punit",JsonUtilAli.getMapFromJson("{'0':'件','1':'个','2':'箱','3':'盒','4':'瓶','5':'片','6':'包','7':'袋','8':'罐','9':'支','10':'克','11':'对','12':'条','13':'听'}"));
		MapSpec.init(mymap);
	}

	/**Map[String,Map[String,String]]版配置列表*/
	private Map<String,Map<String,String>> mymap=new HashMap<String, Map<String,String>>();

	private static MapDb instance = null;   
	//单例
	public static MapDb getInstance(){   
		if(null == instance ) {
			synchronized(MapDb.class){ 
				if(null == instance) 
					instance = new MapDb(); 
			} 
		} 
		return instance;
	}
	
	
	/**
	 * 程序写死的的配置，不能在配置表中修改，否则程序会出错。
	 */
	public MapDb(){
		System.err.println("zzzzzzzzzzzzzzzzz");
		init();
	}

	/**
	 * 根据KEY获取整个map
	 * @param key
	 * @return
	 */
	public Map<String, String> getMap(String key) {
		Map<String, String> ret=mymap.get(key);
		if(ret==null)
			return new HashMap<String, String>();
		return ret;
	}
	/**
	 * 根据key获取map1,再从map1中根据key1获取value
	 * @param key
	 * @param key1
	 * @return
	 */
	public String getMapString(String key,String key1){
		return getMap(key).get(key1);
	}
	
	/**mymap*/
	public Map<String, Map<String, String>> getMymap() {
		return mymap;
	}

	/**mymap*/
	public void setMymap(Map<String, Map<String, String>> mymap) {
		this.mymap = mymap;
	}
}
