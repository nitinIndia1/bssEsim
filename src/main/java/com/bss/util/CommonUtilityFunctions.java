package com.bss.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;


//import jakarta.xml.bind.DatatypeConverter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CommonUtilityFunctions {


	public String generateValidRequestParams(Map<String,Object> map) {
		String reqParams = "";
		Set<String> set = map.keySet();
		for(String key:set) {
			if(map.get(key)!=null) {
				reqParams = reqParams+"&"+key+"="+map.get(key).toString();
				//System.out.println(key+" : "+map.get(key).toString());
			}
		}
		return reqParams;
	}
	
	
	public boolean checkDate(String strDate, SimpleDateFormat sdf) {
		
		boolean flag = false;
		try {
		Date dt = sdf.parse(strDate);
		if(sdf.format(dt).equals(strDate)) {
			flag = true;
		}
		else {
			flag = false;
		}
		}catch(Exception ex) {
			ex.printStackTrace();
			flag=false;
		}
		return flag;
	}

	
	
	public String convertToDifferentSdf(String strDate, SimpleDateFormat sdfFrom, SimpleDateFormat sdfTo) {
		String strDateCon = null;
		try {
		Date dt = sdfFrom.parse(strDate);
		strDateCon = sdfTo.format(dt);
		}catch(Exception ex) {
			ex.printStackTrace();
			strDateCon = null;
		}
		return strDateCon;
	}
	
//	public boolean saveBase64AsImage(String base64,String path) {
//		boolean status = false; 
//		//String base64String = "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";
//
//	       // String[] strings = base64String.split(",");
//	        
//	        byte[] data = DatatypeConverter.parseBase64Binary(base64);
//	        //String path = "C:\\Users\\nitin\\OneDrive\\Desktop\\rough\\one\\one.jpg";
//	        File file = new File(path);
//	        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
//	            outputStream.write(data);
//	        } catch (IOException e) {
//	        	status = false;
//	            e.printStackTrace();
//	        }
//	        if(file.exists()) {
//	        	status = true;
//	        }
//	       return status;
//	}

	
	public boolean allowedValues(String value, List<String> listValues) {
		boolean flag =false;
		if(listValues.contains(value)) {
			flag=true;
		}
		return flag;
	}
	
	public String jsonToQuery(String operator,String json) {
		List list = extractDateRanges(operator,json);
		String whereDateRanges = "";
		JSONObject obj = null;
		if(list!=null && list.size()>0) {
			whereDateRanges = (String)list.get(0);
			obj = (JSONObject)list.get(1);			
		}

		if(obj!=null)
			json = obj.toJSONString();
		//json = json.replace(" ", "");
		String whereNotDate = "";		
		json = json.replace("{", "");
		json = json.replace("\"}", "'");
		json = json.replace("\":\"", "='");
		if(operator.equalsIgnoreCase("and")) {
			json = json.replace("\",\"", "' and ");	
		}
		else if(operator.equalsIgnoreCase("or")) {
			json = json.replace("\",\"", "' or ");			
		}
		json = json.replace("\":", "=");

		if(operator.equalsIgnoreCase("and")) {
			json = json.replace(",\"", " and ");
		}
		else if(operator.equalsIgnoreCase("or")) {
			json = json.replace(",\"", " or ");
		}
		json = json.replace("\"", "");
		json = json.replace("}", "");
		whereNotDate = json;


		if(whereNotDate.trim().length()==0) {
			whereDateRanges = whereDateRanges.replace(" and (", " (");
		}
		System.out.println("WHERENOTDATE :"+whereNotDate);
		System.out.println("WHEREDATE :"+whereDateRanges);

		return whereNotDate + whereDateRanges;
	}

	private List extractDateRanges(String operator,String json) {
		List list = new ArrayList();
		JSONObject obj = null;
		String result = " "+operator+" (";
		List<String> lsRemovedKeys = new ArrayList<String>();
		try {
			JSONParser parser = new JSONParser();
			obj = (JSONObject) parser.parse(json);
			Set<String> keys = obj.keySet();
			for(String key:keys) {
				String v = (String)obj.get(key).toString();

				if(v.contains("~")) {
					String vArr[]  =v.split("~");
					result = result+key+" between '"+vArr[0]+"' and '"+vArr[1]+"' "+operator+" ";
					lsRemovedKeys.add(key);
				}	
			}
			if(result.length()!=5 && result.length()!=6) {
				result = result+" )";
				result = result.replace(" and  )", ")");
				result = result.replace(" or  )", ")");
				list.add(result);
				for(String k:lsRemovedKeys) {
					obj.remove(k);
				}
				list.add(obj);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	public boolean checkDate(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt = sdf.parse(strDate);
			String strDate2 = sdf.format(dt);
			if(strDate.equals(strDate2))
				return true;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}


	public List<String> betweenDays(String from,String to) throws Exception{
		List<String> list = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);

		String strTempFrom = from;
		String strTempTo=to;
		Calendar calendarTemp=Calendar.getInstance();

		while(!strTempFrom.equals(strTempTo)) {
			calendarTemp.setTime(sdf.parse(strTempFrom));

			calendarTemp.add(Calendar.DAY_OF_YEAR, 1);
			Date dt = calendarTemp.getTime();

			strTempFrom=sdf.format(dt);
			list.add(strTempFrom);
		}
		return list;
	}


	public  HashMap<String, Integer> sortMap(HashMap<String, Integer> hashMap) 
	{ 
		List<Map.Entry<String, Integer> > list = new LinkedList<Map.Entry<String, Integer> >(hashMap.entrySet()); 
		Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() { 
			public int compare(Map.Entry<String, Integer> o1,  
					Map.Entry<String, Integer> o2) 
			{ 

				return (o2.getValue()).compareTo(o1.getValue());
				//return (o1.getValue()).compareTo(o2.getValue()); 
			} 
		});
		HashMap<String, Integer> returnMap = new LinkedHashMap<String, Integer>(); 
		for (Map.Entry<String, Integer> map : list) { 
			returnMap.put(map.getKey(), map.getValue()); 
		} 
		return returnMap; 
	} 










	public long latency(long l_time_start) {
		long l_time_end = System.currentTimeMillis();
		long l_diff = l_time_end-l_time_start;
		return l_diff;

	}




public String getRandomNumber(int length) {
	Random rand = new Random();
	 String str = "1";
	 for(int i=1;i<=length;i++) {
		 str = str+"0";
	 }
	 int number = Integer.parseInt(str);
		String rand_number = String.format("%0"+length+"d", rand.nextInt(number));
return rand_number;		
}




public String getDate(Date dt,String pattern) {
	SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	String str = sdf.format(dt);
	return str;
	
}


}
