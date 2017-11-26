/**
 * @company JJE
 * @author Emily.Wang
 * @Time 2017年11月14日 上午5:51:09
 * @Description
 */
package com.haowu.api.msg;

import shelper.iffixture.HttpFixture;

public class LoginDemoMsg {
	public String getRequest(String username,String password)
	{
		HttpFixture hf=new HttpFixture();
		String url="http://www.51testing.com/batch.login.php?action=login";
		String body="username="+username+"&password="+password+"&cookietime=0&loginsubmit=true&refer=http%253A%252F%252Fwww.51testing.com%252F%253Faction-register";
		hf.setUrl(url);
		hf.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
		hf.addRequestBody(body);
		hf.Post();
		System.out.println(hf.getResponseBody());
		return hf.getResponseBody();
	}

}

