package com;

import java.io.File;
import java.nio.file.Files;
import java.util.Calendar;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class TreeJSON {

	public static String getJson(String text) throws Exception {
		File file = new File("temp_" + Calendar.getInstance().getTimeInMillis() + ".txt");
		if (file.exists())
			file.delete();
		System.out.println(file.getAbsolutePath());
		Files.writeString(file.toPath(), text);
		DNAtree.main(new String[] { file.getAbsolutePath() });
		// "/home/sanjeev/Documents/workspace/Nag/DSA/DNATree/input_2.txt"
		Node node = DNAtree.tree.getInfo();
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.addSerializationExclusionStrategy(new ExclusionStrategy() {
			public boolean shouldSkipField(FieldAttributes f) {
				Expose annotation = f.getAnnotation(Expose.class);
				if (annotation != null)
					return !annotation.serialize();
				else
					return false;
			}

			public boolean shouldSkipClass(Class<?> clazz) {
				Expose annotation = clazz.getAnnotation(Expose.class);
				if (annotation != null)
					return !annotation.serialize();
				else
					return false;
			}
		});
		Gson gson = gsonBuilder.create();
		return gson.toJsonTree(node).toString();
	}
}
