package mongo.util;


import java.util.Map;

/**
 * Created by pengshu on 2016/12/7.
 */
public class BaseController {

    /**
     * 返回  api 结果
     *
     * @param obj
     */
    public ApiResult apiRult(Object obj) {
        ApiResult json = new ApiResult();
        json.setCode(0);
        json.setMessage("ok");
        json.setValue(obj);
        return json;
    }

    /**
     * 返回  api 结果 bootstrap 专用
     *
     * @param obj
     */
    public BootstrapApi bootstrapApiRult(Map obj) {
        BootstrapApi bootstrapApi = new BootstrapApi();
        bootstrapApi.setCode(0);
        bootstrapApi.setMessage("ok");
        bootstrapApi.setTotal((Long) obj.get("total"));
        bootstrapApi.setRows(obj.get("result"));
        return bootstrapApi;
    }

    /**
     * 返回提示信息
     *
     * @param message
     * @return
     */
    public ErrorResult errorApiRult(String message) {
        ErrorResult error = new ErrorResult();
        error.setCode(-1);
        error.setMessage(message);
        return error;
    }
}
