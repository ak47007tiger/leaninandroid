package loc.objswitch;

import java.util.HashMap;
import java.util.Map;

public class ObjSwitch {
	private Map<Object,Handle> mapping = new HashMap<Object, Handle>();
	public void addHandle(Object key, Handle handle){
		mapping.put(key, handle);
	}
	public Object DispatchHandle(Object key,Object input){
		return mapping.get(key).handle(input);
	}
}
