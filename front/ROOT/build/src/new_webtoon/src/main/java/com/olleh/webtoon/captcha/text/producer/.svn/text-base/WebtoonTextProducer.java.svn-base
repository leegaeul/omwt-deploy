package com.olleh.webtoon.captcha.text.producer;

import nl.captcha.text.producer.DefaultTextProducer;
import nl.captcha.text.producer.TextProducer;


public class WebtoonTextProducer implements TextProducer {
	static final int DEFAULT_LENGTH = 6;
	private static final char[] WEBTOON_CHARS = new char[] { 'a', 'b', 'c', 'd',
        'e', 'f', 'g', 'h', 'k', 'm', 'n', 'p', 'r', 'w', 'x', 'y'};
	private final TextProducer _txtProd;

	public WebtoonTextProducer() {
		this(DEFAULT_LENGTH);
	}

	public WebtoonTextProducer(int length) {
		_txtProd = new DefaultTextProducer(length, WEBTOON_CHARS);
	}

	@Override
	public String getText() {
		return new StringBuffer(_txtProd.getText()).reverse().toString();
	}
}