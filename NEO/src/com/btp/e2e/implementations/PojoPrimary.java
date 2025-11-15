/**
 * 
 */
package com.btp.e2e.implementations;

import java.lang.reflect.Field;

import com.btp.e2e.interfaces.ICheckService;
import com.google.gson.Gson;

/**
 * @author MNRuiz
 *
 */
public class PojoPrimary implements ICheckService {

	/**
	 * 
	 */
	public PojoPrimary() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.scp.pits.interfaces.ICheckService#checkAttributes()
	 */
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
				// TODO Auto-generated catch block
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

		// determine fields declared in this class only (no fields of superclass)
		Field[] fields = this.getClass().getDeclaredFields();

		// print field names paired with their values
		for (Field field : fields) {
			result.append(" ");
			try {
				result.append(field.getName());
				result.append(": ");
				// requires access to private field:
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
