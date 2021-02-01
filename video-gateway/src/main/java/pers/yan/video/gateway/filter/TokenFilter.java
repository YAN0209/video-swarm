package pers.yan.video.gateway.filter;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import pers.yan.video.common.common.ResponseResult;
import pers.yan.video.common.utils.JwtTokenUtil;
import pers.yan.video.gateway.pojo.entity.Permission;
import pers.yan.video.gateway.service.AccessRemote;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * token校验
 *
 * @author likaiyan
 * @date 2020/8/28 9:55 上午
 */
@Component
public class TokenFilter implements GlobalFilter, Ordered {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AccessRemote accessRemote;

    public static final String PATTERN = "/auth/**";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String path = request.getURI().getPath();
        String realPath = path.substring(path.indexOf("/", path.indexOf("/") + 1));
        String token = jwtTokenUtil.getToken(request);

        AntPathMatcher matcher = new AntPathMatcher();

        //auth开头的路径直接放行
        if (matcher.match(PATTERN, realPath)) {
            return chain.filter(exchange);
        }

        //检查token是否合法
        if (StringUtils.isEmpty(token) || !jwtTokenUtil.validateToken(token)) {
            return doResponse(response, ResponseResult.unauthorized());
        }

        int userId = jwtTokenUtil.getUserIdFromToken(token);

        //校验url权限
        List<Permission> permissions = accessRemote.getPermissionsByUser(userId).getData();
        if (!CollectionUtils.isEmpty(permissions)) {
            for (Permission permission : permissions) {
                if (matcher.match(permission.getUri(), realPath)){
                    request.mutate().header("userId", String.valueOf(userId));
                    return chain.filter(exchange);
                }
            }
        }

        //无权限
        return doResponse(response, ResponseResult.forbidden());
    }

    @Override
    public int getOrder() {
        return -100;
    }

    private Mono<Void> doResponse(ServerHttpResponse response, ResponseResult<?> responseResult) {
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
        byte[] bytes = new Gson().toJson(responseResult).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        return response.writeWith(Flux.just(buffer));
    }

}
