package br.com.hotmart.libs;

public enum NewsApiStatusEnum {

	OK("ok"), ERROR("error");

	private String status;

	private NewsApiStatusEnum(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
