package no.nith.sivpal12.pg5100.eksamen.maintenance.interceptors.impl;

import java.util.LinkedList;
import java.util.List;

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
            List<String> params = new LinkedList<>();
            for (Object param : ctx.getParameters()) {
                params.add(param.toString());
            }
            LOGGER.info(
                    String.format(
                            "Method '%s' was called with parameters %s",
                            ctx.getMethod().getDeclaringClass().getSimpleName()
                                    + "."
                                    + ctx.getMethod().getName(),
                            params
                            )
                    );
        } finally {
            return ctx.proceed();
        }
    }
}
