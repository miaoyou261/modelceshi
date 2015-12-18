package com.base.other;


import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class GetIP {
	/**
	 * ������linux�ϻ�ȡIP������,���в���������һ̨���������IP������²�����
	 * �ų���127.0.0.1���Ĭ��IP
	 * @return
	 */
	public static String GetServiceIP() {
		Enumeration allNetInterfaces;
		try {
			allNetInterfaces = NetworkInterface.getNetworkInterfaces();
		
		InetAddress ip = null;
		while (allNetInterfaces.hasMoreElements())
		{
		NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
//		System.out.println(netInterface.getName());
		Enumeration addresses = netInterface.getInetAddresses();
		while (addresses.hasMoreElements())
		{
		ip = (InetAddress) addresses.nextElement();
		if (ip != null && ip instanceof Inet4Address)
		{
			if (!ip.getHostAddress().equals("127.0.0.1")) {
				System.out.println("������IP = " + ip.getHostAddress());
				return ip.getHostAddress();
			}
		}
		}
		}
		return ip.getHostAddress();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		System.out.println(GetServiceIP());
	}
}
