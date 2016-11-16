package it.pkg.client;

import java.util.ArrayList;
import java.util.List;

public class ChannelCode {
	public static final int NONE = 0;// 无渠道
	public static final int TEST = 1;// 测试渠道
	public static final int IOS_APP_STORE = 100000;// app store
	public static final int IOS_XY = 100001;// xy助手
	public static final int ANDROID_360 = 100002;// 360
	public static final int ANDROID_XIAOMI = 100003;// 小米
	public static final int ANDROID_JIFENG = 100004;// 机锋
	public static List<Integer> channels = new ArrayList<Integer>();
	static {
		channels.add(NONE);
		channels.add(TEST);
		channels.add(IOS_APP_STORE);
		channels.add(IOS_XY);
		channels.add(ANDROID_360);
		channels.add(ANDROID_XIAOMI);
		channels.add(ANDROID_JIFENG);
	}
}
