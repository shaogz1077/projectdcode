package com.echo;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * projectdcode
 *
 * @version V1.0
 * @Package: com.echo
 * @company: byb
 * @author: ollie
 * @date 2015/9/21 17:09
 */
public class Commons {
    /**
     * 返回wifi下的ip地址
     *
     * @return
     */
    public static String getIpAddress() {
        try {

            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()
                            && inetAddress instanceof Inet4Address) {

                        return inetAddress.getHostAddress().toString();
                    }
                }
            }

        } catch (Exception e) {

        }
        return null;
    }
}
