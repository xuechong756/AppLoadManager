package com.dobest.appload.utils;

import com.google.gson.Gson;

public class MyGson {

	private static Gson gson = new Gson();

	private MyGson() {
	}

	public static Gson getInstance() {
		return gson;
	}

}
