package com.jwd.jokers.manager;

import java.util.HashMap;
import java.util.Map;
import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jwd.jokers.App;
import com.jwd.jokers.manager.impl.ImplManager;
import com.jwd.jokers.utils.FileUtils;
import com.jwd.jokers.utils.HttpConnectionUtils;

public abstract class BaseManager implements ImplManager {
	protected String filePath;
	protected String url;
	protected String json;
	protected int httpMethod;

	private static RequestQueue requestQueue = Volley.newRequestQueue(App
			.getInstance());// 消息队列

	public BaseManager() {
		filePath = configFilePath();
		url = configUrl();
	}

	private void loadFile() {
		try {
			json = (String) FileUtils.unserializeObject(filePath);
			onFileFinish(json);
		} catch (Exception e) {
			onFileError(FileError.NO_DATA);
		}
	}

	private void saveFile(String result) {
		try {
			FileUtils.serializeObject(filePath, result);
		} catch (Exception e) {
		}
	}

	private void loadWeb(final HashMap<String, String> params) {
		try {
			if (httpMethod == Method.GET) {
				HttpConnectionUtils.setUrlParameter(url, params);
				System.out.println("loadWeb url=" + url);
			}
			StringRequest request = new StringRequest(httpMethod, url,
					new Response.Listener<String>() {
						@Override
						public void onResponse(String response) {
							saveFile(response);
							onWebFinish(response);
						}
					}, new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError error) {
							// TODO Auto-generated method stub
							onWebError(WebError.CONNECT_FAIL);
						}

					}) {
				@Override
				protected Map<String, String> getParams()
						throws AuthFailureError {
					// TODO Auto-generated method stub
					if (httpMethod == Method.POST) {
						return params;
					}
					return super.getParams();
				}
			};
			requestQueue.add(request);
		} catch (Exception e) {
			// TODO: handle exception
			onWebError(WebError.CONNECT_FAIL);
		}
	}

	public void excute(HashMap<String, String> params) {
		loadFile();
		loadWeb(params);
	}
}
