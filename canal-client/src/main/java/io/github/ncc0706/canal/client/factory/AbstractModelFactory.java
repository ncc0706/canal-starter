package io.github.ncc0706.canal.client.factory;

import io.github.ncc0706.canal.client.enums.TableNameEnum;
import io.github.ncc0706.canal.client.handler.EntryHandler;
import io.github.ncc0706.canal.client.util.GenericUtil;
import io.github.ncc0706.canal.client.util.HandlerUtil;

public abstract class AbstractModelFactory<T> implements IModelFactory<T> {

    @Override
    public <R> R newInstance(EntryHandler entryHandler, T t) throws Exception {
        String canalTableName = HandlerUtil.getCanalTableName(entryHandler);
        if (TableNameEnum.ALL.name().toLowerCase().equals(canalTableName)) {
            return (R) t;
        }
        Class<R> tableClass = GenericUtil.getTableClass(entryHandler);
        if (tableClass != null) {
            return newInstance(tableClass, t);
        }
        return null;
    }

    abstract <R> R newInstance(Class<R> c, T t) throws Exception;
}
