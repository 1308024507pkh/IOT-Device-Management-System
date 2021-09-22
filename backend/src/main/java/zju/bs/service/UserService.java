package zju.bs.service;

import zju.bs.domain.UserPojo;

public interface UserService {
    public int register(UserPojo userPojo);
    public UserPojo loginByAccount(UserPojo userPojo);
    public UserPojo loginByEmail(UserPojo userPojo);
    public boolean loginValidateByAccount(UserPojo userPojo);
    public boolean loginValidateByEmail(UserPojo userPojo);

    public UserPojo[] testService();
}
