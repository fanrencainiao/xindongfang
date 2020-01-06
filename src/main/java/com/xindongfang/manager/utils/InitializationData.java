package com.xindongfang.manager.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;




/** @version:（1.0） 
* @ClassName	InitializationData
* @Description: （初始化数据） 
*/
@Component
public class InitializationData  implements CommandLineRunner {
	protected Log log=LogFactory.getLog(InitializationData.class);
	


	@Override
	public void run(String... args) throws Exception {
		
		initSuperAdminData();

		
	}
	
	
	/**
       * 初始化异常信息数据
	* @throws Exception
	*/
//	private void initErrorMassageData() throws Exception{
//		if(null==resource) {
//			System.out.println("error initErrorMassageData  resource is null");
//			return;
//		}
//		DBCollection errMsgCollection = getDatastore().getCollection(ErrorMessage.class);
//		
//		if(errMsgCollection == null || errMsgCollection.count()==0) {
//			
//			BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
//			StringBuffer message = new StringBuffer();
//			String line = null;
//			while ((line = br.readLine()) != null) {
//				message.append(line);
//			}
//			String defaultString = message.toString();
//			if(!StringUtil.isEmpty(defaultString)){
//				List<ErrorMessage> errorMessages = JSONObject.parseArray(defaultString, ErrorMessage.class);
//				errorMessages.forEach(errorMessage ->{
//					getDatastore().save(errorMessage);
//				});
//				
//			}
//			log.info("\n"+">>>>>>>>>>>>>>> 异常信息数据初始化完成  <<<<<<<<<<<<<");
//		}
//	}
	
	
	/**
        * 初始化默认超级管理员数据
	*/
	private void initSuperAdminData() {
		
		
			log.info("\n" + ">>>>>>>>>>>>>>> 默认系统通知数据初始化完成  <<<<<<<<<<<<<");
		
		
	}


	
}
