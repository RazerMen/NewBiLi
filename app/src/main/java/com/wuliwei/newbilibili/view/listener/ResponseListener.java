package com.wuliwei.newbilibili.view.listener;

/**
 * Created by
 * 武立伟
 * 2017/3/30.
 * <p>
 * 作用：
 */

public interface ResponseListener {
    /**
     * @param progress 已经下载或上传字节数
     * @param total    总字节数
     * @param done     是否完成
     */

    void onProgress(long progress, long total, boolean done);

    void onResponse();

    void onFailure(String error);
}
