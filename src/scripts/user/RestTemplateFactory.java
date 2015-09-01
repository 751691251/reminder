package com.ibm.iga.reminder.service.user;

import org.apache.http.HttpHost;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateFactory  implements FactoryBean<RestTemplate>, InitializingBean {

	private RestTemplate restTemplate;
	@Override
	public void afterPropertiesSet() throws Exception {
		HttpHost host = new HttpHost("lmc2.watson.ibm.com", 15013, "http");
        restTemplate = new RestTemplate(
          new HttpComponentsClientHttpRequestFactoryBasicAuth(host));
		
	}

	@Override
	public RestTemplate getObject() throws Exception {
		
		return restTemplate;
	}

	@Override
	public Class<?> getObjectType() {
		return RestTemplate.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
