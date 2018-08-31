package com.holo.domain.my;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.holo.domain.base.BaseEntity;

@Entity
@Table(name = "user")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)//可读可写
public class User extends BaseEntity{
	public static final String TABLE_NAME="用户表"; 
	
	@Max(99999999999L)
	private Integer id;
	
	/**创建时间 Date   */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtCreate;
	
	/**修改时间 Date   */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtModified;
	
	/**状态 Integer default=0*/
	@Max(999L)
	private Integer status = 0;	
	
	/**角色 Integer default=0 */
	@Max(999L)
	private Integer roleId = 0;
	
	/**账号 String  唯一值 */
	@NotBlank @Length(max=20)
	private String username;
	
	/**密码 String   */
	@NotBlank @Length(max=32)
	private String password;
	/**当@Id写字段上时 ，如过 把 @column 写在 getter 方法上 ，会出现错误 或者不起作用
	 *同样如果把 @Id 注解到 getter 方法上，而把 @column 注解到 字段上，也会同样报上述错误。。
	 *所以建议 统一 注解 的地方。。建议都注解到getter 方法上。
	 *原因： 使用Annotations形式的配置方式，默认的访问形式由@Id标记放置的位置决定，当@Id标记在属性上声明，而不是在getter方法上，
	 *则所有的其它属性默认采用属性访问形式（即不通过getter和setter方法，直接访问属性）*/
	@Id 
	@Column(name = "id",  unique = false, nullable = false, insertable = true, updatable = true, length = 11)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public Date getGmtModified() {
		return gmtModified;
	}
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", gmtCreate=" + gmtCreate + ", gmtModified="
				+ gmtModified + ", status=" + status + ", roleId=" + roleId
				+ ", username=" + username + ", password=" + password + "]";
	}

	public User(Integer id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}
}
