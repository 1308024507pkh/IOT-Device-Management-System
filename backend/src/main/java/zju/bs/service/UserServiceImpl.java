package zju.bs.service;

import zju.bs.dao.UserDao;
import zju.bs.domain.UserPojo;
import zju.bs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    public int register(UserPojo userPojo){
        if(userDao.findByAccount(userPojo.getAccount())!=null){
            return -2;
        }
        else if(userDao.findByEmail(userPojo.getEmail())!=null){
            return -1;
        }
        try {
            int res = userDao.addUser(userPojo);
            if(res>0)   return 1;
            else        return 0;
        } catch(Exception e){
            return 0;
        }
    }

    public UserPojo loginByAccount(UserPojo userPojo){
        UserPojo user = userDao.findByAccountAndPw(userPojo);
        if(user!=null)          return user;
        else                    return null;
    }

    public boolean loginValidateByAccount(UserPojo userPojo){
        if(userDao.findByAccount(userPojo.getAccount()) == null)
            return false;
        return true;
    }

    public UserPojo loginByEmail(UserPojo userPojo){
        UserPojo user = userDao.findByEmailAndPw(userPojo);
        if(user != null)    return user;
        else                return null;
    }

    public boolean loginValidateByEmail(UserPojo userPojo){
        if(userDao.findByEmail(userPojo.getEmail()) == null)
            return false;
        return true;
    }


    public UserPojo[] testService(){
        return userDao.testDao();
    }
}
