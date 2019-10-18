package com.techsnob.generator;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

public class DriverIdGenerator extends SequenceStyleGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object)
			throws HibernateException {
		return  super.generate(session, object);
	}

}
