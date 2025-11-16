package com.btp.e2e.implementations;

import java.lang.reflect.Field;

import com.btp.e2e.interfaces.ICheckService;
import com.google.gson.Gson;

public class PojoPrimary implements ICheckService {

	public PojoPrimary() {
	}

	@Override
	public void checkAttributes() {
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.getName();
			try {
				if (field.get(this) == null)
					if (field.getType() == String.class) {
				        field.set(this, String.valueOf(""));
				    } else if (field.getType() == boolean.class) {
				        field.set(this,  false);
				    }
				    else if (field.getType() == byte.class) {
				        field.set(this, Byte.valueOf(""));
				    }
				    else if (field.getType() == char.class) {
				        field.set(this, "".charAt(0));
				    }
				    else if (field.getType() == short.class) {
				        field.set(this, Short.valueOf("0"));
				    }
				    else if (field.getType() == int.class) {
				        field.set(this, Integer.valueOf("0"));
				    }
				    else if (field.getType() == long.class) {
				        field.set(this, Long.valueOf("0"));
				    }
				    else if (field.getType() == float.class) {
				        field.set(this, Float.valueOf("0"));
				    }
				    else if (field.getType() == double.class) {
				        field.set(this, Double.valueOf("0"));
				    }
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		String newLine = System.getProperty("line.separator");

		result.append(this.getClass().getName());
		result.append("{");
		result.append(newLine);

		Field[] fields = this.getClass().getDeclaredFields();

		for (Field field : fields) {
			result.append(" ");
			try {
				result.append(field.getName());
				result.append(": ");
				result.append(field.get(this));
			} catch (IllegalAccessException ex) {
				System.out.println(ex);
			}
			result.append(newLine);
		}
		result.append("}");

		return result.toString();
	}
	
	public String returnJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
