package com.bjethwan;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import net.spy.memcached.CASResponse;
import net.spy.memcached.CASValue;
import net.spy.memcached.MemcachedClient;

public class Application {

	public static void main(String[] args) {

		set();
		

	}

	public static void set(){
		try {

			MemcachedClient memcachedClient 
				= new MemcachedClient(new InetSocketAddress("appstore.konylabs.net", 11211));

			Future<Boolean> future = memcachedClient.set("bipin", 180, "married to neha talreja");
			System.out.println("key: bipin is stored on memcached: "+ future.get());
			
			
			
			TimeUnit.MINUTES.sleep(1);
			
			CASValue<Object> casValue = memcachedClient.gets("bipin");
			
			CASResponse casResponse = memcachedClient.cas("bipin", casValue.getCas(), "married to neha talreja jethwani");
			
			// display CAS Response
	         System.out.println("CAS Response - " + casResponse);
	         
			memcachedClient.shutdown();

		} catch (IOException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

	
	public static void get(){
		try {

			MemcachedClient memcachedClient = new MemcachedClient(new InetSocketAddress("appstore.konylabs.net", 11211));

			System.out.println("key: bipin has value as: " + memcachedClient.get("bipin"));

			memcachedClient.shutdown();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
