package com.joh.rhms.config;

import java.io.File;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	private final int maxUploadFileSize = 2 * 1024 * 1024; // 5 MB

	private static final Logger LOGGER = LoggerFactory.getLogger(WebInitializer.class);

	public WebInitializer() {
		System.out.println("WebInitializer");
	}

	@Override
	protected Filter[] getServletFilters() {
		LOGGER.info("WebInitializer: Configuring Servlet Filters");
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		return new Filter[] { characterEncodingFilter };
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { SecurityConfig.class, SpringContextConfiguraror.class };

	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		OpenEntityManagerInViewFilter openEntityInViewFilter = new OpenEntityManagerInViewFilter();
		openEntityInViewFilter.setEntityManagerFactoryBeanName("entityManagerFactory");
		Dynamic filterRegistration = servletContext.addFilter("openEntityInViewFilter", openEntityInViewFilter);
		filterRegistration.addMappingForUrlPatterns(null, true, "/*");
	}

	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {

		File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));

		MultipartConfigElement multipartConfigElement = new MultipartConfigElement(uploadDirectory.getAbsolutePath(),
				maxUploadFileSize, maxUploadFileSize * 2, maxUploadFileSize / 2);

		registration.setMultipartConfig(multipartConfigElement);

	}

}
