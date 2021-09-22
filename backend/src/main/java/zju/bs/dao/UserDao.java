package zju.bs.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import zju.bs.domain.UserPojo;
//import java.util.List;

@Repository("userDao")
@Mapper
public interface UserDao {
    public UserPojo findByAccount(String account);
    public UserPojo findByEmail(String email);
    public UserPojo findByAccountAndPw(UserPojo userPojo);
    public UserPojo findByEmailAndPw(UserPojo userPojo);
    public int addUser(UserPojo userPojo);

    public UserPojo[] testDao();
}




