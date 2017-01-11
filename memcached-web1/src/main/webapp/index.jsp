<%@page import="java.util.concurrent.Future"%>
<%@page import="java.net.InetSocketAddress"%>
<%@page import="net.spy.memcached.MemcachedClient"%>
<html>
<body>
<h2>Hello, Memcached!!!</h2>
<%

MemcachedClient memcachedClient 
= new MemcachedClient(new InetSocketAddress("appstore.konylabs.net", 11211));

String cachedValue = (String) memcachedClient.get("bipin");

if(cachedValue==null){
	Future<Boolean> future = memcachedClient.set("bipin", 180, "married to neha talreja");
	out.println("key: bipin was NOT found and has been stored fresh on memcached: "+ future.get());
	out.println("<br/>");
}else{
	out.println("key: bipin has value as: " + cachedValue);
	out.println("<br/>");
}

%>
</body>
</html>
