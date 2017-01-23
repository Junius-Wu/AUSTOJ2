package cn.edu.aust.common.service;

/**
 * 执行回调任务接口
 * @author Niu Li
 * @date 2017/1/22
 */
//R返回参数  P传入参数
public interface WorkCallBack<R,P> {
    /**
     * 回调接口
     * @param p 传入参数
     * @return 返回类型
     */
    R execute(P p);
}
