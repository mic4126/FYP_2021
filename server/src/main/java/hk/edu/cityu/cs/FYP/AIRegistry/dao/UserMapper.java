package hk.edu.cityu.cs.FYP.AIRegistry.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import hk.edu.cityu.cs.FYP.AIRegistry.model.ResetPasswordInfo;
import hk.edu.cityu.cs.FYP.AIRegistry.model.UserInfo;

@Mapper
public interface UserMapper {
    @Insert("""
            INSERT INTO user
            (username, firstName, lastName, userType, password, salt, email)
            VALUES (#{username}, #{firstName}, #{lastName}, #{userType},
            #{hashedPassword}, #{salt}, #{email})""")

    public void createUser(UserInfo userInfo);

    @Update("""
            UPDATE user SET
            new_password = #{hashedPassword},
            new_salt = #{salt}
            WHERE
            username = #{username}
            AND email = #{email}
            """)
    public void resetPassword(ResetPasswordInfo resetPasswordInfo);

    @Results({
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "firstName",column = "firstName"),
            @Result(property = "lastName",column = "lastName"),
            @Result(property = "userType",column = "userType")
    })
    @Select("""
            SELECT username, email, firstName, lastName, userType 
            FROM user 
            WHERE username = #{username}
            """)
    public UserInfo findUserByUsername(String username);

}
