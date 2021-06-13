package com.example.demo.service;

import java.io.FileReader;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class ResourceLoaderService implements ResourceLoaderAware {

	private ResourceLoader resourceLoader;
	
	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
			this.resourceLoader = resourceLoader;
			System.out.println("Invoked resourceLoader");
	}

	public ResourceLoader getResourceLoader() {
		return resourceLoader;
	}
	
	public RSAPrivateKey show() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
		Resource resource = resourceLoader.getResource("classpath:/key/mykey.pem");
		System.out.println("File is exists "+resource.exists());	
		KeyFactory factory = KeyFactory.getInstance("RSA");

	    try (FileReader keyReader = new FileReader(resource.getFile());
	      PemReader pemReader = new PemReader(keyReader)) {

	        PemObject pemObject = pemReader.readPemObject();
	        byte[] content = pemObject.getContent();
	        PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(content);
	        System.out.println("Type casting RSA private key");
	        return (RSAPrivateKey) factory.generatePrivate(privKeySpec);
	    }
	}
	
}
