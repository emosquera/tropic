/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.base.locator;

import java.util.Hashtable;
import java.util.Map;
import java.util.TreeMap;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Edgar Mosquera
 */
public class ServiceLocator {

    private static volatile ServiceLocator instance = null;

    private final Map<String, Object> services = new TreeMap<>();

    private Hashtable jndiProperties = new Hashtable();
    private Context ctx;

    private ServiceLocator() throws NamingException {        
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,  "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
        ctx = new InitialContext(jndiProperties);
    }

    public static ServiceLocator instance() throws NamingException {

        if (instance == null) {

            synchronized (ServiceLocator.class) {
                if (instance == null) {
                    instance = new ServiceLocator();
                } else {
                    if (instance.ctx == null) {
                        instance.ctx = new InitialContext(instance.jndiProperties);
                    }
                }
            }
        }

        return instance;
    }

    public Object get(String beanName) throws NamingException {

        Object vs = services.get(beanName);
        Object serviceRef = null;

        if (vs != null) {
            try {
                //vs.isAlive();
                serviceRef = vs;
            } catch (Exception e) {
            }

        }
        if (serviceRef == null) {
            serviceRef = ctx.lookup(beanName);
            //assert ServiceVerifier.class.isAssignableFrom(serviceRef.getClass());
            services.put(beanName, serviceRef);
        }
        return serviceRef;
    }
    
}
