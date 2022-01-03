package hk.edu.cityu.cs.FYP.AIRegistry.dao;

import java.util.List;

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
            @Result(property = "firstName", column = "firstName"),
            @Result(property = "lastName", column = "lastName"),
            @Result(property = "userType", column = "userType"),
            @Result(property = "hashedPassword", column = "password"),
            @Result(property = "salt", column = "salt"),
            @Result(property = "newPassword", column = "new_password"),
            @Result(property = "newSalt", column = "new_salt")
    })
    @Select("""
            SELECT username, email, firstName, lastName, userType, password, salt, new_password, new_salt
            FROM user
            WHERE username = #{username}
            """)
    public UserInfo findUserByUsername(String username);

    @Update("""
            UPDATE user SET
            password = #{hashedPassword},
            salt = #{salt},
            new_password = NULL,
            new_salt = NULL
            WHERE
            username = #{username}
            """)
    public void changePassword(UserInfo userInfo);

    @Update("""
            UPDATE user SET
            firstName = #{firstName},
            lastName = #{lastName},
            email = #{email}
            WHERE
            username = #{username}
            """)
    public void changeUserInfo(UserInfo userInfo);

    @Update("""
            UPDATE user SET
            delete_date = NOW()
            WHERE
            username = #{username}
            """)
    public void deleteUser(String username);


    @Results({
        @Result(property = "username", column = "username"),
        @Result(property = "email", column = "email"),
        @Result(property = "firstName", column = "firstName"),
        @Result(property = "lastName", column = "lastName"),
        @Result(property = "userType", column = "userType"),
        @Result(property = "hashedPassword", column = "password"),
        @Result(property = "salt", column = "salt"),
        @Result(property = "newPassword", column = "new_password"),
        @Result(property = "newSalt", column = "new_salt")
    })
    @Select("""
            <script>
            SELECT username, email, firstName, lastName, userType, 
                password, salt, new_password, new_salt
            FROM user
            WHERE username IN
                <foreach item='item' index='index' collection='usernameList'
                    open='(' separator=',' close=')'>
                    #{item}
                </foreach>
            </script>
            """)
    public List<UserInfo> findUsersByUsername(List<String> usernameList);

}
