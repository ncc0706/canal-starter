package io.github.ncc0706.canal.client.context;


import io.github.ncc0706.canal.client.model.CanalModel;

public class CanalContext {

    private static ThreadLocal<CanalModel> threadLocal = new ThreadLocal<>();

    public static CanalModel getModel() {
        return threadLocal.get();
    }

    public static void setModel(CanalModel canalModel) {
        threadLocal.set(canalModel);
    }

    public static void removeModel() {
        threadLocal.remove();
    }
}
