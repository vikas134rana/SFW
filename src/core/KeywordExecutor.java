package core;

import keywords.core.Highlight;
import keywords.core.Screenshot;

public abstract class KeywordExecutor {

	public Keyword execute() {

		beforeExecute();

		Keyword kw = innerExecute();

		afterExecute();

		System.out.println(kw);

		return kw;
	}

	public abstract Keyword innerExecute();

	private void beforeExecute() {

		Highlight.highlightEles.clear();

	}

	private void afterExecute() {

		Screenshot.takeScreenshotInsideTemp();
		Highlight.dehighlightAll();
	}

}
