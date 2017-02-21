package com.yk.moments.httpUtil;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**   
* @Title: JsonToData.java 
* @Package 
* @Description: TODO
* @author yk
* @date 2015年8月19日 上午10:56:09 
* @version V1.0   
*/
public class JsonToDataUtil {
	
	/**
	* @author yk 
	* @date 2015年9月1日 下午4:10:57 
	* @Title: initJsonData 
	* @Description: 获取json数据
	* @param jsonstr
	* @param cla
	* @return    设定文件 
	* @return List<T>    返回类型 
	* @throws
	 */
	public static <T> List<T> initJsonDataOfLsObject(String jsonstr,Class<?> cla)
	{
		List<T> listdata=new ArrayList<T>();
		Field[] field=cla.getDeclaredFields();
		for(Field f:field) 
		{
			f.setAccessible(true);
		}
		try {
			JSONObject jsonobject=new JSONObject(jsonstr);
			JSONArray jsonarray=jsonobject.getJSONArray("data");
			for(int i=0;i<jsonarray.length();i++)
			{
				try {
					T T_stance=(T) cla.newInstance();
					jsonobject=jsonarray.getJSONObject(i);
					for(Field f:field)
					{
						try {
							
							if(f.getGenericType().toString().equals("class java.lang.Integer"))
							{
								if(jsonobject.has(f.getName()))
								{
									f.set(T_stance, Integer.valueOf(jsonobject.get(f.getName()).toString()));
								}
							}
							else if(f.getGenericType().toString().equals("class java.lang.String"))
							{
								if(jsonobject.has(f.getName()))
								{
									f.set(T_stance, jsonobject.get(f.getName()).toString());
								}
							}
						} catch (Exception e) {
							f.set(T_stance, "");
						}
					}
					listdata.add(T_stance);
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listdata;
	}
	
	
	public static <T> T initJsonDatatoObject(String jsonstr,Class<?> cla)
	{
		T T_stance=null;
		Field[] field=cla.getDeclaredFields();
		for(Field f:field) 
		{
			f.setAccessible(true);
		}
		try {
			T_stance=(T) cla.newInstance();
			JSONObject jsonobject=new JSONObject(jsonstr);
			for(Field f:field)
			{
				try {
					
					if(f.getGenericType().toString().equals("class java.lang.Integer"))
					{
						if(jsonobject.has(f.getName()))
						{
							f.set(T_stance, Integer.valueOf(jsonobject.get(f.getName()).toString()));
						}
					}
					else if(f.getGenericType().toString().equals("class java.lang.String"))
					{
						if(jsonobject.has(f.getName()))
						{
							f.set(T_stance, jsonobject.get(f.getName()).toString());
						}
					}
				} catch (Exception e) {
					f.set(T_stance, "");
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return T_stance;
	}
	
	
	public static <T> List<T> initJsonDataOfArray(String jsonstr,Class<?> cla)
	{
		List<T> listdata=new ArrayList<T>();
		Field[] field=cla.getDeclaredFields();
		for(Field f:field) 
		{
			f.setAccessible(true);
		}
		try {
			JSONArray jsonarray=new JSONArray(jsonstr);
			for(int i=0;i<jsonarray.length();i++)
			{
				if(i>4)
				{
					Log.i("YK", ""+i);
				}
				try {
					T T_stance=(T) cla.newInstance();
					JSONObject jsonobject=jsonarray.getJSONObject(i);
					for(Field f:field)
					{
						try {
							if(f.getGenericType().toString().startsWith("class java.lang"))
							{
								if(f.getGenericType().toString().equals("class java.lang.Integer"))
								{
									if(jsonobject.has(f.getName()))
									{
										f.set(T_stance, Integer.valueOf(jsonobject.get(f.getName()).toString()));
									}
								}
								else if(f.getGenericType().toString().equals("class java.lang.String"))
								{
									if(jsonobject.has(f.getName()))
									{
										f.set(T_stance, jsonobject.get(f.getName()).toString());
									}
								}
							}
							else if(f.getGenericType().toString().startsWith("java.util.List"))
							{
								if(f.getType().isAssignableFrom(List.class))
							    {   
						             Type ft = f.getGenericType();
						             if(ft == null) continue;  
						             if(ft instanceof ParameterizedType)
						            {   
						                   ParameterizedType pt = (ParameterizedType) ft;  
						                   Class class_parameter = (Class)pt.getActualTypeArguments()[0];
						                   if(jsonobject.has(f.getName()))
						                   {
						                	   List<Object> ls_Object=initJsonDataOfArray(jsonobject.get(f.getName()).toString(),class_parameter);
						                	   f.set(T_stance, ls_Object);
						                   }
						             }   
							    } 
							}
							else
							{
								Class class_custom= f.getType();
								if(jsonobject.has(f.getName()))
								{
									f.set(T_stance, initJsonDatatoObject(jsonobject.get(f.getName()).toString(),class_custom));
								}
							}
						} 
						catch (JSONException e) {
							throw e;
						}
					}
					listdata.add(T_stance);
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listdata;
	}
	
	/**
	 * @throws JSONException 
	* @author yk 
	* @date 2015年9月1日 下午4:11:14 
	* @Title: initJsonSuccess 
	* @Description: 获取success
	* @param jsonstr
	* @return
	* @throws JSONException    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public boolean initJsonSuccess(String jsonstr) throws JSONException
	{
		StringBuffer result=new StringBuffer();
		JSONObject jsonobject=new JSONObject(jsonstr);
		result.append(jsonobject.get("success").toString());
		return Boolean.valueOf(result.toString());
	}
	
	/**
	* @author yk 
	* @date 2015年9月1日 下午4:11:34 
	* @Title: initJsonMsg 
	* @Description: 获取msg
	* @param jsonstr
	* @return
	* @throws JSONException    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String initJsonMsg(String jsonstr) throws JSONException
	{
		StringBuffer result=new StringBuffer();
		JSONObject jsonobject=new JSONObject(jsonstr);
		result.append(jsonobject.get("msg").toString());
		return result.toString();
	}
}
