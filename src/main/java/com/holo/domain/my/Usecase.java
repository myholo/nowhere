/*
 * zmax 
 * 
 */


//  
package com.holo.domain.my;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.holo.domain.base.BaseEntity;


/**
 * 用例
 * @author zmax
 * @version 1.0
 * @since 
 */

@Entity
@Table(name = "usecase")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)//可读可写
public class Usecase extends BaseEntity{
	
	//alias
	public static final String TABLE_ALIAS = "用例";

	//date formats
	public static final String FORMAT_GMT_CREATE = DATE_FORMAT;
	
	//columns START
	/**序号 Integer   */
	@Max(99999999999L)
	
	private Integer id;
	/**创建时间 Date   */
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtCreate;
	/**标题 String   */
	@Length(max=20)
	
	private String title;
	/**运行状态 Integer default=0  map={'0':'未运行','1':'正在运行'}*/
	@Max(999L)
	
	private Integer status = 0;
	/**设备ID Integer   */
	@Max(99999999999L)
	
	private Integer deviceId;
	/**协议ID Integer   */
	@Max(99999999999L)
	
	private Integer protocolId;
	/**协议方向类型 Integer default=0  map={'1':'http_get','2':'http_post','3':'http_postjson','4':'tcpclient_发送','5':'tcpclient_应答','6':'tcpserver_发送','7':'tcpserver_应答','8':'808发送登录应答','9':'808发送加密应答'}*/
	@Max(999L)
	
	private Integer pdtype = 0;
	/**数据ID Integer   */
	@Max(99999999999L)
	
	private Integer pdataId;
	/**所在服务器节点ID Integer   */
	@Max(99999999999L)
	
	private Integer serverpointId;
	/**发送服务器地址 String  http是url地址，tcp是ip地址 */
	@Length(max=200)
	
	private String pseturl;
	/**服务器端口 Integer  http用不到，tcp要用到，如果是tcpclient是对方端口，tcpserver是自己端口 */
	@Max(99999999999L)
	
	private Integer psetport;
	/**单次数据条数 Integer default=1  */
	@Max(99999999999L)
	
	private Integer datanums = 1;
	/**线程数 Integer default=1  */
	@Max(99999999999L)
	
	private Integer sthreadnums = 1;
	/**循环次数 Integer default=1 0表示无限循环 */
	@Max(99999999999L)
	
	private Integer sloopnums = 1;
	/**循环间隔毫秒 Integer default=1000  */
	@Max(99999999999L)
	
	private Integer steploop = 1000;
	/**空闲关闭连接 Integer default=1  map={'0':'否','1':'是'}*/
	@Max(999L)
	
	private Integer releasetype = 1;
	/**用例集ID*/
	private int usecasesetid;
	//columns END
	/**
	 * 设置默认值
	 * @param _this
	 */
	public void makedefault(Usecase _this){
		_this.gmtCreate=new Date();
		_this.status=0;
		_this.pdtype=0;
		_this.datanums=1;
		_this.sthreadnums=1;
		_this.sloopnums=1;
		_this.steploop=1000;
		_this.releasetype=1;
		_this.usecasesetid=0;
	}
	public Usecase(){
		makedefault(this);
	}
	public Usecase(Integer _id){
		this.id=_id;
		makedefault(this);
	}
	/**
	 * 精简版构造，在创建提交数据库时应该使用saveCreate
	 * @param title String 标题   
	 * @param status Integer 运行状态 default=0  {'0':'未运行','1':'正在运行'}
	 * @param deviceId Integer 设备ID   
	 * @param pdataId Integer 数据ID   
	 * @param serverpointId Integer 所在服务器节点ID   
	 * @param pseturl String 发送服务器地址  http是url地址，tcp是ip地址 
	 * @param psetport Integer 服务器端口  http用不到，tcp要用到，如果是tcpclient是对方端口，tcpserver是自己端口 
	 * @param sthreadnums Integer 线程数 default=1  
	 * @param sloopnums Integer 循环次数 default=1 0表示无限循环 
	 * @param steploop Integer 循环间隔毫秒 default=1000  
	 * @param releasetype Integer 空闲关闭连接 default=1  {'0':'否','1':'是'}
	* @param notuse String 没用
	 */
	public Usecase(String title ,Integer status ,Integer deviceId ,Integer pdataId ,Integer serverpointId ,String pseturl ,Integer psetport ,Integer sthreadnums ,Integer sloopnums ,Integer steploop ,Integer releasetype ,String notuse) {
		super();
		this.title = title;
		this.status = status;
		this.deviceId = deviceId;
		this.pdataId = pdataId;
		this.serverpointId = serverpointId;
		this.pseturl = pseturl;
		this.psetport = psetport;
		this.sthreadnums = sthreadnums;
		this.sloopnums = sloopnums;
		this.steploop = steploop;
		this.releasetype = releasetype;
	}
	/**
	 * 不包括自动变量的构造
	 * @param id Integer 序号   
	 * @param title String 标题   
	 * @param status Integer 运行状态 default=0  {'0':'未运行','1':'正在运行'}
	 * @param deviceId Integer 设备ID   
	 * @param protocolId Integer 协议ID   
	 * @param pdtype Integer 协议方向类型 default=0  {'1':'http_get','2':'http_post','3':'http_postjson','4':'tcpclient_发送','5':'tcpclient_应答','6':'tcpserver_发送','7':'tcpserver_应答','8':'808发送登录应答','9':'808发送加密应答'}
	 * @param pdataId Integer 数据ID   
	 * @param serverpointId Integer 所在服务器节点ID   
	 * @param pseturl String 发送服务器地址  http是url地址，tcp是ip地址 
	 * @param psetport Integer 服务器端口  http用不到，tcp要用到，如果是tcpclient是对方端口，tcpserver是自己端口 
	 * @param datanums Integer 单次数据条数 default=1  
	 * @param sthreadnums Integer 线程数 default=1  
	 * @param sloopnums Integer 循环次数 default=1 0表示无限循环 
	 * @param steploop Integer 循环间隔毫秒 default=1000  
	 * @param releasetype Integer 空闲关闭连接 default=1  {'0':'否','1':'是'}
	* @param notuse String 没用
	 */
	public Usecase(Integer id ,String title ,Integer status ,Integer deviceId ,Integer protocolId ,Integer pdtype ,Integer pdataId ,Integer serverpointId ,String pseturl ,Integer psetport ,Integer datanums ,Integer sthreadnums ,Integer sloopnums ,Integer steploop ,Integer releasetype ,String notuse) {
		super();
		this.id = id;
		this.gmtCreate = new Date();
		this.title = title;
		this.status = status;
		this.deviceId = deviceId;
		this.protocolId = protocolId;
		this.pdtype = pdtype;
		this.pdataId = pdataId;
		this.serverpointId = serverpointId;
		this.pseturl = pseturl;
		this.psetport = psetport;
		this.datanums = datanums;
		this.sthreadnums = sthreadnums;
		this.sloopnums = sloopnums;
		this.steploop = steploop;
		this.releasetype = releasetype;
	}
	/**
	 * 全部数据的构造，包括了对象字段
	 * @param id Integer 序号   
	 * @param gmtCreate Date 创建时间   
	 * @param title String 标题   
	 * @param status Integer 运行状态 default=0  {'0':'未运行','1':'正在运行'}
	 * @param deviceId Integer 设备ID   
	 * @param protocolId Integer 协议ID   
	 * @param pdtype Integer 协议方向类型 default=0  {'1':'http_get','2':'http_post','3':'http_postjson','4':'tcpclient_发送','5':'tcpclient_应答','6':'tcpserver_发送','7':'tcpserver_应答','8':'808发送登录应答','9':'808发送加密应答'}
	 * @param pdataId Integer 数据ID   
	 * @param serverpointId Integer 所在服务器节点ID   
	 * @param pseturl String 发送服务器地址  http是url地址，tcp是ip地址 
	 * @param psetport Integer 服务器端口  http用不到，tcp要用到，如果是tcpclient是对方端口，tcpserver是自己端口 
	 * @param datanums Integer 单次数据条数 default=1  
	 * @param sthreadnums Integer 线程数 default=1  
	 * @param sloopnums Integer 循环次数 default=1 0表示无限循环 
	 * @param steploop Integer 循环间隔毫秒 default=1000  
	 * @param releasetype Integer 空闲关闭连接 default=1  {'0':'否','1':'是'}
	 * @param usecasesetid Integer 用例集ID default=0  
	* @param notuse String 没用
	 */
	public Usecase(Integer id ,Date gmtCreate ,String title ,Integer status ,Integer deviceId ,Integer protocolId ,Integer pdtype ,Integer pdataId ,Integer serverpointId ,String pseturl ,Integer psetport ,Integer datanums ,Integer sthreadnums ,Integer sloopnums ,Integer steploop ,Integer releasetype ,int usecasesetid ,String notuse,Object notuse2) {
		super();
		this.id = id;
		this.gmtCreate = gmtCreate;
		this.title = title;
		this.status = status;
		this.deviceId = deviceId;
		this.protocolId = protocolId;
		this.pdtype = pdtype;
		this.pdataId = pdataId;
		this.serverpointId = serverpointId;
		this.pseturl = pseturl;
		this.psetport = psetport;
		this.datanums = datanums;
		this.sthreadnums = sthreadnums;
		this.sloopnums = sloopnums;
		this.steploop = steploop;
		this.releasetype = releasetype;
		this.usecasesetid = usecasesetid;
	}

	/**我的中文名称*/
	private String myname;
	/**我的中文名称*/
	@Transient
	
	//@JSONField(serialize = false)
	public String getMyname() {
		myname=(myname==null)?""+title+" ":myname;
		return myname;
	}
	/**我的中文名称*/
	public void setMyname(String myname) {
		this.myname = myname;
	}
	/**我的带id中文名称*/
	private String mynameid;
	/**我的带id中文名称*/
	@Transient
	
	//@JSONField(serialize = false)
	public String getMynameid() {
		mynameid=(mynameid==null)?"["+id+"]"+title+" ":mynameid;
		return mynameid;
	}
	/**我的带id中文名称*/
	public void setMynameid(String mynameid) {
		this.mynameid = mynameid;
	}
	/**设置主键*/
	public void setId(Integer value) {
		this.id = value;
	}
	/**获取主键*/
	

	@Id @GeneratedValue(generator="custom-id")
	@GenericGenerator(name="custom-id", strategy = "identity")
	@Column(name = "id",  unique = false, nullable = false, insertable = true, updatable = true, length = 11)
	public Integer getId() {
		return this.id;
	}
	
	/**获取创建时间  */
	

	@Column(name = "gmt_create", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public Date getGmtCreate() {
		return this.gmtCreate;
	}
	/**设置创建时间  */

	public void setGmtCreate(Date value) {
		this.gmtCreate = value;
	}
	/**获取标题  */
	

	@Column(name = "title", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getTitle() {
		return this.title;
	}
	/**设置标题  */

	public void setTitle(String value) {
		this.title = value;
	}
	/**获取运行状态  {'0':'未运行','1':'正在运行'}*/
	

	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getStatus() {
		return this.status;
	}
	/**设置运行状态  {'0':'未运行','1':'正在运行'}*/

	public void setStatus(Integer value) {
		this.status = value;
	}
	/**获取设备ID  */
	

	@Column(name = "device_id", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getDeviceId() {
		return this.deviceId;
	}
	/**设置设备ID  */

	public void setDeviceId(Integer value) {
		this.deviceId = value;
	}
	/**获取协议ID  */
	

	@Column(name = "protocol_id", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getProtocolId() {
		return this.protocolId;
	}
	/**设置协议ID  */

	public void setProtocolId(Integer value) {
		this.protocolId = value;
	}
	/**获取协议方向类型  {'1':'http_get','2':'http_post','3':'http_postjson','4':'tcpclient_发送','5':'tcpclient_应答','6':'tcpserver_发送','7':'tcpserver_应答','8':'808发送登录应答','9':'808发送加密应答'}*/
	

	@Column(name = "pdtype", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getPdtype() {
		return this.pdtype;
	}
	/**设置协议方向类型  {'1':'http_get','2':'http_post','3':'http_postjson','4':'tcpclient_发送','5':'tcpclient_应答','6':'tcpserver_发送','7':'tcpserver_应答','8':'808发送登录应答','9':'808发送加密应答'}*/

	public void setPdtype(Integer value) {
		this.pdtype = value;
	}
	/**获取数据ID  */
	

	@Column(name = "pdata_id", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getPdataId() {
		return this.pdataId;
	}
	/**设置数据ID  */

	public void setPdataId(Integer value) {
		this.pdataId = value;
	}
	/**获取所在服务器节点ID  */
	

	@Column(name = "serverpoint_id", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getServerpointId() {
		return this.serverpointId;
	}
	/**设置所在服务器节点ID  */

	public void setServerpointId(Integer value) {
		this.serverpointId = value;
	}
	/**获取发送服务器地址 http是url地址，tcp是ip地址 */
	

	@Column(name = "pseturl", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getPseturl() {
		return this.pseturl;
	}
	/**设置发送服务器地址 http是url地址，tcp是ip地址 */

	public void setPseturl(String value) {
		this.pseturl = value;
	}
	/**获取服务器端口 http用不到，tcp要用到，如果是tcpclient是对方端口，tcpserver是自己端口 */
	

	@Column(name = "psetport", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getPsetport() {
		return this.psetport;
	}
	/**设置服务器端口 http用不到，tcp要用到，如果是tcpclient是对方端口，tcpserver是自己端口 */

	public void setPsetport(Integer value) {
		this.psetport = value;
	}
	/**获取单次数据条数  */
	

	@Column(name = "datanums", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getDatanums() {
		return this.datanums;
	}
	/**设置单次数据条数  */

	public void setDatanums(Integer value) {
		this.datanums = value;
	}
	/**获取线程数  */
	

	@Column(name = "sthreadnums", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getSthreadnums() {
		return this.sthreadnums;
	}
	/**设置线程数  */

	public void setSthreadnums(Integer value) {
		this.sthreadnums = value;
	}
	/**获取循环次数 0表示无限循环 */
	

	@Column(name = "sloopnums", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getSloopnums() {
		return this.sloopnums;
	}
	/**设置循环次数 0表示无限循环 */

	public void setSloopnums(Integer value) {
		this.sloopnums = value;
	}
	/**获取循环间隔毫秒  */
	

	@Column(name = "steploop", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Integer getSteploop() {
		return this.steploop;
	}
	/**设置循环间隔毫秒  */

	public void setSteploop(Integer value) {
		this.steploop = value;
	}
	/**获取空闲关闭连接  {'0':'否','1':'是'}*/
	

	@Column(name = "releasetype", unique = false, nullable = true, insertable = true, updatable = true, length = 3)
	public Integer getReleasetype() {
		return this.releasetype;
	}
	/**设置空闲关闭连接  {'0':'否','1':'是'}*/

	public void setReleasetype(Integer value) {
		this.releasetype = value;
	}
	/**对象 获取用例集ID  */
	@Transient
	

	public int getUsecasesetid() {
		return this.usecasesetid;
	}
	/**设置用例集ID  */

	public void setUsecasesetid(int value) {
		this.usecasesetid = value;
	}
	/**创建时间String*/
	private String gmtCreateString;
	/**获取创建时间String*/
	@Transient
	
	public String getGmtCreateString() {
		if(gmtCreateString==null && gmtCreate!=null)
			gmtCreateString=new SimpleDateFormat(DATE_FORMAT).format(gmtCreate);
		return gmtCreateString;
	}
	/**设置创建时间String*/
	public void setGmtCreateString(String value) {
		this.gmtCreateString=value;
	}
	/**运行状态String*/
	private String statusString;
	/**获取运行状态String*/
	@Transient
	
	public String getStatusString() {
		return statusString;
	}
	/**设置运行状态String*/
	public void setStatusString(String value) {
		this.statusString=value;
	}
	/**运行状态Map*/
	private Map<String, Object> statusMap;
	/**获取运行状态Map*/
	@Transient
	
	public Map<String, Object> getStatusMap() {
		return statusMap;
	}
	/**设置运行状态Map*/
	public void setStatusMap(Map<String, Object> value) {
		this.statusMap=value;
	}
	/**设备IDString*/
	private String deviceIdString;
	/**获取设备IDString*/
	@Transient
	
	public String getDeviceIdString() {
		return deviceIdString;
	}
	/**设置设备IDString*/
	public void setDeviceIdString(String value) {
		this.deviceIdString=value;
	}
	
	/**设备IDStringid*/
	private String deviceIdStringid;
	/**获取设备IDStringid*/
	@Transient
	
	public String getDeviceIdStringid() {
		return deviceIdStringid;
	}
	/**设置设备IDStringid*/
	public void setDeviceIdStringid(String value) {
		this.deviceIdStringid=value;
	}
	/**协议IDString*/
	private String protocolIdString;
	/**获取协议IDString*/
	@Transient
	
	public String getProtocolIdString() {
		return protocolIdString;
	}
	/**设置协议IDString*/
	public void setProtocolIdString(String value) {
		this.protocolIdString=value;
	}

	/**协议IDStringid*/
	private String protocolIdStringid;
	/**获取协议IDStringid*/
	@Transient
	
	public String getProtocolIdStringid() {
		return protocolIdStringid;
	}
	/**设置协议IDStringid*/
	public void setProtocolIdStringid(String value) {
		this.protocolIdStringid=value;
	}
	/**协议方向类型String*/
	private String pdtypeString;
	/**获取协议方向类型String*/
	@Transient
	
	public String getPdtypeString() {
		return pdtypeString;
	}
	/**设置协议方向类型String*/
	public void setPdtypeString(String value) {
		this.pdtypeString=value;
	}
	/**协议方向类型Map*/
	private Map<String, Object> pdtypeMap;
	/**获取协议方向类型Map*/
	@Transient
	
	public Map<String, Object> getPdtypeMap() {
		return pdtypeMap;
	}
	/**设置协议方向类型Map*/
	public void setPdtypeMap(Map<String, Object> value) {
		this.pdtypeMap=value;
	}
	/**数据IDString*/
	private String pdataIdString;
	/**获取数据IDString*/
	@Transient
	
	public String getPdataIdString() {
		return pdataIdString;
	}
	/**设置数据IDString*/
	public void setPdataIdString(String value) {
		this.pdataIdString=value;
	}

	/**数据IDStringid*/
	private String pdataIdStringid;
	/**获取数据IDStringid*/
	@Transient
	
	public String getPdataIdStringid() {
		return pdataIdStringid;
	}
	/**设置数据IDStringid*/
	public void setPdataIdStringid(String value) {
		this.pdataIdStringid=value;
	}
	/**所在服务器节点IDString*/
	private String serverpointIdString;
	/**获取所在服务器节点IDString*/
	@Transient
	
	public String getServerpointIdString() {
		return serverpointIdString;
	}
	/**设置所在服务器节点IDString*/
	public void setServerpointIdString(String value) {
		this.serverpointIdString=value;
	}
	/**所在服务器节点IDStringid*/
	private String serverpointIdStringid;
	/**获取所在服务器节点IDStringid*/
	@Transient
	
	public String getServerpointIdStringid() {
		return serverpointIdStringid;
	}
	/**设置所在服务器节点IDStringid*/
	public void setServerpointIdStringid(String value) {
		this.serverpointIdStringid=value;
	}
	/**空闲关闭连接String*/
	private String releasetypeString;
	/**获取空闲关闭连接String*/
	@Transient
	
	public String getReleasetypeString() {
		return releasetypeString;
	}
	/**设置空闲关闭连接String*/
	public void setReleasetypeString(String value) {
		this.releasetypeString=value;
	}
	/**空闲关闭连接Map*/
	private Map<String, Object> releasetypeMap;
	/**获取空闲关闭连接Map*/
	@Transient
	
	public Map<String, Object> getReleasetypeMap() {
		return releasetypeMap;
	}
	/**设置空闲关闭连接Map*/
	public void setReleasetypeMap(Map<String, Object> value) {
		this.releasetypeMap=value;
	}

	
	/**
	 * 非常严格的检查对象，不能空不能0
	 * @param checkId 是否检查id
	 * @return
	 */
	public boolean errCheckObjHard(boolean checkId){
		if (errCheckObj(checkId))
			return true;
		if(checkId){
			if(0==this.id)
				return true;
		}
		return false;
	}
	/**
	 * 较松的的检查对象，不能空，可以是0
	 * @param checkId 是否检查id
	 * @return
	 */
	public boolean errCheckObj(boolean checkId){
		if(checkId){
			if(this.id==null)
				return true;
		}
		return false;
	}
	/**备用对象1*/
	private Object obj1;
	/**
	 * 获取备用对象1
	 * @return Object
	 */
	@Transient
	
	public Object getObj1() {
		return obj1;
	}
	/**
	 * 设置备用对象1
	 * @param obj1
	 */
	public void setObj1(Object obj1) {
		this.obj1 = obj1;
	}
	/**备用对象2*/
	private Object obj2;
	/**
	 * 获取备用对象2
	 * @return Object
	 */
	@Transient
	
	public Object getObj2() {
		return obj2;
	}
	/**
	 * 设置备用对象2
	 * @param obj2
	 */
	public void setObj2(Object obj2) {
		this.obj2 = obj2;
	}
	/**备用对象3*/
	private Object obj3;
	/**
	 * 获取备用对象3
	 * @return Object
	 */
	@Transient
	
	public Object getObj3() {
		return obj3;
	}
	/**
	 * 设置备用对象3
	 * @param obj3
	 */
	public void setObj3(Object obj3) {
		this.obj3 = obj3;
	}
	/**备用对象4*/
	private Object obj4;
	/**
	 * 获取备用对象4
	 * @return Object
	 */
	@Transient
	
	public Object getObj4() {
		return obj4;
	}
	/**
	 * 设置备用对象4
	 * @param obj4
	 */
	public void setObj4(Object obj4) {
		this.obj4 = obj4;
	}
	/**备用对象5*/
	private Object obj5;
	/**
	 * 获取备用对象5
	 * @return Object
	 */
	@Transient
	
	public Object getObj5() {
		return obj5;
	}
	/**
	 * 设置备用对象5
	 * @param obj5
	 */
	public void setObj5(Object obj5) {
		this.obj5 = obj5;
	}
	/**备用对象 整数1*/
	private Integer objint1;
	/**
	 * 获取备用对象 整数1
	 * @return int
	 */
	@Transient
	
	public Integer getObjint1() {
		return objint1;
	}
	/**
	 * 设置备用对象 整数1
	 * @param objint1 int
	 */
	public void setObjint1(Integer objint1) {
		this.objint1 = objint1;
	}
	/**备用对象 文本1*/
	private String objstring1;
	/**
	 * 获取备用对象 文本1
	 * @return string
	 */
	@Transient
	
	public String getObjstring1() {
		return objstring1;
	}
	/**
	 * 设置备用对象 文本1
	 * @param objstring1 String
	 */
	public void setObjstring1(String objstring1) {
		this.objstring1 = objstring1;
	}
	/**备用对象 整数2*/
	private Integer objint2;
	/**
	 * 获取备用对象 整数2
	 * @return int
	 */
	@Transient
	
	public Integer getObjint2() {
		return objint2;
	}
	/**
	 * 设置备用对象 整数2
	 * @param objint2 int
	 */
	public void setObjint2(Integer objint2) {
		this.objint2 = objint2;
	}
	/**备用对象 文本2*/
	private String objstring2;
	/**
	 * 获取备用对象 文本2
	 * @return string
	 */
	@Transient
	
	public String getObjstring2() {
		return objstring2;
	}
	/**
	 * 设置备用对象 文本2
	 * @param objstring2 String
	 */
	public void setObjstring2(String objstring2) {
		this.objstring2 = objstring2;
	}
	/**备用对象 整数3*/
	private Integer objint3;
	/**
	 * 获取备用对象 整数3
	 * @return int
	 */
	@Transient
	
	public Integer getObjint3() {
		return objint3;
	}
	/**
	 * 设置备用对象 整数3
	 * @param objint3 int
	 */
	public void setObjint3(Integer objint3) {
		this.objint3 = objint3;
	}
	/**备用对象 文本3*/
	private String objstring3;
	/**
	 * 获取备用对象 文本3
	 * @return string
	 */
	@Transient
	
	public String getObjstring3() {
		return objstring3;
	}
	/**
	 * 设置备用对象 文本3
	 * @param objstring3 String
	 */
	public void setObjstring3(String objstring3) {
		this.objstring3 = objstring3;
	}
	/**
	 * Hash，同所有字段相加判断
	 */
	public int hashCode() {
		if(getId()==null)
		return new HashCodeBuilder()
			.append(getId())
			.append(getGmtCreate())
			.append(getTitle())
			.append(getStatus())
			.append(getDeviceId())
			.append(getProtocolId())
			.append(getPdtype())
			.append(getPdataId())
			.append(getServerpointId())
			.append(getPseturl())
			.append(getPsetport())
			.append(getDatanums())
			.append(getSthreadnums())
			.append(getSloopnums())
			.append(getSteploop())
			.append(getReleasetype())
			.toHashCode();
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	/**
	 * 重写，对象是否相同，用ID来判断
	 */
	public boolean equals(Object obj) {
		if(obj instanceof Usecase == false) return false;
		if(this == obj) return true;
		Usecase other = (Usecase)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	/**
	 * 转文本
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE)
			.append(super.toString())
			.append(",Id:",getId())
			.append(",GmtCreate:",getGmtCreate())
			.append(",Title:",getTitle())
			.append(",Status:",getStatus())
			.append(",DeviceId:",getDeviceId())
			.append(",ProtocolId:",getProtocolId())
			.append(",Pdtype:",getPdtype())
			.append(",PdataId:",getPdataId())
			.append(",ServerpointId:",getServerpointId())
			.append(",Pseturl:",getPseturl())
			.append(",Psetport:",getPsetport())
			.append(",Datanums:",getDatanums())
			.append(",Sthreadnums:",getSthreadnums())
			.append(",Sloopnums:",getSloopnums())
			.append(",Steploop:",getSteploop())
			.append(",Releasetype:",getReleasetype())
			.toString();

	}
/*	*//**
	 * 返回JSON之不显示第0级，对于一些不能传递的对象，不进行json编码，比如password明文，用得较少。
	 * @return
	 *//*
	public String toJsonNotshow(){
		String[] excludesProperties={"null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}
	*//**
	 * 返回JSON之不显示第1级，nowshow+notshow1('notshow1'+xxxObj+xxxString+xxxList)
	 * <br>在内部服务通讯时，防止get页面参数过大，通常设置为notshow1，返回时可以是notshow或notshow1
	 * @return
	 *//*
	public String toJsonNotshow1(){
		String[] excludesProperties={"myname","mynameid","gmtCreateString","statusString","statusMap","deviceIdString","deviceIdDeviceObj","deviceIdStringid","protocolIdString","protocolIdProtocolObj","protocolIdStringid","pdtypeString","pdtypeMap","pdataIdString","pdataIdPdataObj","pdataIdStringid","serverpointIdString","serverpointIdServerpointObj","serverpointIdStringid","releasetypeString","releasetypeMap","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}
	*//**
	 * 返回JSON之不显示第2级，nowshow+notshow1+notshow2，用于外部服务通讯，符合对方的api要求
	 * @return
	 *//*
	public String toJsonNotshow2(){
		String[] excludesProperties={"myname","mynameid","gmtCreateString","statusString","statusMap","deviceIdString","deviceIdDeviceObj","deviceIdStringid","protocolIdString","protocolIdProtocolObj","protocolIdStringid","pdtypeString","pdtypeMap","pdataIdString","pdataIdPdataObj","pdataIdStringid","serverpointIdString","serverpointIdServerpointObj","serverpointIdStringid","releasetypeString","releasetypeMap","obj1","obj2","obj3","obj4","obj5","obj1","obj1","obj2","obj2","obj3","obj3","null"};
		String ret=JsonUtilAli.toJSONString(this, null, excludesProperties);
		return ret;
	}*/

/*
//精简构造 用例
Usecase usecase = new Usecase(
	title , //String 标题   
	status , //Integer 运行状态 default=0  {'0':'未运行','1':'正在运行'}
	deviceId , //Integer 设备ID   
	pdataId , //Integer 数据ID   
	serverpointId , //Integer 所在服务器节点ID   
	pseturl , //String 发送服务器地址  http是url地址，tcp是ip地址 
	psetport , //Integer 服务器端口  http用不到，tcp要用到，如果是tcpclient是对方端口，tcpserver是自己端口 
	sthreadnums , //Integer 线程数 default=1  
	sloopnums , //Integer 循环次数 default=1 0表示无限循环 
	steploop , //Integer 循环间隔毫秒 default=1000  
	releasetype , //Integer 空闲关闭连接 default=1  {'0':'否','1':'是'}
	null
);
*/
}
