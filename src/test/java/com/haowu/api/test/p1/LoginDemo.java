/**
 * @company JJE
 * @author Emily.Wang
 * @Time 2017年11月14日 上午5:49:39
 * @Description
 */
package com.haowu.api.test.p1;

import java.util.Iterator;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.haowu.api.msg.LoginDemoMsg;

import shelper.datadrive.ExcelProvider;
import shelper.environment.Env;

public class LoginDemo extends Env{
	@Test(dataProvider="testData")
	public void test(Map<String,String>dataDriven)
	{
		new LoginDemoMsg().getRequest(dataDriven.get("username"),dataDriven.get("password"));
	}
	@DataProvider
	public Iterator<Object[]> testData()
	{
		return new ExcelProvider(this,"test");
	}
}

