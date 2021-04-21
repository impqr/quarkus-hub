package per.pqr.rest;

import lombok.extern.jbosslog.JBossLog;
import org.jboss.resteasy.spi.HttpRequest;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.USER)
@JBossLog
public class Filter implements ExceptionMapper<Exception>, ContainerRequestFilter, ContainerResponseFilter {

    @Context
    UriInfo info;

    @Context
    private HttpRequest request;

    @Context
    HttpHeaders headers;

    /**
     * 异常拦截器
     */
    @Override
    public Response toResponse(Exception exception) {
        log.error(exception.getMessage());
        return Response.status(200).entity(ResponseMessage.buildErrorMessage("服务器异常，请联系管理员")).build();
    }

    /**
     * 前置拦截器
     */
    @Override
    public void filter(ContainerRequestContext context) {

        HttpHeaders headers = request.getHttpHeaders();

        String host          = request.getRemoteHost();
        String xRealIP       = headers.getHeaderString("X-Real-IP");
        String xForwardedFor = headers.getHeaderString("X-Forwarded-For");
        String method        = context.getMethod();
        String path          = info.getPath();

        log.infof("\nRequest\n\thost\t: %s --> %s --> %s\n\tmethod\t: %s\n\tpath\t: %s", host, xRealIP, xForwardedFor, method, path);
    }

    /**
     * 后置拦截器
     */
    @Override
    public void filter(ContainerRequestContext req, ContainerResponseContext res) {

        HttpHeaders headers = request.getHttpHeaders();

        String host          = request.getRemoteHost();
        String xRealIP       = headers.getHeaderString("X-Real-IP");
        String xForwardedFor = headers.getHeaderString("X-Forwarded-For");
        String method        = req.getMethod();
        String path          = info.getPath();
        Object entity        = res.getEntity();

        log.infof("\nResponse\n\thost\t: %s --> %s --> %s\n\tmethod\t: %s\n\tpath\t: %s\n\tentity\t: %s", host, xRealIP, xForwardedFor, method, path, entity);

        if (! (entity instanceof ResponseMessage)) {
            res.setEntity(ResponseMessage.buildMessage(entity));
        }
    }
}
