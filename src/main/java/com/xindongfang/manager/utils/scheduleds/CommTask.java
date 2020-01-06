package com.xindongfang.manager.utils.scheduleds;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.scheduling.config.TaskManagementConfigUtils;
import org.springframework.stereotype.Component;

import com.xindongfang.manager.utils.ThreadUtil;
import com.xindongfang.manager.utils.supper.Callback;

@Component
@EnableScheduling
public class CommTask implements ApplicationListener<ApplicationContextEvent>{

	protected Log log=LogFactory.getLog("sche");
	
	
	
	@Resource(name = TaskManagementConfigUtils.SCHEDULED_ANNOTATION_PROCESSOR_BEAN_NAME)
	private ScheduledAnnotationBeanPostProcessor scheduledProcessor;
	 public CommTask() {
			super();
	 }

	 @Override
	public void onApplicationEvent(ApplicationContextEvent event) {
		 if(event.getApplicationContext().getParent() != null)
			 return;
		 //root application context 没有parent，他就是老大.
		 //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
		
				
			
						
						ThreadUtil.executeInThread(new Callback() {
							@Override
							public void execute(Object obj) {
								 try {
										Thread.currentThread().sleep(10000);
										scheduledProcessor.destroy();
										System.out.println("====定时任务被关闭了=======》");
								 	} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
							}
						});
						
//				 System.out.println("====定时任务开启中=======》");
	}
	
	@Scheduled(cron = "0 0 0/1 * * ?")
	public void execute() {
		
		
	}
	
}
