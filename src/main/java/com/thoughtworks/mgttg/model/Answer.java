package com.thoughtworks.mgttg.model;

/**
 * @author talipk
 *
 */
public class Answer {

	private String text;

	public Answer(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		return "Answer [text=" + text + "]";
	}

}
