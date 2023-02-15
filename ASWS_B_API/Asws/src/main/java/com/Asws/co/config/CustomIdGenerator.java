package com.Asws.co.config;

import java.io.Serializable;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CustomIdGenerator implements IdentifierGenerator{

    @Override
    public Serializable generate(SharedSessionContractImplementor arg0, Object arg1) throws HibernateException {
        Random rnd = new Random();
        return "STD_"+rnd.nextInt(99999999);
    }
    
}
