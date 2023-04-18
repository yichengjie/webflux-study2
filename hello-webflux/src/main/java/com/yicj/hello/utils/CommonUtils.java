package com.yicj.hello.utils;

import com.yicj.hello.vo.UserIdentity;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.server.ServerRequest;

/**
 * @author: yicj
 * @date: 2023/4/18 21:11
 */
public class CommonUtils {

    public static UserIdentity parseUserIdentity(ServerRequest request){
        ServerRequest.Headers headers = request.headers();
        String userId = headers.firstHeader("user-id");
        if (!StringUtils.hasLength(userId)){
            return null ;
        }
        String userName = headers.firstHeader("user-name");
        String userNo = headers.firstHeader("user-no");
        UserIdentity userIdentity = new UserIdentity() ;
        userIdentity.setUserId(userId);
        userIdentity.setUserName(userName);
        userIdentity.setUserNo(userNo);
        return userIdentity ;
    }
}
