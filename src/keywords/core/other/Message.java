package keywords.core.other;

public enum Message {

	/*- Status */
	PASS("Pass"), FAIL("Fail"),

	/*- Verification */
	VERIFIED("Verified"), NOT_VERIFIED("Not Verified"),

	/*- Other */
	FRAME_SWITCH_SUCCESSFUL("Frame Switching Successful"),FRAME_SWITCH_FAILED("Frame Switching Failed"), DONE("Done"),
	WINDOW_SWITCH_SUCCESSFUL("Window Switching Successful"),WINDOW_SWITCH_FAILED("Window Switching Failed");

	String msg;

	private Message(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return msg;
	}
}
