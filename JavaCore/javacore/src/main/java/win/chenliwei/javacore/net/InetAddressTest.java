/**
 * @author: ChenLiwei
 * 2017-03-15
 * InetAddressTest.java
 * Comments: It is to demonstrate how to get the addresses list of a domain name
 * and also print the Internet address of your own
 * Since I have two net cards installed in my pc, so it is different with the way from the book
 */
package win.chenliwei.javacore.net;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class InetAddressTest {

	public static void main(String[] args) throws UnknownHostException, SocketException {
		if(args.length != 0){
			String host = args[0];
			InetAddress[] remoteHostAddresses = InetAddress.getAllByName(host);
			for(InetAddress address : remoteHostAddresses) System.out.println(address);
			
		}else{
			Enumeration<NetworkInterface> mynet = NetworkInterface.getNetworkInterfaces();
			for(NetworkInterface subnet : Collections.list(mynet)){
				if(subnet.getHardwareAddress() != null){
					List<InterfaceAddress> localHostAddress = subnet.getInterfaceAddresses();
					for(InterfaceAddress address : localHostAddress) {
						if(address.getBroadcast()!= null)System.out.println(address.getAddress());
					}
				}
			}
		}
	}

}
