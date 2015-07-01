package com.jwd.jokers.manager;

import com.jwd.jokers.utils.FileUtils;

public class JokeListManager extends BaseManager {

	@Override
	public String configFilePath() {
		// TODO Auto-generated method stub
		return FileUtils.getCollectFilePath();
	}

	@Override
	public String configUrl() {
		// TODO Auto-generated method stub
		return "http://route.showapi.com/107-33?showapi_appid=760&showapi_sign=1BF9176DE6EBA0A99F57A6BAB564AD91&showapi_timestamp=2015-06-16+13%3A19%3A35";
	}

	@Override
	public void onWebFinish(String result) {
		// TODO Auto-generated method stub
		System.out.println("onWebFinish result=" + result);
	}

	@Override
	public void onWebError(WebError errorCode) {
		// TODO Auto-generated method stub
		System.out.println("onWebError errorCode=" + errorCode);
	}

	@Override
	public void onFileFinish(String result) {
		// TODO Auto-generated method stub
		System.out.println("onFileFinish result=" + result);
	}

	@Override
	public void onFileError(FileError errorCode) {
		// TODO Auto-generated method stub
		System.out.println("onFileError errorCode=" + errorCode);
	}

	@Override
	public void onParse(String json) {
		// TODO Auto-generated method stub

	}

}
