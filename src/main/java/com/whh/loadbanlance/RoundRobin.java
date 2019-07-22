package com.whh.loadbanlance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.lowagie.text.List;

/**
 * @desc: 负载均衡算法：轮询算法
 */
public class RoundRobin {
	static Map<String,Integer> serverWeithMap = new HashMap<String, Integer>();
	static {
		serverWeithMap.put("192.168.1.15", 1);
		serverWeithMap.put("192.168.1.16", 2);
		serverWeithMap.put("192.168.1.17", 2);
		serverWeithMap.put("192.168.1.18", 3);
		serverWeithMap.put("192.168.1.19", 3);
	}
	Integer pos=0;
	public String roundRobin() {
		Map<String, Integer> serverMap = new HashMap<String, Integer>();
		serverMap.putAll(serverWeithMap);
		Set<String> keySet = serverMap.keySet();
		ArrayList<String> keyList = new ArrayList<String>();
		keyList.addAll(keySet);
		String server =null;
		synchronized (pos) {
			if(pos>=keySet.size()) {
				pos=0;
			}
			server=keyList.get(pos);
			pos++;
		}
		
		return server;
	} 
	
	public static void main(String[] args) {
		RoundRobin robin = new RoundRobin();
		for(int i=0;i<100;i++) {
			String ip = robin.roundRobin();
			System.out.println(ip);
		}
	}
	
}
