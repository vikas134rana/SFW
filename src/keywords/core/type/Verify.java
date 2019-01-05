package keywords.core.type;

import keywords.core.other.Message;

public class Verify {

	private boolean verified;
	private String message;

	public Verify() {
	}

	public Verify(boolean verified, String message) {
		this.verified = verified;
		this.message = message;
	}

	public Verify(boolean verified, Message msg) {
		this.verified = verified;
		this.message = msg.toString();
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
