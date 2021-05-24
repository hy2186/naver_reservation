package com.ntscorp.reservation.common.file.util;

import java.util.UUID;

public class FileNameGenerator {
	public static String getRandomFileName() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
}
