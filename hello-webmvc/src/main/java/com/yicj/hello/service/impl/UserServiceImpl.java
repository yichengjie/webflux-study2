package com.yicj.hello.service.impl;

import com.yicj.hello.model.vo.UserVO;
import com.yicj.hello.service.UserService;
import com.yicj.hello.utils.CommonUtils;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserVO save(UserVO vo) {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String uuid = CommonUtils.uuid() ;
        vo.setId(uuid);
        return vo ;
    }
}
