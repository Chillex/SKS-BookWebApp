package util;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class IntegerAdapter extends XmlAdapter<String, Integer> {

	@Override
	public String marshal(Integer v) throws Exception {
		if(v == null)
			return "";
		
		return v.toString();
	}
	
	@Override
	public Integer unmarshal(String v) throws Exception {
		return Integer.parseInt(v);
	}
}
