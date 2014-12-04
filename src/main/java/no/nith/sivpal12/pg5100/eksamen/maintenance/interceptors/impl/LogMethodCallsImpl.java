package no.nith.sivpal12.pg5100.eksamen.maintenance.interceptors.impl;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import no.nith.sivpal12.pg5100.eksamen.maintenance.interceptors.LogMethodCalls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Interceptor
@LogMethodCalls
public class LogMethodCallsImpl {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(LogMethodCallsImpl.class);

    @AroundInvoke
    public Object logValues(InvocationContext ctx) throws Exception {
        try {
            final Object[] paramArray = ctx.getParameters();
            String params =
                    paramArray.length > 0 ? paramArray[0].toString() : "";

            for (int i = 1; i < paramArray.length; i++) {
                params += ", " + paramArray[i];
            }
            LOGGER.info(String.format(
                    "%s.%s(%s)",
                    ctx.getMethod().getDeclaringClass().getSimpleName(),
                    ctx.getMethod().getName(), params));
        } finally {
            return ctx.proceed();
        }
    }
}
