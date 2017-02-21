package com.yk.moments.httpUtil;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.util.Log;

/**   
* @Title: HttpJsonThread.java 
* @Package
* @Description: TODO
* @author yk
* @date 2015��8��1�� ����9:53:31 
* @version V1.0   
*/
public abstract class JsonListUtil<T> extends Thread{
	
	private String murl;
	private Handler mhandler;
	private Class<?> mclass; 
	private Context mContext;
	/**
	* @author yk
	* @date 2015��8��3�� ����3:47:29 
	* @param content ������
	* @param url ���ʵ�ַ
	* @param cla list�����������
	* @param handler UI����
	* @param listview list�ؼ�
	* @param layoutId ���list��item��id
	 */
	public JsonListUtil(Context context,String url) {
		// TODO Auto-generated constructor stub
		this.murl=url;
		this.mhandler=new Handler();
		this.mclass=getMyClass();
		mContext=context;
	}
	
	/**
	 * http��ȡjson
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String resultStr="";
		try {
			SharedPreferences sp = mContext.getSharedPreferences("sp_com", Context.MODE_PRIVATE);
			resultStr = sp.getString(murl, "");
			Log.i("YK", resultStr);
			if("".equals(resultStr))
			{
				resultStr = HttpClientGetOperate.httpClientGet(murl);
				Editor editor = sp.edit();
				editor.putString(murl, resultStr);
				editor.commit();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!resultStr.equals(""))
		{
			final List<T> lt=JsonToDataUtil.initJsonDataOfArray(resultStr, mclass);

			mhandler.post(new Runnable() {
				public void run() {
					initShowData(lt,mclass);
				}
			});
			
		}
	}
	
	/**
	* @author yk 
	* @date 2015��8��17�� ����11:28:12 
	* @Title: getMyClass 
	* @Description: ��ȡʵ�����ķ���class����
	* @return    �趨�ļ� 
	* @return Class<T>    �������� 
	* @throws
	 */
	public Class<T> getMyClass()
	{
		Class<T> cla=null;
		cla =  (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		return cla;
	}


	/**
	* @author yk 
	* @date 2015��8��3�� ����3:04:44 
	* @Title: initShowData 
	* @Description: ��http������������ݽ��д���
	* @return void    �������� 
	* @throws
	 */
	public abstract void initShowData(List<T> datas,Class cla);

}


