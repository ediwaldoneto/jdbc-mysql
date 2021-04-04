package br.com.nt.utils;

import java.util.logging.Logger;

public final class Util {

	private static final Logger log;

	static {
		System.setProperty("java.util.logging.SimpleFormatter.format",
				"%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS: %4$s: %5$s%n%6$s%n");
		log = Logger.getLogger(Util.class.getName());

	}

	private Util() {
	}

	public static void infoLog(String msg) {
		log.info(msg);
	}

}