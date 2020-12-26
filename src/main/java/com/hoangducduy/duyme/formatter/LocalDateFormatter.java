package com.hoangducduy.duyme.formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.format.Formatter;

public class LocalDateFormatter implements Formatter<LocalDate> {

	@Override
	public String print(LocalDate object, Locale locale) {
		if (object == null) {
			return "";
		}
		return object.toString();
	}

	@Override
	public LocalDate parse(String text, Locale locale) throws ParseException {
		if (text != null) {
			return LocalDate.parse(text, DateTimeFormatter.ISO_LOCAL_DATE);
		} else {
			return LocalDate.now();
		}
	}
}
