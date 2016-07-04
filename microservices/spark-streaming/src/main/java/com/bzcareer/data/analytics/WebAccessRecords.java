package com.bzcareer.data.analytics;

/**
 * WebAccessRecords<br>
 * 
 * If you plan on persisting to Apache Cassandra you should create the corresponding table so you can persist records.
 * 
 * @author Zak Hassan <Zak.Hassan1010@gmail.com>
 */
public class WebAccessRecords {
	String method;
	String ip_address;
	String timestamp;
	String referrer;
	String user_agent;
	String protocol;
	String access_port;
	String uri;
	String http_code;
	String time_taken;
	String session_id;
	
	@Override
	public String toString() {
		return "WebAccessRecords [method=" + method + ", ip_address=" + ip_address
				+ ", timestamp=" + timestamp + ", referrer=" + referrer
				+ ", user_agent=" + user_agent + ", protocol=" + protocol
				+ ", access_port=" + access_port + ", uri=" + uri
				+ ", http_code=" + http_code + ", time_taken=" + time_taken
				+ ", session_id=" + session_id + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((access_port == null) ? 0 : access_port.hashCode());
		result = prime * result
				+ ((http_code == null) ? 0 : http_code.hashCode());
		result = prime * result
				+ ((ip_address == null) ? 0 : ip_address.hashCode());
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		result = prime * result
				+ ((protocol == null) ? 0 : protocol.hashCode());
		result = prime * result
				+ ((referrer == null) ? 0 : referrer.hashCode());
		result = prime * result
				+ ((session_id == null) ? 0 : session_id.hashCode());
		result = prime * result
				+ ((time_taken == null) ? 0 : time_taken.hashCode());
		result = prime * result
				+ ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((uri == null) ? 0 : uri.hashCode());
		result = prime * result
				+ ((user_agent == null) ? 0 : user_agent.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WebAccessRecords other = (WebAccessRecords) obj;
		if (access_port == null) {
			if (other.access_port != null)
				return false;
		} else if (!access_port.equals(other.access_port))
			return false;
		if (http_code == null) {
			if (other.http_code != null)
				return false;
		} else if (!http_code.equals(other.http_code))
			return false;
		if (ip_address == null) {
			if (other.ip_address != null)
				return false;
		} else if (!ip_address.equals(other.ip_address))
			return false;
		if (method == null) {
			if (other.method != null)
				return false;
		} else if (!method.equals(other.method))
			return false;
		if (protocol == null) {
			if (other.protocol != null)
				return false;
		} else if (!protocol.equals(other.protocol))
			return false;
		if (referrer == null) {
			if (other.referrer != null)
				return false;
		} else if (!referrer.equals(other.referrer))
			return false;
		if (session_id == null) {
			if (other.session_id != null)
				return false;
		} else if (!session_id.equals(other.session_id))
			return false;
		if (time_taken == null) {
			if (other.time_taken != null)
				return false;
		} else if (!time_taken.equals(other.time_taken))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		if (user_agent == null) {
			if (other.user_agent != null)
				return false;
		} else if (!user_agent.equals(other.user_agent))
			return false;
		return true;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getReferrer() {
		return referrer;
	}
	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}
	public String getUser_agent() {
		return user_agent;
	}
	public void setUser_agent(String user_agent) {
		this.user_agent = user_agent;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getAccess_port() {
		return access_port;
	}
	public void setAccess_port(String access_port) {
		this.access_port = access_port;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getHttp_code() {
		return http_code;
	}
	public void setHttp_code(String http_code) {
		this.http_code = http_code;
	}
	public String getTime_taken() {
		return time_taken;
	}
	public void setTime_taken(String time_taken) {
		this.time_taken = time_taken;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

}
