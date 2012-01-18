package net.evrythng.thng.api.utils;

import java.util.Collection;

import net.evrythng.thng.api.model.Property;
import net.evrythng.thng.api.model.Thng;
import net.evrythng.thng.api.model.ThngCollection;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.PropertyNameProcessor;
import net.sf.json.util.JavaIdentifierTransformer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSONUtils {

	private static final Logger logger = LoggerFactory.getLogger(JSONUtils.class);

	private static JsonConfig config;

	public static class CamelCapsToUnderscores implements PropertyNameProcessor {

		public String processPropertyName(Class beanClass, String name) {
			String[] words = name.split("(?=\\p{Upper})");
			StringBuffer buffer = new StringBuffer();
			for (String word : words) {
				if (buffer.length() != 0) {
					buffer.append("_");
				}
				buffer.append(word.toLowerCase());
			}
			return buffer.toString();
		}
	}

	public static JsonConfig getConfig() {
		if (config == null) {
			config = new JsonConfig();

			// TODO: find a better way to process JAVA property names:
			config.registerJsonPropertyNameProcessor(Thng.class, new CamelCapsToUnderscores());
			config.registerJsonPropertyNameProcessor(Property.class, new CamelCapsToUnderscores());
			config.registerJsonPropertyNameProcessor(ThngCollection.class, new CamelCapsToUnderscores());

			config.setJavaIdentifierTransformer(new BeanJavaIdentifierTransformer());
		}

		return config;
	}

	private static final class BeanJavaIdentifierTransformer extends JavaIdentifierTransformer {
		@Override
		public String transformToJavaIdentifier(String str) {
			if (str == null) {
				return null;
			}

			String str2 = shaveOffNonJavaIdentifierStartChars(str);

			char[] chars = str2.toCharArray();
			int pos = 0;
			StringBuffer buf = new StringBuffer();
			boolean toUpperCaseNextChar = false;
			while (pos < chars.length) {
				if (!Character.isJavaIdentifierPart(chars[pos]) || Character.isWhitespace(chars[pos]) || chars[pos] == '_') {
					toUpperCaseNextChar = true;
				} else {
					if (toUpperCaseNextChar) {
						buf.append(Character.toUpperCase(chars[pos]));
						toUpperCaseNextChar = false;
					} else {
						buf.append(chars[pos]);
					}
				}
				pos++;
			}
			return buf.toString();
		}
	}

	public static JSONArray toJSONArray(Object object) {
		// Delegate:
		return JSONArray.fromObject(object, getConfig());
	}

	public static JSONObject toJSONObject(Object object) {
		// Delegate:
		return JSONObject.fromObject(object, getConfig());
	}

	public static <K> K toBean(JSONObject json, Class<K> klass) {
		JsonConfig config = getConfig();
		config.setRootClass(klass);
		return (K) JSONObject.toBean(json, config);
	}

	public static <K> Collection<K> toCollection(JSONArray jsonArray, Class<K> klass) {
		JsonConfig config = getConfig();
		config.setRootClass(klass);
		return JSONArray.toCollection(jsonArray, config);
	}

	public static void debug(Collection<?> items) throws JSONException {
		logger.debug("> {} items(s) found!", items.size());
		int count = 1;
		for (Object item : items) {
			logger.debug(">> {} => {}", count++, item);
		}
	}
}
