package com.base.other;

import java.util.UUID;
/**
 * UUID类测试
 * @author Administrator
 *
 */
public class CeshiUUID {
public static void main(String[] args) {
	UUID uuid = UUID.randomUUID();
	System.out.println("String: "+uuid.toString());
	System.out.println("hashcode: "+uuid.hashCode());
	System.out.println("variant: "+uuid.variant());
	System.out.println("getLeastSignificantBits: "+uuid.getLeastSignificantBits());
	System.out.println("getMostSignificantBits: "+uuid.getMostSignificantBits());
	//包含绝对值的64位UUID
	System.out.println("getMostSignificantBits: "+Math.abs(UUID.randomUUID().getMostSignificantBits()));
}
}
