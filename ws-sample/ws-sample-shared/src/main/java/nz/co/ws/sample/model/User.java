package nz.co.ws.sample.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@SuppressWarnings("serial")
@XmlRootElement(namespace = "http://ws.integration.sample.ws.co.nz", name = "User")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "http://ws.integration.sample.ws.co.nz", name = "User")
public class User implements Serializable {

	@XmlElement(required = true)
	private String userId;
	@XmlElement(required = true)
	private String userName;
	private String password;
	@XmlElement(required = true)
	private String role;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public static class Builder {
		private String userId;
		private String userName;
		private String password;
		private String role;

		public Builder userName(String userName) {
			this.userName = userName;
			return this;
		}

		public Builder password(String password) {
			this.password = password;
			return this;
		}

		public Builder role(String role) {
			this.role = role;
			return this;
		}

		public Builder userId(String userId) {
			this.userId = userId;
			return this;
		}

		public User build() {
			User user = new User();
			user.setPassword(password);
			user.setRole(role);
			user.setUserId(userId);
			user.setUserName(userName);
			return user;
		}
	}

	@Override
	public boolean equals(Object obj) {
		EqualsBuilder builder = new EqualsBuilder();
		return builder
				.append(this.userName, ((User) obj).userName)
				.append(this.role, ((User) obj).role)
				.isEquals();
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		return builder
				.append(this.role)
				.append(this.userName)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
				.append("userName", userName)
				.append("userId", userId)
				.append("role", role)
				.toString();
	}

}
