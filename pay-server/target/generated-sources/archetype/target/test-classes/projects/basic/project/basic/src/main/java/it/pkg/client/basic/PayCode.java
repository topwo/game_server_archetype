package it.pkg.client.basic;

public class PayCode {
	public static final int NOT_VALIDATE = 0;// 未验证
	public static final int VALIDATE_ERR = 1;// 验证失败
	public static final int NOT_SEND = 2;// 未发货
	public static final int SEND_ERR = 3;// 发货失败
	public static final int FINISH = 4;// 订单完成
}
