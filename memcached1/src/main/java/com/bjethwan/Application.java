package com.bjethwan;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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
			
			future = memcachedClient.set("bipin", 180, "married to neha talreja jethwani");
			System.out.println("Trying to store another value for key: bipin: "+ future.get());

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
