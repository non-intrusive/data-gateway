package cn.snaildev.zuul.gateway.constants;

/**
 * @author: Ming.Zhao
 * @create: 2019-04-09 20:03
 **/
public enum RocketMQErrorEnum implements ErrorCode {
    PARAMM_NULL("MQ_001", "参数为空"),
    NOT_FOUND_CONSUMESERVICE("MQ_100", "根据topic和tag没有找到对应的消费服务"),
    HANDLE_RESULT_NULL("MQ_101", "消费方法返回值为空"),
    CONSUME_FAIL("MQ_102", "消费失败");

    private String code;
    private String msg;

    private RocketMQErrorEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    /**
     * 错误码
     *
     * @return
     */
    public String getCode() {
        return this.code;
    }

    /**
     * 错误信息
     *
     * @return
     */
    public String getMsg() {
        return this.msg;
    }
}
