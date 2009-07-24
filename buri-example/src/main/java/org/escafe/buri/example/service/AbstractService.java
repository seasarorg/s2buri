package org.escafe.buri.example.service;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.persistence.OptimisticLockException;

import org.seasar.extension.jdbc.EntityMeta;
import org.seasar.extension.jdbc.EntityMetaFactory;
import org.seasar.extension.jdbc.PropertyMeta;
import org.seasar.extension.jdbc.manager.JdbcManagerImplementor;
import org.seasar.extension.jdbc.service.S2AbstractService;
import org.seasar.extension.jdbc.where.SimpleWhere;
import org.seasar.framework.util.FieldUtil;
import org.seasar.framework.util.tiger.CollectionsUtil;

/**
 * サービスの抽象クラスです。
 * 
 * @author S2JDBC-Gen
 * @param <E>
 *            エンティティの型
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public abstract class AbstractService<E> extends S2AbstractService<E> {

    /**
     * {@link JdbcManagerImplementor}です。
     */
    public JdbcManagerImplementor jdbcManagerImplementor;

    private final AtomicBoolean locking = new AtomicBoolean(false);

    private static final long SYSTEM_USER_ACCOUNT_ID = 1L;

    private final String deleteTypeName = "deleteType";

    private final String disableTypeName = "disableType";

    /**
     * 全レコードを削除します。
     */
    public void deleteAll() {
        EntityMeta entityMeta = getEntityMeta();
        String tableName = entityMeta.getTableMeta().getName();
        String sql = String.format("DELETE FROM %s", tableName);
        jdbcManager.updateBySql(sql).execute();
    }

    /**
     * ID指定で削除します．
     * 
     * @param id
     *            ID
     * @return 削除したレコード数
     */
    protected int deleteByIds(Object... id) {
        EntityMeta entityMeta = getEntityMeta();
        String tableName = entityMeta.getTableMeta().getName();
        String sql = String.format("DELETE FROM %s", tableName);
        StringBuilder sb = new StringBuilder(sql);
        sb.append(" WHERE ");
        Iterator<PropertyMeta> propertyMetaIterator = entityMeta
            .getIdPropertyMetaList()
            .iterator();
        List<Class<?>> paramClassList = CollectionsUtil.newArrayList();
        while (propertyMetaIterator.hasNext()) {
            PropertyMeta propertyMeta = propertyMetaIterator.next();
            sb.append(propertyMeta.getColumnMeta().getName()).append(" = ? ");
            Class<?> propertyClass = propertyMeta.getPropertyClass();
            paramClassList.add(propertyClass);
            if (propertyMetaIterator.hasNext()) {
                sb.append(" AND ");
            }
        }
        int result = jdbcManager
            .updateBySql(
                sb.toString(),
                paramClassList.toArray(new Class<?>[paramClassList.size()]))
            .params(id)
            .execute();
        return result;
    }

    public E findById(Object... keys) {
        return select().id(keys).getSingleResult();
    }

    /**
     * エンティティメタを返します。
     * 
     * @return {@link EntityMeta}
     */
    protected EntityMeta getEntityMeta() {
        EntityMetaFactory entityMetaFactory = jdbcManagerImplementor
            .getEntityMetaFactory();
        EntityMeta entityMeta = entityMetaFactory.getEntityMeta(entityClass);
        return entityMeta;
    }

    /**
     * エンティティのプライマリキーに値が設定されているかどうかを返します．
     * 
     * @param entity
     *            エンティティ
     * @return プライマリキーに値が設定されている場合はtrue, それ以外はfalse
     */
    public boolean hasPrimaryKeyValue(E entity) {
        boolean result = true;
        for (PropertyMeta pm : this.getEntityMeta().getIdPropertyMetaList()) {
            Field f = pm.getField();
            Object id = FieldUtil.get(f, entity);
            result = result && (id != null);
        }
        return result;
    }

    /*
     * (非 Javadoc)
     * 
     * @see
     * org.seasar.extension.jdbc.service.S2AbstractService#insert(java.lang.
     * Object)
     */
    @Override
    public int insert(E entity) {
        try {
            return super.insert(entity);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * 挿入もしくは更新を行います．
     * 
     * @param entity
     *            エンティティ
     * @return 更新レコード数
     */
    public int insertOrUpdate(E entity) {
        int result = 0;
        RuntimeException ex = null;
        if (hasPrimaryKeyValue(entity) == false) {
            result = insert(entity);
        } else {
            try {
                result = update(entity);
            } catch (OptimisticLockException e) {
                ex = e;
            }
            if (ex != null) {
                if (!isExistEntity(entity)) {
                    result = insert(entity);
                } else {
                    throw ex;
                }
            }
        }
        return result;
    }

    /**
     * エンティティが存在するかどうかをチェックします．
     * 
     * @param entity
     *            エンティティ
     * @return 存在する場合はtrue, それ以外はfalse
     */
    public boolean isExistEntity(E entity) {
        SimpleWhere where = new SimpleWhere();
        for (PropertyMeta pm : this.getEntityMeta().getIdPropertyMetaList()) {
            Field f = pm.getField();
            Object id = FieldUtil.get(f, entity);
            where.eq(pm.getName(), id);
        }
        return select().where(where).getCount() > 0;
    }

    protected void lockTable() {
        if (!locking.get()) {
            String tableName = this.getEntityMeta().getTableMeta().getName();
            jdbcManager.updateBySql(
                String.format(
                    "LOCK TABLE %s IN ACCESS EXCLUSIVE MODE",
                    tableName)).execute();
            locking.set(true);
        }
    }

    protected void unlockTable() {
        if (locking.get()) {
            locking.set(false);
        }
    }

    /*
     * (非 Javadoc)
     * 
     * @see
     * org.seasar.extension.jdbc.service.S2AbstractService#update(java.lang.
     * Object)
     */
    @Override
    public int update(E entity) {
        return jdbcManager.update(entity).excludesNull().execute();
    }

}
