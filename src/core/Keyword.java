package core;

public class Keyword {

	private ExecutionStatus status;
	private String output;
	private String message;
	private String screenshotPath;

	private Keyword(KeywordRB builder) {
		this.status = builder.status;
		this.output = builder.output;
		this.message = builder.message;
		this.screenshotPath = builder.screenshotPath;
	}

	@Override
	public String toString() {
		return "Keyword -> " + "[Status: " + status + "] [Output: " + output + "] [Message: " + message + "]";
	}

	public ExecutionStatus getStatus() {
		return status;
	}

	public String getOutput() {
		return output;
	}

	public String getMessage() {
		return message;
	}

	public String getScreenshotPath() {
		return screenshotPath;
	}

	public static class KeywordRB {

		private ExecutionStatus status;
		private String output;
		private String message;
		private String screenshotPath;

		public static KeywordRB PASS() {
			KeywordRB keywordObj = new KeywordRB();
			keywordObj.status = ExecutionStatus.PASS;
			return keywordObj;
		}

		public static KeywordRB FAIL() {
			KeywordRB keywordObj = new KeywordRB();
			keywordObj.status = ExecutionStatus.FAIL;
			return keywordObj;
		}

		public KeywordRB setMessage(String msg) {
			this.message = msg;
			return this;
		}

		public KeywordRB setScreenshotPath(String path) {
			this.screenshotPath = path;
			return this;
		}

		public KeywordRB setOutput(String output) {
			this.output = output;
			return this;
		}

		public KeywordRB setOutput(boolean output) {
			this.output = String.valueOf(output);
			return this;
		}

		public KeywordRB setOutput(int output) {
			this.output = String.valueOf(output);
			return this;
		}

		public KeywordRB setOutput(double output) {
			this.output = String.valueOf(output);
			return this;
		}

		public KeywordRB setOutput(long output) {
			this.output = String.valueOf(output);
			return this;
		}

		public Keyword build() {
			return new Keyword(this);
		}
	}
}
