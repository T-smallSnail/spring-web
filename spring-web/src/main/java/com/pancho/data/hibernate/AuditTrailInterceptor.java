package com.pancho.data.hibernate;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.TimestampType;
import org.hibernate.type.Type;

/**
 * @date 2014年12月10日11:38:52
 * @author 赵伟锋
 * hibernate 持久对象的时候监听 save和update对公用字段的处理类
 */
@SuppressWarnings("serial")
public class AuditTrailInterceptor extends EmptyInterceptor{
	
	private final static String CREATE_TIME = "createTime" ; //创建时间的key
	public final static String UPDATE_TIME = "updateTime" ; //修改时间的key

	
	
	
	/*
	 * (non-Javadoc)
	 * @see org.hibernate.EmptyInterceptor#onFlushDirty(java.lang.Object, java.io.Serializable, java.lang.Object[], java.lang.Object[], java.lang.String[], org.hibernate.type.Type[])
	 */
	@Override
	public boolean onFlushDirty(Object entity, Serializable id,Object[] currentState, Object[] previousState,String[] propertyNames, Type[] types) {
		boolean modified = false;
		for(int i = 0 ;i < types.length ;i ++){
			if(UPDATE_TIME.equals(propertyNames[i]) && (types[i] instanceof TimestampType)){
				//Date date = new Date();
				Timestamp timestampNow = new Timestamp((new Date()).getTime());
				currentState[i] = timestampNow ;
				modified = true ;
			}

		}
		return modified;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.EmptyInterceptor#onSave(java.lang.Object, java.io.Serializable, java.lang.Object[], java.lang.String[], org.hibernate.type.Type[])
	 */
	@Override
	public boolean onSave(Object entity, Serializable id, Object[] currentState,String[] propertyNames, Type[] types) {
		boolean modified = false;
		for(int i = 0 ; i < types.length;i++){
			if(CREATE_TIME.equals(propertyNames[i]) && (types[i] instanceof TimestampType)){
				//Date date = new Date() ;
				Timestamp timestampNow = new Timestamp((new Date()).getTime());
				currentState[i] = timestampNow ;
				modified = true;
			}

		}
		return modified;
	}
	
	
}
