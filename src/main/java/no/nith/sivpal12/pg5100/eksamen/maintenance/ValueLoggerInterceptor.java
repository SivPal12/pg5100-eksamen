package no.nith.sivpal12.pg5100.eksamen.maintenance;

import java.util.LinkedList;
import java.util.List;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValueLoggerInterceptor {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ValueLoggerInterceptor.class);

    @AroundInvoke
    public Object logValues(InvocationContext ctx) throws Exception {
        try {
            List<String> params = new LinkedList<>();
            for (Object param : ctx.getParameters()) {
                params.add(param.toString());
            }
            LOGGER.trace(
                    String.format(
                            "Method '%s' was called with parameters %s",
                            ctx.getMethod().getDeclaringClass().getName() + "."
                                    + ctx.getMethod().getName(),
                            params
                            )
                    );
        } finally {
            return ctx.proceed();
        }
    }
}
