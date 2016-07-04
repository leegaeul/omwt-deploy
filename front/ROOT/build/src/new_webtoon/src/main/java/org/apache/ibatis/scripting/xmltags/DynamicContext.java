package org.apache.ibatis.scripting.xmltags;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.ognl.OgnlException;
import org.apache.ibatis.ognl.OgnlRuntime;
import org.apache.ibatis.ognl.PropertyAccessor;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;

public class DynamicContext {

	public static final String PARAMETER_OBJECT_KEY = "_parameter";
	public static final String DATABASE_ID_KEY = "_databaseId";

	static {
		OgnlRuntime
				.setPropertyAccessor(ContextMap.class, new ContextAccessor());
	}

	private final ContextMap bindings;
	private final StringBuilder sqlBuilder = new StringBuilder();
	private int uniqueNumber = 0;

	public DynamicContext(Configuration configuration, Object parameterObject) {
		if (parameterObject != null && !(parameterObject instanceof Map)) {
			MetaObject metaObject = configuration
					.newMetaObject(parameterObject);
			bindings = new ContextMap(metaObject);
		} else {
			bindings = new ContextMap(null);
		}
		bindings.put(PARAMETER_OBJECT_KEY, parameterObject);
		bindings.put(DATABASE_ID_KEY, configuration.getDatabaseId());
	}

	public Map<String, Object> getBindings() {
		return bindings;
	}

	public void bind(String name, Object value) {
		bindings.put(name, value);
	}

	public void appendSql(String sql) {
		sqlBuilder.append(sql);
		sqlBuilder.append(" ");
	}

	public String getSql() {
		return sqlBuilder.toString().trim();
	}

	public int getUniqueNumber() {
		return uniqueNumber++;
	}

	static class ContextMap extends HashMap<String, Object> {
		private static final long serialVersionUID = 2977601501966151582L;

		private MetaObject parameterMetaObject;

		public ContextMap(MetaObject parameterMetaObject) {
			this.parameterMetaObject = parameterMetaObject;
		}

		@Override
		public Object get(Object key) {
			String strKey = (String) key;
			if (super.containsKey(strKey)) {
				return super.get(strKey);
			}

			if (parameterMetaObject != null) {
				Object object = parameterMetaObject.getValue(strKey);
				if (object != null) {
					super.put(strKey, object);
				}

				return object;
			}

			return null;
		}

		// XXX(함수추가) <if test="empty(value)">
		public boolean isEmpty(Object obj) {
			if (obj == null)
				return true;

			if (obj instanceof String) {
				return ((String) obj).equals("");
			} else if (obj instanceof List) {
				return ((List) obj).isEmpty();
			} else if (obj instanceof Map) {
				return ((Map) obj).isEmpty();
			} else if (obj instanceof Object[]) {
				return Array.getLength(obj) == 0;
			} else {
				return obj == null;
			}
		}

		public boolean isNotEmpty(Object obj) {
			return !isEmpty(obj);
		}
		
		public boolean isEqual(Object o1,Object o2) {
			if(o1 == o2)
				return true;
			if(o1 == null || o2 == null)
				return false;
			return o1.equals(o2);
		}
		
		public boolean isNotEqual(Object o1,Object o2) {
			return !isEqual(o1,o2);
		}
	}

	static class ContextAccessor implements PropertyAccessor {

		@SuppressWarnings("unchecked")
		public Object getProperty(Map context, Object target, Object name)
				throws OgnlException {
			Map map = (Map) target;

			Object result = map.get(name);
			if (result != null) {
				return result;
			}

			Object parameterObject = map.get(PARAMETER_OBJECT_KEY);
			if (parameterObject instanceof Map) {
				return ((Map) parameterObject).get(name);
			}

			return null;
		}

		@SuppressWarnings("unchecked")
		public void setProperty(Map context, Object target, Object name,
				Object value) throws OgnlException {
			Map map = (Map) target;
			map.put(name, value);
		}
	}
}